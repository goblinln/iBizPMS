

import { DataTypes } from "ibiz-core";

/**
 * 关联表单项复合绘制插件插件类
 *
 * @export
 * @class Associatedform
 * @extends {Vue}
 */
export class Associatedform {

renderCtrlItem(h: any, ctrlItemModel: any, parentContainer: any, data: any) {
        //todo  json缺少代码表 & json 没有 usertag 类型参数
        const { formItems } = ctrlItemModel;
        const formItemsProp: any[] = [];
        const localParam: any[] = [];
        const getItemLocaleTag = (field: any) => {
            if (field.getPSAppDataEntity && field.getPSAppDEField) {
                return `entities.${field?.getPSAppDataEntity()?.getCodeName()?.toLowerCase()}.fields.${field.getPSAppDEField()?.getCodeName()?.toLowerCase()}`
            }
        }
        formItems?.forEach((item: any) => {
            if (item.name != 'srfarray' && item.getPSAppDEField) {
                formItemsProp.push({
                    name: item.getPSAppDEField ? item.getPSAppDEField.codeName : item.id,
                    localetag: getItemLocaleTag(item),
                    prop: item?.getPSAppDEField?.codeName.toLowerCase(),
                    unique: item.unique ? true : false,
                    hidden: item.hidden ? true : false,
                })
                localParam.push(
                    {
                        [item.getPSAppDEField?.codeName?.toLowerCase()]: ['%' + item.getPSAppDEField?.codeName?.toLowerCase() + '%']
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
                'formitemvaluechange': parentContainer.onFormItemValueChange
            },
            scopedSlots: {
                default: () => {
                    return [
                        this.readerItem(formItems, parentContainer, ctrlItemModel, localParam),
                        this.readerItem2(formItems, parentContainer, ctrlItemModel, localParam)
                    ]

                }
            }
        })
    }

    /**
     * itme1项
     *
     * @param {*} formItems
     * @param {*} parentContainer
     * @param {*} ctrlItemModel
     * @param {*} localParam
     * @return {*} 
     * @memberof Associatedform
     */
    readerItem(formItems: any, parentContainer: any, ctrlItemModel: any, localParam: any) {
        return formItems.getPSDEFormItems.map((item: any) => {
            if (item.getName().toLowerCase() == "branchs" && item.getPSAppDEField()) {
                parentContainer.$createElement('dropdown-list-dynamic', {
                    props: {
                        'v-model': item.getPSAppDEField.codeName.toLowerCase(),
                        data: [...parentContainer.data, ...item],
                        viewparams: parentContainer.viewparams,
                        localParam: localParam,
                        disabled: parentContainer.detailsModel[ctrlItemModel.name].disabled,
                        valueType: DataTypes.isNumber(DataTypes.toString(item.dataType)) ? 'number' : 'string',
                        tag: item?.codelist?.codeName ? item.codelist.codeName : '',
                        codelistType: item?.codelist?.getCodeListType() ? item.codelist.getCodeListType() : null
                    }
                })
            }
        })
    }
    /**
     * itme2项
     *
     * @param {*} formItems
     * @param {*} parentContainer
     * @param {*} ctrlItemModel
     * @param {*} localParam
     * @return {*} 
     * @memberof Associatedform
     */
    public readerItem2(formItems: any, parentContainer: any, ctrlItemModel: any, localParam: any) {
        return formItems.getPSDEFormItems.map((item: any) => {
            if (item.getName().toLowerCase() == "branchs" && item.getPSAppDEField()) {
                return parentContainer.$createElement('div', {
                    props: { style: "display: flex;" },
                    scopedSlots: {
                        [item.getPSAppDEField.getCodeName().toLowerCase()]: (fromitem2: any) => {
                            return <dropdown-list-dynamic
                                v-model={fromitem2[item.getPSAppDEField.getCodeName().toLowerCase()]}
                                data={[...parentContainer.data, ...fromitem2]}
                                context={parentContainer.context}
                                viewparams={parentContainer.viewparams}
                                localParam={localParam}
                                disabled={parentContainer.detailsModel[ctrlItemModel].disabled}
                                valueType={DataTypes.isNumber(DataTypes.toString(item.dataType)) ? 'number' : 'string'}
                                tag={item?.codelist?.codeName ? item.codelist.codeName : ''}
                                codelistType={item?.codelist?.getCodeListType() ? item.codelist.getCodeListType() : null}
                                placeholder=""
                                style="flex-grow: 1;">
                            </dropdown-list-dynamic>

                        },
                        header: () => {
                            return <span class="column-header">{ctrlItemModel.caption}</span>
                        }
                    }
                })
            }
        })
    }


}
