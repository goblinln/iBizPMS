
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppDefaultGrid } from 'ibiz-vue/src/components/control/app-default-grid/app-default-grid';
import { IBizEditorModel } from 'ibiz-core';


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
            this.$Notice.error({ title: '错误', desc: 'CaseStepMainGridView9_EditMode视图表格loaddraftAction参数未配置' });
            return;
        }
        let _this = this;
        let param: any = {};
        Object.assign(param,{viewparams:this.viewparams});
		this.ctrlBeginLoading();
        let post: Promise<any> = this.service.loadDraft(this.loaddraftAction, JSON.parse(JSON.stringify(this.context)), param, this.showBusyIndicator);
        post.then((response: any) => {
			this.endLoading();
            if (!response.status || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error({ title: '错误', desc: response.errorMessage });
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
			this.endLoading();
            if (response && response.status === 401) {
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({ title: '错误', desc: '系统异常' });
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
        let columnsInstanceArr: Array<any> = this.controlInstance.allColumns;
        if(columnsInstanceArr && columnsInstanceArr.length>0) {
            for(const columnInstance of columnsInstanceArr) {
                let editItem: any = this.controlInstance.getEditColumnByName(columnInstance.name.toLowerCase());
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
                    codelistId: columnInstance.codeList?.codeName,
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
    public renderEditContent(item: any,scope: any) {
        const editItem: any = this.controlInstance.getEditColumnByName(item.name.toLowerCase());
        let editor: IBizEditorModel = new IBizEditorModel(editItem?.getPSEditor,this.context);
        const { row, column, $index } = scope;
        return (
            <app-default-editor
                editorInstance={editor}
                value={row[editor?.name]}
                context={this.context}
                contextData={row}
                viewparams={this.viewparams}
                on-change={(value: any) => {
                    this.onGridItemValueChange(row, { name: editor.name, value: value !== null && typeof value === 'object' ? value.value : value }, $index);
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
        this.allColumnsInstance.forEach((item: any) => {
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
        return (
            <group-step-table
                class="grid" 
                groupfield={this.controlInstance?.getColumnByName("type")? "type": ""}
                data={this.items}
                cols={this.allColumns}
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

