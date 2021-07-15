
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppGridBase } from 'ibiz-vue/src/components/control/app-common-control/app-grid-base';
import { IPSDEGridColumn } from '@ibiz/dynamic-model-api';
import '../plugin-style.less';

/**
 * 表格批保存插件插件类
 *
 * @export
 * @class SaveBatch
 * @class SaveBatch
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class SaveBatch extends AppGridBase {

    /**
     * 计算表格参数
     * 
     * @memberof SaveBatch
     */
    public computeGridParams() {
        let options: any = {
            "data": this.items,
            "size":"mini",
            "stripe": true,
            "high-light-current-row": this.controlInstance.singleSelect,
            "show-header": !this.controlInstance.hideHeader,
            "row-class-name": ({ row, rowIndex }: any) => this.getRowClassName({ row, rowIndex }),
            "cell-class-name": ({ row, column, rowIndex, columnIndex }: any) => this.getCellClassName({ row, column, rowIndex, columnIndex }),
            
        };
        const { aggMode } = this.controlInstance;
        //  支持排序
        if (!this.isNoSort) {
            Object.assign(options, {
                "default-sort": {
                    prop: this.minorSortPSDEF,
                    order: Object.is(this.minorSortDir, "ASC") ? "ascending" : Object.is(this.minorSortDir, "DESC") ? "descending" : ""
                }
            });
        }
        //  支持表格聚合
        if (aggMode && aggMode != "NONE") {
            Object.assign(options, {
                "show-summary": this.items.length > 0 ? true : false,
                "summary-method": (param: any) => this.getSummaries(param)
            });
        }
        //  支持分组
        if (this.isEnableGroup) {
            Object.assign(options, {
                "span-method": ({ row, column, rowIndex, columnIndex }: any) => this.arraySpanMethod({ row, column, rowIndex, columnIndex }),
                "tree-props": {
                    children: "children",
                    hasChildren: "children && children.length>0 ? true : false"
                },
                "row-key": "groupById"
            });
        }
        return options;
    }

    /**
     * 绘制数据列
     * 
     * @param {*} h CreateElement对象
     * @param {any} column 表格列实例
     * @memberof AppGridBase
     */
    public renderGridColumn(column: any) {
        const { name, enableRowEdit, width, caption, widthUnit, align } = column;
        const editItem = this.controlInstance.findPSDEGridEditItem(column.codeName);
        let renderParams: any = {
            "label": caption,
            "prop": name,
            "align": align ? align.toLowerCase() : "center",
            "sortable": "custom"
        }
        if (widthUnit && widthUnit != "STAR") {
            renderParams["width"] = width;
        } else {
            renderParams["min-width"] = width;
        }
        return this.$createElement('el-table-column', {
            props: renderParams,
            scopedSlots: {
                default: (scope: any) => {
                    return (this.actualIsOpenEdit && enableRowEdit && editItem) ?
                        this.renderOpenEditItem(column, scope) : this.renderColumn(column, scope)
                },
                header: () => {
                    return <span class="column-header">{column.caption}</span>
                }
            }
        });
    }

    /**
     * 保存行，批量保存、
     * @memberof SaveBatch
     */
    public async save() {
        if (!await this.validateAll()) {
            if(this.errorMessages && this.errorMessages.length > 0) {
                this.$throw(this.errorMessages[0],'save');
            } else {
                this.$throw((this.$t('app.commonwords.rulesexception') as string),'save');
            }
            return [];
        }
        let action = "saveBatch";
        let _context = JSON.parse(JSON.stringify(this.context));
        let result: Array<any> = [];
        for(const item of this.items){
            let { data: Data,context: Context } = this.service.handleRequestData(action, _context, item, true);
            if (Object.is(item.rowDataState, 'create')){
                Data.id = null;
            }
            result.push(Data);
        }
		   this.onControlRequset('saveBatch', _context, result);
        const post: Promise<any> = this.appEntityService.saveBatch(_context, result, true);
        post.then((response:any) =>{
			this.onControlResponse('saveBatch', response);
            if (response && response.status === 200) {
                this.$success(this.$t('app.commonwords.savesuccess'),'save');
                this.closeView(response.data);
            }
        }).catch((error: any) =>{
			this.onControlResponse('saveBatch', error);
            this.$throw(error.data.message,'save');
            console.error(error);
        })
    }

    /**
     * 绘制
     * 
     * @param h 
     */
    public render(h: any) {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        return (<div class={{ ...controlClassNames, 'grid': true, "grid-save-batch":true }} >
            <i-form style="height:100%">
                {this.renderGridContent(h)}
                {this.controlInstance.enablePagingBar ? this.renderPagingBar(h) : ""}
            </i-form>
        </div>)
    }

}

