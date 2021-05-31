

import { DataTypes } from "ibiz-core";
import { IPSDEFormItemEx, IPSDEFormItem } from '@ibiz/dynamic-model-api';

/**
 * 移动端复合表单插件插件类
 *
 * @export
 * @class MobCompoundList2
 * @extends {Vue}
 */
export class MobCompoundList2 {


    // 替换当前作用域$set函数为vue.$set
    public $set(parentContainer:any, target:any, propertyName:any, value:any){
      return parentContainer.$set( target, propertyName, value );
    }    

    // 绘制插件
    public renderCtrlItem(h: any, ctrlItemModel: IPSDEFormItemEx, parentContainer: any, data: any) {
        const formItems = ctrlItemModel.getPSDEFormItems() as IPSDEFormItem[];
        const formItemsProp: any[] = [];
        const localParam: any = {products: '%products%',srfarray: '%srfarray%',branchs: '%branchs%',plans: '%plans%',};
        const getItemLocaleTag = (field: IPSDEFormItem) => {
            if (field.getPSAppDEField()) {
                return `project.fields.${field.getPSAppDEField()?.codeName?.toLowerCase()}`
            }
        }
        //todo  json缺少代码表
        formItems?.forEach((item: IPSDEFormItem) => {
            if (item.name != 'srfarray' && item.getPSAppDEField()) {
                formItemsProp.push({
                    name: item.getPSAppDEField() ? item.getPSAppDEField()?.codeName : item.name,
                    localetag: getItemLocaleTag(item),
                    prop: item?.getPSAppDEField()?.codeName.toLowerCase(),
                    unique: item.userTag == "unique" ? true : false,
                    hidden: item.userTag == "hidden" ? true : false,
                });
            }
        });
        
        // 绘制
        return parentContainer.$createElement('comb-form-item', {
            props: {
                name: "srfarray",
                value: parentContainer.data.srfarray,
                formItems: formItemsProp
            },
            on: {
                'formitemvaluechange': parentContainer.onFormItemValueChange.bind(parentContainer)
            },
            scopedSlots: {
                'products': (formitem:any) => {
                    return [
                        this.renderItem(formItems, parentContainer, ctrlItemModel, localParam, formitem),
                    ]
                },
                'plans': (formitem:any) => {
                    return [
                        this.renderItem2(formItems, parentContainer, ctrlItemModel, localParam, formitem),
                    ]
                }

            }
        })
    }


    public renderItem(formItems: IPSDEFormItem[], parentContainer: any, ctrlItemModel: any, localParam: any, item2:any){
        const {item:formitem} = item2;

        return formItems.map((item: IPSDEFormItem) => {
            if (item.name.toLowerCase() != "srfarray" && item.getPSAppDEField()) {
                return [
                  item.name.toLowerCase() == "products" ? 
                  parentContainer.$createElement("app-form-item", {
                    "props": {
                      "name": item.getPSAppDEField()?.codeName.toLowerCase(),
                      "labelPos": "LEFT",
                      "uiStyle": "DEFAULT",
                      "isShowCaption": "false"
                    }
                  }, [parentContainer.$createElement("app-mob-select-dynamic", {
                    "props": {
                      "data": Object.assign(parentContainer.data, formitem),
                      "context": parentContainer.context,
                      "viewparams": parentContainer.viewparams,
                      "navigateParam": localParam,
                      "disabled": parentContainer.detailsModel[ctrlItemModel]?.disabled,
                      "valueType": DataTypes.isNumber(DataTypes.toString(item.dataType)) ? 'number' : 'string',
                      "tag" : 'Product',
                      "codelistType": 'DYNAMIC',
                      "placeholder": ""
                    },
                    "on": {
                      "change": (val:any) => {
                        formitem[item.getPSAppDEField()?.codeName.toLowerCase() as string] = val;
                      }
                    },
                    "model": {
                      value: formitem[item.getPSAppDEField()?.codeName.toLowerCase() as string],
                      callback: ($$v:any) => {
                        this.$set(parentContainer, formitem, item.getPSAppDEField()?.codeName.toLowerCase(), $$v);
                      }
                    }
                  })]) : null, 
                  item.name.toLowerCase() == "branchs" ? 
                  parentContainer.$createElement("app-form-item", {
                    "props": {
                      "name": item.getPSAppDEField()?.codeName.toLowerCase(),
                      "labelPos": "LEFT",
                      "uiStyle": "DEFAULT",
                      "isShowCaption": "false"
                    }
                  }, [parentContainer.$createElement("app-mob-select-dynamic", {
                    "props": {
                      "data": Object.assign(parentContainer.data, formitem),
                      "context": parentContainer.context,
                      "viewparams": parentContainer.viewparams,
                      "navigateParam": localParam,
                      "disabled": parentContainer.detailsModel[ctrlItemModel]?.disabled,
                      "valueType": DataTypes.isNumber(DataTypes.toString(item.dataType)) ? 'number' : 'string',
                      "tag" : 'ProductBranch',
                      "codelistType": 'DYNAMIC',
                      "placeholder": ""
                    },
                    "on": {
                      "change": (val:any) => {
                        formitem[item.getPSAppDEField()?.codeName.toLowerCase() as string] = val;
                      }
                    },
                    "model": {
                      value: formitem[item.getPSAppDEField()?.codeName.toLowerCase() as string],
                      callback: (val:any) => {
                        this.$set(parentContainer,formitem, item.getPSAppDEField()?.codeName.toLowerCase(), val);
                      }
                    }
                  })]) : null
                ]
               
            }
        })
    }

    public renderItem2(formItems:IPSDEFormItem[], parentContainer: any, ctrlItemModel: any, localParam: any, item2:any){
      const {item:formitem} = item2;
      
      return formItems.map((item: IPSDEFormItem) => {
        if (item.name.toLowerCase() == "plans" && item.getPSAppDEField()) {
          return parentContainer.$createElement("app-form-item", {
            "props": {
              "name": item.getPSAppDEField()?.codeName.toLowerCase(),
              "labelPos": "LEFT",
              "uiStyle": "DEFAULT",
              "labelWidth": 130,
              "isShowCaption": "false"
            }
          }, [parentContainer.$createElement("app-mob-select-dynamic", {
            "props": {
              "data": Object.assign(parentContainer.data, formitem),
              "context": parentContainer.context,
              "viewparams": parentContainer.viewparams,
              "navigateParam": localParam,
              "disabled": parentContainer.detailsModel[ctrlItemModel]?.disabled,
              "valueType": DataTypes.isNumber(DataTypes.toString(item.dataType)) ? 'number' : 'string',
              "tag" : 'ProductPlan',
              "codelistType": 'DYNAMIC',
              "placeholder": ""
            },
            "on": {
              "change": (val:any) => {
                formitem[item.getPSAppDEField()?.codeName.toLowerCase() as string] = val;
              }
            },
            "model": {
              value: formitem[item.getPSAppDEField()?.codeName.toLowerCase() as string],
              callback: (val:any) => {
                this.$set(parentContainer,formitem, item.getPSAppDEField()?.codeName.toLowerCase(), val);
              }
            }
          })]);
        }
      })
    }

}
