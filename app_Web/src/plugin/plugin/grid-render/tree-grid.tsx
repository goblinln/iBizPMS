
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppContextStore, AppGridService } from 'ibiz-vue';
import { AppDefaultGrid } from 'ibiz-vue/src/components/control/app-default-grid/app-default-grid';
import { IPSDEGridUAColumn, IPSDEUIAction, IPSDEUIActionGroup, IPSUIActionGroupDetail } from '@ibiz/dynamic-model-api';

export class AppTreeGridService extends AppGridService {

    constructor(opts: any = {}) {
        super(opts);
    }
    
    public handleRequestData(action: string, context: any, data: any = {}, isMerge: boolean = false) {
        let mode: any = this.getMode();
        if (!mode && mode.getDataItems instanceof Function) {
            return data;
        }
        let formItemItems: any[] = mode.getDataItems();
        formItemItems.push({
            name:'items',
            prop:'items',
            dataType: 'QUERYPARAM'
        })
        let requestData: any = {};
        if (isMerge && (data && data.viewparams)) {
            Object.assign(requestData, data.viewparams);
        }
        formItemItems.forEach((item: any) => {
            if (item && item.dataType && Object.is(item.dataType, 'FRONTKEY')) {
                if (item && item.prop) {
                    requestData[item.prop] = context[item.name];
                }
            } else {
                if (item && item.prop) {
                    requestData[item.prop] = data[item.name];
                } else {
                    if (item.dataType && Object.is(item.dataType, "FORMPART")) {
                        Object.assign(requestData, data[item.name]);
                    }
                }
            }
        });
        let tempContext: any = JSON.parse(JSON.stringify(context));
        if (tempContext && tempContext.srfsessionid) {
            tempContext.srfsessionkey = tempContext.srfsessionid;
            delete tempContext.srfsessionid;
        }
        return { context: tempContext, data: requestData };
    }

    public handleResponseData(action: string, data: any = {}, isCreate?: boolean, codelistArray?: any) {
        let model: any = this.getMode();
        if (!(model && model.getDataItems instanceof Function)) {
            return data;
        }
        let item: any = {};
        let dataItems: any[] = model.getDataItems();
        dataItems.push({
            name:'items',
            prop:'items',
            dataType: 'QUERYPARAM'
        })
        dataItems.forEach(dataitem => {
            let val = data.hasOwnProperty(dataitem.prop) ? data[dataitem.prop] : null;
            if (val === null) {
                val = data.hasOwnProperty(dataitem.name) ? data[dataitem.name] : null;
            }
            if ((isCreate === undefined || isCreate === null) && Object.is(dataitem.dataType, 'GUID') && Object.is(dataitem.name, 'srfkey') && (val && !Object.is(val, ''))) {
                isCreate = true;
            }
            item[dataitem.name] = val;
            // 转化代码表
            if (codelistArray && dataitem.codelist) {
                if (codelistArray.get(dataitem.codelist.tag) && codelistArray.get(dataitem.codelist.tag).get(val)) {
                    item[dataitem.name] = codelistArray.get(dataitem.codelist.tag).get(val);
                }
            }
        });
        item.srfuf = data.srfuf ? data.srfuf : (isCreate ? "0" : "1");
        return item;
    }
}



