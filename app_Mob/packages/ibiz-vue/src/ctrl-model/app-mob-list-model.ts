import { IPSAppDEMobMDView, IPSDEMobMDCtrl, IPSAppDEField, IPSDEListDataItem, IPSDEFormItem, IPSDESearchForm } from "@ibiz/dynamic-model-api";
import { DataTypes, ModelTool } from "ibiz-core";

/**
 * AppListModel 部件模型
 *
 * @export
 * @class AppListModel
 */
export class AppMobListModel {

    /**
    * 列表实例对象
    *
    * @memberof AppListModel
    */
    public MDCtrlInstance !: IPSDEMobMDCtrl;

    /**
     * Creates an instance of AppListModel.
     * 
     * @param {*} [opts={}]
     * @memberof AppListModel
     */
    constructor(opts: any) {
        this.MDCtrlInstance = opts;
    }

    /**
    * 获取数据项集合
    *
    * @returns {any[]}
    * @memberof AppListModel
    */
    public getDataItems(): any[] {
      let modelArray:any[] = [
        {
          name:'size',
          prop:'size'
        },
        {
          name:'query',
          prop:'query'
        },
        {
          name:'sort',
          prop:'sort'
        },
        {
          name:'page',
          prop:'page'
        },
        // 前端新增修改标识，新增为"0",修改为"1"或未设值
        {
          name: 'srffrontuf',
          prop: 'srffrontuf',
          dataType: 'TEXT',
        },
      ]
      if (this.MDCtrlInstance.getPSDEListDataItems()) {
        this.MDCtrlInstance.getPSDEListDataItems()?.forEach((dataitem:IPSDEListDataItem)=>{
          let obj:any = {};
          obj.name = dataitem.name.toLowerCase();
          // 附加属性
          if(dataitem.getPSAppDEField()){
            obj.prop = dataitem.getPSAppDEField()?.codeName.toLowerCase();
            obj.dataType = DataTypes.toString((dataitem.getPSAppDEField() as IPSAppDEField).stdDataType);
          }
          modelArray.push(obj);
        });
        // 附加界面主键
        const appDataEntity = this.MDCtrlInstance.getPSAppDataEntity();
        const keyField =  ModelTool.getAppEntityKeyField(appDataEntity);
        const flag = this.MDCtrlInstance.getPSDEListDataItems()?.find((item:IPSDEListDataItem) =>{
          return item.getPSAppDEField && (item.getPSAppDEField()?.codeName.toLowerCase() == keyField?.codeName.toLowerCase());
        })
        if(flag){
          modelArray.push({
            name: appDataEntity?.codeName.toLowerCase(),
            prop: keyField?.codeName.toLowerCase(),
            dataType: 'FRONTKEY',
          })
        }

        const parentView = this.MDCtrlInstance.getParentPSModelObject() as IPSAppDEMobMDView;
        if (parentView) {
          const searchForm = ModelTool.findPSControlByName('searchform', parentView.getPSControls() || []) as IPSDESearchForm;
          if (searchForm) {
            const formItems = searchForm.getPSDEFormItems();
            formItems?.forEach((formItem:IPSDEFormItem)=>{
                let temp: any = { name: formItem.id, prop: formItem.id };
                if(formItem.getPSAppDEField()){
                    temp.dataType = 'QUERYPARAM';
                }
                modelArray.push(temp);
            });
          }
        }
      }
      return modelArray;
    }

}