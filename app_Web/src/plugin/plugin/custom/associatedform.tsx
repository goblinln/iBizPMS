

import { DataTypes, Util } from "ibiz-core";

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
     * @param detailsInstance 表单项实例对象
     * @param parentContainer 表单容器
     * @param data 数据
     * @returns 
     */
    public renderCtrlItem(h: any, detailsInstance: any, parentContainer: any, data: any) {
        let formItems = detailsInstance.getPSDEFormItems();
        const formItemsProp: any[] = [];
        const localParam: any[] = [];
        const getItemLocaleTag = (field: any) => {
            return `entities.${parentContainer.appDeCodeName.toLowerCase()}.fields.${field.getPSAppDEField().codeName.toLowerCase()}`;
        }
        formItems?.forEach(async (item: any) => {
            if (item.name != 'srfarray' && item.getPSAppDEField()) {
                let tempProp: any = {
                    name: item.caption,
                    localetag: getItemLocaleTag(item),
                    prop: item.getPSAppDEField().codeName.toLowerCase(),
                };
                if (item.userTag && (item.userTag == 'unique' || item.userTag == 'hidden')) {
                    Object.assign(tempProp, {
                        [item.userTag == 'unique' ? 'unique' : 'hidden']: true
                    });
                }
                formItemsProp.push(tempProp);
                localParam.push(
                    {
                        [item.getPSAppDEField().codeName?.toLowerCase()]: ['%' + item.getPSAppDEField().codeName?.toLowerCase() + '%']
                    }
                );
            }
        });
        return parentContainer.$createElement('comb-form-item', {
            props: {
                name: "srfarray",
                value: parentContainer.data.srfarray,
                formItems: formItemsProp
            },
            on: {
                'formitemvaluechange': parentContainer.onFormItemValueChange.bind(parentContainer)
            },
            scopedSlots: this.renderSolt(formItems, parentContainer, detailsInstance, localParam)
        })
    }

    /**
     * 绘制具名插槽
     * 
     * @param formItems 复合表单项集合
     * @param parentContainer 表单容器
     * @param detailsInstance 复合表单项实例对象
     * @param localParam 局部参数
     * @returns 插槽
     */
    public renderSolt(formItems: any[], parentContainer: any, detailsInstance: any, localParam: any){
        let scopedSlots: any = {}
        formItems.forEach((formItem: any) => {
            if(formItem.name != "srfarray" && formItem.userTag != "hidden" && formItem.getPSAppDEField()){
                scopedSlots[formItem.getPSAppDEField().codeName.toLowerCase()] = (scope: any)=>{
                    return (
                        this.readerItem(formItem, formItems, parentContainer, detailsInstance, localParam, scope)
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
     * @param detailsInstance 复合表单项实例对象
     * @param localParam 局部参数
     * @param scope 插槽数据
     */
    public readerItem(formItem: any, formItems: any[], parentContainer: any, detailsInstance: any, localParam: any, scope: any){
        const { item } = scope;
        const branchs: any = formItems.find((_formItem: any) =>{ return Object.is('branchs', _formItem.name) });
        const branchsCodeList = branchs?.getPSEditor()?.getPSAppCodeList?.();
        const codeList = formItem.getPSEditor()?.getPSAppCodeList?.();
        let h: any = parentContainer.$createElement;
        return h('div', 
            {
                style: "display: flex;width: 100%;"
            },
            [
                h('dropdown-list-dynamic',{
                    props: {
                        itemValue: item[formItem.getPSAppDEField().codeName.toLowerCase()],
                        data: {...parentContainer.data, ...item},
                        context: Util.deepCopy(parentContainer.context),
                        viewparams: parentContainer.viewparams,
                        localParam: localParam,
                        disabled: parentContainer.detailsModel[detailsInstance.name].disabled,
                        valueType: DataTypes.isNumber(DataTypes.toString(item.dataType)) ? 'number' : 'string',
                        tag: codeList ? codeList.codeName : null,
                        codelistType: codeList ? codeList.codeListType : null,
                        placeholder: ""
                    },
                    on: {
                        change: (value: any) =>{
                            item[formItem.getPSAppDEField().codeName.toLowerCase()] = value;
                        }
                    },
                    style: "flex-grow: 1;"
                }),
                Object.is('products', formItem.name) && branchs ?
                h('dropdown-list-dynamic',{
                    props: {
                        itemValue: item[branchs.getPSAppDEField().codeName.toLowerCase()],
                        data: {...parentContainer.data, ...item},
                        context: Util.deepCopy(parentContainer.context),
                        viewparams: parentContainer.viewparams,
                        localParam: localParam,
                        disabled: parentContainer.detailsModel[detailsInstance.name].disabled,
                        valueType: DataTypes.isNumber(DataTypes.toString(item.dataType)) ? 'number' : 'string',
                        tag: branchsCodeList ? branchsCodeList.codeName : null,
                        codelistType: branchsCodeList ? branchsCodeList.codeListType : null,
                        placeholder:"" 
                    },
                    on: {
                        change: (value: any) =>{
                            item[branchs.getPSAppDEField().codeName.toLowerCase()] = value;
                        }
                    },
                    style: "width: 100px;flex-shrink: 0;margin-left: 5px;"
                }) : null,
            ]
        )
    }

}