/**
 * 树表格展示插件类
 *
 * @export
 * @class TreeGrid
 * @class TreeGrid
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class TreeGrid extends AppDefaultGrid {

public async ctrlModelInit() {
        await super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppTreeGridService(this.controlInstance);
        }
    }

	/**
     * 绘制操作列
     * 
     * @param {any} column 表格列实例
     * @memberof TreeGrid 
     */
    public renderUAColumn(column: IPSDEGridUAColumn) {
        const { name, caption, align, width, widthUnit } = column;
        //参数
        let renderParams: any = {
            "column-key": name,
            "label": caption,
            "align": align ? align.toLowerCase() : "center",
            "fixed": 'right'
        }
        if (widthUnit && widthUnit != "STAR") {
            renderParams["width"] = width;
        } else {
            renderParams["min-width"] = width;
        }
        //绘制
        return this.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return this.renderActionModel(column, scope)
                },
                header: () => {
                    return <span class="column-header">{column.caption}</span>
                }
            }
        });
    }


    /**
     * 绘制操作列内容
     * 
     * @param {any} _column 表格列实例
     * @param {row, column, $index} scope 插槽返回数据
     */
    public renderActionModel(_column: IPSDEGridUAColumn, scope: any) {
        const uiactionDetails: Array<IPSUIActionGroupDetail> = (_column.getPSDEUIActionGroup() as IPSDEUIActionGroup)?.getPSUIActionGroupDetails() || [];
        if (uiactionDetails.length > 0) {
            return (
                <div style="text-align: center;">
                    {
                        scope.row.children ?
                            <span>
                                <a title={scope.row.tooltip} on-click={this.loadMore(scope.row)}>{scope.row.toolcaption}</a>
                            </span> : this.renderActionModelDetail(_column, scope, uiactionDetails)
                    }
                </div>
            );
        }
    }

    /**
     * 计算表格参数
     * 
     * @memberof TreeGrid
     */
    public computeGridParams() {
        const opt = super.computeGridParams();
        opt['treeProps'] = {children: 'items', hasChildren: 'hasChildren'}
        opt['rowKey'] = 'id'
        return opt;
    }

    /**
     * 绘制操作列内容
     * 
     * @param {any} _column 表格列实例
     * @param {row, column, $index} scope 插槽返回数据
     */
    public renderActionModelDetail(_column: any, scope: any, uiactionDetails: Array<IPSUIActionGroupDetail>) {
        const { row } = scope;
        if (uiactionDetails.length > 0) {
            return (
                <div style="text-align: center;">
                    {uiactionDetails.map((uiactionDetail: IPSUIActionGroupDetail, index: number) => {
                        const uiaction: IPSDEUIAction = uiactionDetail.getPSUIAction() as IPSDEUIAction;
                        if(row[uiaction.uIActionTag]?.visabled){
                            return (
                                <tooltip transfer={true} max-width={600}>
                                    <a class={index == 0 ? "grid-first-uiaction" : "grid-uiaction-divider"} disabled={row[uiaction.uIActionTag].disabled} style={{'display': 'block'}} on-click={($event: any) => { this.handleActionClick(row, $event, _column, uiactionDetail) }} >
                                        {uiactionDetail.showIcon ? <i class={uiaction?.getPSSysImage()?.cssClass ? uiaction?.getPSSysImage()?.cssClass : "fa fa-save"}></i> : ""}
                                    </a>
                                    <div slot="content">
                                        {uiaction?.caption ? uiaction.caption : ""}
                                    </div>
                                </tooltip>
                            )
                        }else{
                            return null;
                       }
                    })}
                </div>
            );
        }
    }

    /**
     * 上下文仓库
     *
     * @memberof TreeGrid
     */
    public appContextStore = new AppContextStore();

    /**
     * 表格数据加载
     *
     * @param {*} [arg={}]
     * @memberof TreeGrid
     */
    public load(opt: any = {}, pageReset: boolean = false): void {
     
        if (pageReset) {
            this.curPage = 1;
        }
        const arg: any = { ...opt };
        const page: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage - 1, size: this.limit });
        }
        // 设置排序
        if (!this.isNoSort && !Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + "," + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg, { viewparams: tempViewParams });
		this.ctrlBeginLoading();
        const post: Promise<any> = this.service.search(this.fetchAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        post.then((response: any) => {
			this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                if (response.errorMessage) {
                }
                return;
            }
            const data: any = response.data;
            if(data.length === 0 && this.curPage > 1) {
                this.curPage--;
                this.load(opt, pageReset);
                return;
            }
            this.totalRecord = response.total;
            this.items = JSON.parse(JSON.stringify(data));
            // 清空selections,gridItemsModel
            this.selections = [];
            this.gridItemsModel = [];
            this.items.forEach(() => { this.gridItemsModel.push(this.getGridRowModel()) });
            this.items.forEach((item: any) => {
                this.setActionState(item);
            });
		  	this.ctrlEvent({ controlname: this.controlInstance.name, action: "load", data: this.items });
            // 向上下文中填充当前数据
            this.appContextStore.setContextData(this.context, this.service?.appEntityService?.APPDENAME, { items: this.items });
            // 设置默认选中
            setTimeout(() => {
                if (this.isSelectFirstDefault) {
                    this.rowClick(this.items[0]);
                }
                if (this.selectedData) {
                    const refs: any = this.$refs;
                    if (refs.multipleTable) {
                        refs.multipleTable.clearSelection();
                        JSON.parse(this.selectedData).forEach((selection: any) => {
                            let selectedItem = this.items.find((item: any) => {
                                return Object.is(item.srfkey, selection.srfkey);
                            });
                            if (selectedItem) {
                                this.rowClick(selectedItem);
                            }
                        });
                    }
                }
            }, 300);
            this.addMore();
            
        }).catch((response: any) => {
			this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
        });
    }

    /**
     * 表格数据加载
     *
     * @param {*} item
     * @returns {void}
     * @memberof TreeGrid
     */
    public setActionState(item: any) {
        Object.assign(item, this.getActionState(item));
        if(item.items && item.items.length > 0) {
            item.items.forEach((data: any) => {
                let _data: any = this.service.handleResponseData('', data);
                Object.assign(data, _data);
                this.setActionState(data);
            })
        }
    }

    /**
    * 添加更多行
    * 
    * @memberof TreeGrid
    */
    public addMore(){
        if(this.items.length > 0){
            this.items.forEach((item: any) => {
                if(item.hasOwnProperty('items') && item.items && item.items.length == 20){
                    const moreitem: any = {
                        children: true,
                        parent: item.id,
                    }
                    item.items.push(moreitem);
                }
            })
        }
    }

    /**
     * 数据导出
     *
     * @param {*} data
     * @memberof TreeGrid
     */
    public exportExcel(data: any = {}): void {
        let _this: any = this;
        // 导出Excel
        const isDeExport: boolean = this.allExportColumns?.length>0 ? true : false;
        console.log(this.allExportColumns);
        const doExport = async (_data: any) => {
            const tHeader: Array<any> = [];
            const filterVal: Array<any> = [];
            (isDeExport ? this.allExportColumns : this.allColumns).forEach((item: any) => {
                item.show && item.label ? tHeader.push(item.label) : '';
                item.show && item.name ? filterVal.push(item.name) : '';
            });
            const data = await this.formatExcelData(filterVal, _data);
            _this.$export.exportExcel().then((excel: any) => {
                excel.export_json_to_excel({
                    header: tHeader, //表头 必填
                    data, //具体数据 必填
                    filename: `${this.controlInstance.getPSAppDataEntity()?.logicName}` + (this.$t('app.gridpage.grid') as string), 
                    autoWidth: true, //非必填
                    bookType: 'xlsx', //非必填
                });
            });
        };
        const page: any = {};
        // 设置page，size
        if (Object.is(data.type, 'maxRowCount')) {
            Object.assign(page, { page: 0, size: data.maxRowCount });
        } else if (Object.is(data.type, 'activatedPage')) {
            if (isDeExport) {
                Object.assign(page, { page: this.curPage - 1, size: this.limit });
            } else {
                try {
                    const datas = [...this.items];
                    let exportData: Array<any> = [];
                    datas.forEach((data: any) => {
                        exportData.push(data);
                        if(data.hasOwnProperty('items') && data.items && data.items instanceof Array){
                            data.items.forEach((item: any) => {
                                exportData.push(item);
                            });
                        }
                    });
                    doExport(JSON.parse(JSON.stringify(exportData)));
                } catch (error) {
                    console.error(error);
                }
                return;
            }
        }
        // 设置排序
        if (!this.isNoSort && !Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + ',' + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        const arg: any = {};
        Object.assign(arg, page);
        // 获取query,搜索表单，viewparams等父数据
        const parentdata: any = {};
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg, { viewparams: tempViewParams });
        let post: any;
		this.ctrlBeginLoading();
        if (isDeExport) {
            post = this.service.searchDEExportData(
                this.fetchAction,
                JSON.parse(JSON.stringify(this.context)),
                arg,
                this.showBusyIndicator
            );
        } else {
            post = this.service.search(
                this.fetchAction,
                JSON.parse(JSON.stringify(this.context)),
                arg,
                this.showBusyIndicator
            );
        }
        post.then((response: any) => {
			this.ctrlEndLoading();
            if (!response || response.status !== 200) {
                this.$Notice.error({
                    title: '',
                    desc: (this.$t('app.gridpage.exportFail') as string) + ',' + response.info,
                });
                return;
            }
            try {
                const datas = [...response.data];
                let exportData: Array<any> = [];
                datas.forEach((data: any) => {
                    exportData.push(data);
                    if(data.hasOwnProperty('items') && data.items && data.items instanceof Array){
                        data.items.forEach((item: any) => {
                            exportData.push(item);
                        });
                    }
                });
                doExport(JSON.parse(JSON.stringify(exportData)));
            } catch (error) {
                console.error(error);
            }
        }).catch((response: any) => {
			this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: '', desc: this.$t('app.gridpage.exportFail') as string });
        });
    }

    /**
     * 加载更多
     *  
     * @param data
     * @memberof  TreeGrid
     */
    public loadMore(data: any) {

    }

}

