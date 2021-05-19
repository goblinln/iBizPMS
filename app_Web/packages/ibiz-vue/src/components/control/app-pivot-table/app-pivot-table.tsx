
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { IPSAppCodeList, IPSDEGridColumn, IPSDEGridFieldColumn } from '@ibiz/dynamic-model-api';
import { AppGridBase } from '../app-common-control/app-grid-base';

/**
 * 透视表
 *
 * @export
 * @class AppPivotTable
 * @class AppPivotTable
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppPivotTable extends AppGridBase {

    /**
     * 选中数据字符串
     *
     * @type {string}
     * @memberof AppPivotTable
     */
    public selectedData?: string;

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPivotTable
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.selectedData = newVal.selectedData;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 选中值变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPivotTable
     */
    @Watch('selectedData')
    public onValueChange(newVal: any, oldVal: any) {
        this.selections = [];
        if(this.selectedData){
            const refs: any = this.$refs;
            if (refs[this.gridRefName]) {
                refs[this.gridRefName].clearSelection();
                JSON.parse(this.selectedData).forEach((selection:any)=>{
                    let selectedItem = this.items.find((item:any)=>{
                        return Object.is(item.srfkey, selection.srfkey);
                    });
                    if(selectedItem){
                        this.rowClick(selectedItem);
                    }
                });
            }
        }
    }

    /**
     * 表格数据加载
     *
     * @param {*} [arg={}]
     * @memberof AppPivotTable
     */
    public load(opt: any = {}, pageReset: boolean = false): void {
        if(!this.fetchAction){
            this.$throw((this.$t('app.gridpage.notConfig.fetchAction') as string),'load');
            return;
        }
        if(pageReset){
            this.curPage = 1;
        }
        const arg: any = {...opt};
        const page: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage-1, size: this.limit });
        }
        // 设置排序
        if (!this.isNoSort && !Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF+","+this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.ctrlEvent({ controlname: this.name , action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams:any = parentdata.viewparams?parentdata.viewparams:{};
        Object.assign(tempViewParams,JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg,{viewparams:tempViewParams});
        this.ctrlBeginLoading();
        const post: Promise<any> = this.service.search(this.fetchAction,JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        post.then(async (response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                this.$throw(response,'load');
                return;
            }
            const data: any = response.data;
            this.totalRecord = response.total;
            this.items = await this.formatGridData(JSON.parse(JSON.stringify(data)));
            this.gridItemsModel = [];
            this.items.forEach(()=>{this.gridItemsModel.push(this.getGridRowModel())});
            this.items.forEach((item:any)=>{
                Object.assign(item,this.getActionState(item));    
            });
            this.ctrlEvent({ controlname: this.name , action: "load", data: this.items });
            // 设置默认选中
            let _this = this;
            setTimeout(() => {
                //在导航视图中，如已有选中数据，则右侧展开已选中数据的视图，如无选中数据则默认选中第一条
                if(_this.isSelectFirstDefault){
                    if(_this.selections && _this.selections.length > 0){
                        if(_this.items.length == 0) {
                            let models: Array<any> = this.service.getMode().getDataItems();
                            if(models?.length>0) {
                                let emptyItem: any = {}; 
                                models.forEach((model: any) => {
                                    emptyItem[model.name] = null;
                                });
                                this.ctrlEvent({ controlname: _this.name, action: "selectionchange", data: [emptyItem] });
                            }
                        }
                        _this.selections.forEach((select: any)=>{
                            const index = _this.items.findIndex((item:any) => Object.is(item.srfkey,select.srfkey));
                            if(index != -1){
                                _this.rowClick(_this.items[index]);
                            }
                        })
                    }else{
                        _this.rowClick(this.items[0]);
                    }
                }
                if(_this.selectedData){
                    const table: any = (this.$refs as any)[this.gridRefName];
                    if (table) {
                        table.clearSelection();
                        JSON.parse(_this.selectedData).forEach((selection:any)=>{
                            let selectedItem = _this.items.find((item:any)=>{
                                return Object.is(item.srfkey, selection.srfkey);
                            });
                            if(selectedItem){
                                _this.rowClick(selectedItem);
                            }
                        });
                    }
                }
            }, 300);
            if(this.aggMode && Object.is(this.aggMode, "ALL")) {
                this.getAggData();
            }          
        }).catch((response: any) => {
            this.ctrlEndLoading();
            this.$throw(response,'load');
        });
    }

    /**
     * 表格数据代码表翻译
     *
     * @public
     * @param {any} data 表格数据
     * @memberof AppPivotTable
     */
    public async formatGridData(data:any){
        let columnsInstanceArr: Array<IPSDEGridColumn> = this.controlInstance.getPSDEGridColumns() || [];
        let codelistColumns:Array<any> = [];
        if(columnsInstanceArr && columnsInstanceArr.length>0) {
            for(const columnInstance of columnsInstanceArr) {
                const codelist: IPSAppCodeList = (columnInstance as IPSDEGridFieldColumn).getPSAppCodeList() as IPSAppCodeList;
                if(codelist && codelist.codeName){
                    let codeListColumn = { 
                        name: columnInstance.name.toLowerCase(),
                        srfkey: codelist.codeName,
                        codelistType: codelist.codeListType,
                    }
                    if(codelist.orMode && Object.is('STR', codelist.orMode)){
                        Object.assign(codeListColumn,{
                            renderMode: 'string',
                            textSeparator: codelist.textSeparator,
                            valueSeparator: codelist.valueSeparator,
                        })
                    } else if(codelist.orMode && Object.is('NUM', codelist.orMode)){
                        Object.assign(codeListColumn,{
                            renderMode: 'number',
                            textSeparator: codelist.textSeparator,
                            valueSeparator: ',',
                        })
                    } else {
                        Object.assign(codeListColumn,{
                            renderMode: 'other',
                            textSeparator: '、',
                            valueSeparator: ',',
                        })
                    }
                    codelistColumns.push(codeListColumn);
                }
            }
        }
        let _this = this;
        if(codelistColumns.length >0){
            for (const codelist of codelistColumns) {
                let items = await _this.codeListService.getDataItems({ type: codelist.codelistType, tag: codelist.srfkey });
                data.forEach((row:any)=>{
                    row[codelist.name] = _this.getCodeListItemValue(items, row[codelist.name]);
                });
            }
        }
        return data;
    }

    /**
     * 代码表转化
     *
     * @public
     * @param {any} items 代码表所有数据
     * @param {any} value 当前数据当前项代码表值
     * @memberof AppPivotTable
     */
    public getCodeListItemValue(items: any, value: any){
        if(items && items.length >0){
            for(let i=0;i<items.length;i++){
                if(items[i].value === value){
                    return items[i].text;
                }
            }
            return value;
        }else{
            return value;
        }
    }

    /**
     * 绘制透视表内容
     * 
     * @param h 
     * @memberof AppPivotTable
     */
    public renderGridContent(h: any){
        const style = {
            'height' : this.isEnablePagingBar && this.items.length > 0 ? 'calc(100% - 50px)' : '100%',
            'overflow': 'auto'
        }
        return (
            <div style={style}>
                <app-vue-pivottable datas={this.items} allColumns={this.allColumns}></app-vue-pivottable>
            </div>
        );
    }

}

