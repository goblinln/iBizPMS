

import { DataTypes, IBizEditorModel, Util } from "ibiz-core";

/**
 * 关联表单项复合绘制插件插件类
 *
 * @export
 * @class Associatedform
 * @extends {Vue}
 */
export class Associatedform {

    /**
     * 绘制表单项前端扩展插件
     * 
     * @param h 
     * @param ctrlItemModel 表单项模型
     * @param parentContainer 表单容器
     * @param data 数据
     * @returns 
     */
    public renderCtrlItem(h: any, ctrlItemModel: any, parentContainer: any, data: any) {
        const { formItems } = ctrlItemModel;
        const formItemsProp: any[] = [];
        const localParam: any[] = [];
        const getItemLocaleTag = (field: any) => {
            if (field.getPSAppDEField) {
                return `entities.${parentContainer.controlInstance.appDataEntity?.codeName?.toLowerCase()}.fields.${field.getPSAppDEField.codeName.toLowerCase()}`
            }
        }
        formItems?.forEach(async (item: any) => {
            if (item.name != 'srfarray' && item.getPSAppDEField) {
                let tempProp: any = {
                    name: item.caption,
                    localetag: getItemLocaleTag(item),
                    prop: item.getPSAppDEField.codeName.toLowerCase(),
                };
                if (item.userTag && (item.userTag == 'unique' || item.userTag == 'hidden')) {
                    Object.assign(tempProp, {
                        [item.userTag == 'unique' ? 'unique' : 'hidden']: true
                    });
                }
                formItemsProp.push(tempProp);
                localParam.push(
                    {
                        [item.getPSAppDEField?.codeName?.toLowerCase()]: ['%' + item.getPSAppDEField?.codeName?.toLowerCase() + '%']
                    }
                );
                if(item.getPSEditor){
                    let editor: IBizEditorModel = new IBizEditorModel(item.getPSEditor, parentContainer.context);
                    await editor.loaded();
                    Object.assign(item, { editorInstance: editor });
                }
            }
        });
        return parentContainer.$createElement('comb-form-item', {
            props: {
                name: "srfarray",
                value: parentContainer.data.srfarray,
                formItems: formItemsProp
            },
            on: {
                'formitemvaluechange': parentContainer.onFormItemValueChange
            },
            scopedSlots: this.renderSolt(formItems, parentContainer, ctrlItemModel, localParam)
        })
    }

    /**
     * 绘制具名插槽
     * 
     * @param formItems 复合表单项集合
     * @param parentContainer 表单容器
     * @param ctrlItemModel 复合表单项模型
     * @param localParam 局部参数
     * @returns 插槽
     */
    public renderSolt(formItems: any[], parentContainer: any, ctrlItemModel: any, localParam: any){
        let scopedSlots: any = {}
        formItems.forEach((formItem: any) => {
            if(formItem.name != "srfarray" && formItem.userTag != "hidden" && formItem.getPSAppDEField){
                scopedSlots[formItem.getPSAppDEField.codeName.toLowerCase()] = (scope: any)=>{
                    return (
                        this.readerItem(formItem, formItems, parentContainer, ctrlItemModel, localParam, scope)
                    )
                }
            }
        })
        return scopedSlots;
    }

    /**
     * 绘制复合表单项
     * 
     * @param formItem 当前绘制项
     * @param formItems 复合表单项集合
     * @param parentContainer 表单容器
     * @param ctrlItemModel 复合表单项模型
     * @param localParam 局部参数
     * @param scope 插槽数据
     */
    public readerItem(formItem: any, formItems: any[], parentContainer: any, ctrlItemModel: any, localParam: any, scope: any){
        const { item } = scope;
        const branchs: any = formItems.find((_formItem: any) =>{ return Object.is('branchs', _formItem.name) });
        let h: any = parentContainer.$createElement;
        return h('div', 
            {
                style: "display: flex;width: 100%;"
            },
            [
                h('dropdown-list-dynamic',{
                    props: {
                        itemValue: item[formItem.getPSAppDEField.codeName.toLowerCase()],
                        data: {...parentContainer.data, ...item},
                        context: Util.deepCopy(parentContainer.context),
                        viewparams: parentContainer.viewparams,
                        localParam: localParam,
                        disabled: parentContainer.detailsModel[ctrlItemModel.name].disabled,
                        valueType: DataTypes.isNumber(DataTypes.toString(item.dataType)) ? 'number' : 'string',
                        tag: formItem.editorInstance?.codeList ? formItem.editorInstance.codeList.codeName : null,
                        codelistType: formItem.editorInstance?.codeList ? formItem.editorInstance.codeList.codeListType : null,
                        placeholder: ""
                    },
                    on: {
                        change: (value: any) =>{
                            item[formItem.getPSAppDEField.codeName.toLowerCase()] = value;
                        }
                    },
                    style: "flex-grow: 1;"
                }),
                Object.is('products', formItem.name) && branchs ?
                h('dropdown-list-dynamic',{
                    props: {
                        itemValue: item[branchs.getPSAppDEField.codeName.toLowerCase()],
                        data: {...parentContainer.data, ...item},
                        context: Util.deepCopy(parentContainer.context),
                        viewparams: parentContainer.viewparams,
                        localParam: localParam,
                        disabled: parentContainer.detailsModel[ctrlItemModel.name].disabled,
                        valueType: DataTypes.isNumber(DataTypes.toString(item.dataType)) ? 'number' : 'string',
                        tag: branchs.editorInstance?.codeList ? branchs.editorInstance.codeList.codeName : null,
                        codelistType: branchs.editorInstance?.codeList ? branchs.editorInstance.codeList.codeListType : null,
                        placeholder:"" 
                    },
                    on: {
                        change: (value: any) =>{
                            item[branchs.getPSAppDEField.codeName.toLowerCase()] = value;
                        }
                    },
                    style: "width: 100px;flex-shrink: 0;margin-left: 5px;"
                }) : null,
            ]
        )
    }

}
