
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppDefaultGrid } from 'ibiz-vue/src/components/control/app-default-grid/app-default-grid';
import { IPSDEGrid, IPSDEGridColumn, IPSDEGridEditItem, IPSDEGridFieldColumn, IPSEditor } from '@ibiz/dynamic-model-api';
import { ModelTool } from 'ibiz-core';


/**
 * 分组步骤表格插件类
 *
 * @export
 * @class StepTable
 * @class StepTable
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class StepTable extends AppDefaultGrid {


    /**
     * 添加数据
     * 
     * @param {*}  row 行数据
     * @memberof StepTable
     */
    public add({ row, index }: { row: any, index: number }, func: Function) {
        if(!this.loaddraftAction){
            this.$throw('CaseStepMainGridView9_EditMode视图表格loaddraftAction参数未配置','add');
            return;
        }
        let _this = this;
        let param: any = {};
        Object.assign(param,{viewparams:this.viewparams});
        let tempContext:any = JSON.parse(JSON.stringify(this.context));
        this.onControlRequset('create', tempContext, param);
        let post: Promise<any> = this.service.loadDraft(this.loaddraftAction, tempContext, param, this.showBusyIndicator);
        post.then((response: any) => {
			this.onControlResponse('create', response);
            if (!response.status || response.status !== 200) {
                if (response.errorMessage) {
                    this.$throw(response.errorMessage,'add');
                }
                return;
            }
            const data = response.data;
            this.createDefault(data);
            data.rowDataState = "create";
            if(row.type) {
                if(Object.is(row.type.toLowerCase(), 'group') || Object.is(row.type.toLowerCase(), 'item')) {
                    data.type = 'item';
                } else {
                    data.type = 'step';
                }
            }
            if(func instanceof Function) {
                func(data);
            }
            _this.gridItemsModel.push(_this.getGridRowModel());
        }).catch((response: any) => {
			this.onControlResponse('create', response);
            if (response && response.status === 401) {
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$throw('系统异常','add');
                return;
            }
        });
    }

    /**
     * 获取所有列成员模型
     *
     * @param {IBizGridModel} gridInstance 表格部件实例对象
     */
    public initAllColumns() {
        this.allColumns = [];
        let columnsInstanceArr: Array<IPSDEGridColumn> = this.controlInstance.getPSDEGridColumns() || [];
        if(columnsInstanceArr && columnsInstanceArr.length>0) {
            for(const columnInstance of columnsInstanceArr) {
                let editItem: IPSDEGridEditItem = ModelTool.getGridItemByCodeName(columnInstance.codeName, this.controlInstance) as IPSDEGridEditItem;
                //表格列
                const column = {
                    name: columnInstance.name.toLowerCase(),
                    label: columnInstance.caption,
                    //todo  国际化标识待补充(需求部件实体信息)
                    langtag: '',
                    show: !columnInstance.hideDefault,
                    unit: columnInstance.widthUnit,
                    isEnableRowEdit: columnInstance.enableRowEdit,
                    enableCond: editItem?.enableCond ? editItem?.enableCond : 3,
                    codelistId: (columnInstance as IPSDEGridFieldColumn)?.getPSAppCodeList?.()?.codeName,
                };
                this.allColumns.push(column);
            }
        }
    }

    /**
     * 绘制行编辑内容
     * 
     * @param {*} item 列数据
     * @param {*} scope 标识
     * @memberof StepTable
     */
    public renderEditContent(item: IPSDEGridColumn,scope: any) {
        const editItem: IPSDEGridEditItem = ModelTool.getGridItemByCodeName(item.codeName, this.controlInstance) as IPSDEGridEditItem;
        let editor = editItem.getPSEditor() as IPSEditor;
        const { row, column, $index } = scope;
        const data: any = {
            row: row,
            datas: this.items
        }
        return (
            <app-default-editor
                editorInstance={editor}
                value={row[editor?.name]}
                context={this.context}
                contextData={data}
                viewparams={this.viewparams}
                on-change={(value: any) => {
                    this.onGridItemValueChange(row, value, $index);
                }}
            />
        )
    }

    /**
     * 绘制具名插槽
     * 
     * 
     * @memberof StepTable
     */
    public renderSolt(){
        let scopedSlots: any = {}
        this.allColumnsInstance.forEach((item: IPSDEGridColumn) => {
            scopedSlots[item.name] = (scope: any)=>{
                return (
                    this.renderEditContent(item, scope) 
                )
            }
        })
        return scopedSlots;
    }

    /**
     * 绘制分组步骤表格
     * 
     * @param h 
     * @memberof StepTable
     */
    public render(h: any){
        if (!this.controlIsLoaded) {
            return null;
        }
        const groupfield =  ModelTool.getGridItemByCodeName("type", this.controlInstance, 'GRIDCOLUMN') as IPSDEGridColumn;
        return (
            <group-step-table
                class="grid"
		           newRowState={this.newRowState}
                groupfield={groupfield ? "type": ""}
                data={this.items}
                cols={this.allColumns}
						context={this.context}
                viewparams={this.viewparams}
                codeListService={this.codeListService}
                isEdit={this.actualIsOpenEdit}
                gridItemsModel={this.gridItemsModel}
                on-change={(row: any, field: string,rowField: any, index: number) => {
                    this.gridEditItemChange(row,field,rowField,index);
                }}
                on-remove={(data: any) => {
                    this.remove(data);
                }}
                on-add={(data: any,fun: any) => {
                    this.add(data,fun);
                }}
                scopedSlots={
                    this.controlInstance.enableRowEdit ? this.renderSolt() : false
                }>
            </group-step-table> 
        )
    }


}

