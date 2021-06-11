import { IPSDEDataView, IPSAppDataEntity, IPSAppDEField, IPSDESearchForm, IPSDEFormItem, IPSAppDEDataView } from '@ibiz/dynamic-model-api';
import { ModelTool } from 'ibiz-core';
export class AppDataViewModel {

    /**
     * 数据视图实例对象
     * 
     * @memberof AppDataViewModel
     */
    public $DataViewInstance: IPSDEDataView;

    /**
    * Creates an instance of AppGridModel.
    * 
    * @param {*} [opts={}]
    * @memberof AppDataViewModel
    */
    constructor(opts: any) {
        this.$DataViewInstance = opts;
    }

    /**
	 * TODO 是否是实体数据导出(暂时未使用)
	 *
	 * @returns {any}
	 * @memberof AppDataViewModel
	 */
   public isDEExport: boolean = false;

    /**
	 * 获取数据项集合
	 *
	 * @returns {any[]}
	 * @memberof AppDataViewModel
	 */
    public getDataItems(): any[]  {
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
              {
                name:'srfparentdata',
                prop:'srfparentdata'
              },
            {
                name: 'srfwfmemo',
                prop: 'srfwfmemo',
                dataType: 'TEXT',
            }
        ];
        const appDataEntity = this.$DataViewInstance.getPSAppDataEntity();
        const dataViewItems = this.$DataViewInstance.getPSDEDataViewDataItems() || [];
        //数据项
        if(dataViewItems?.length>0){
          dataViewItems.forEach((dataItem: any)=>{
                let temp: any = {name: dataItem.name.toLowerCase()};
                temp.prop = dataItem.getPSAppDEField()?.codeName.toLowerCase();
                temp.dataType = dataItem.getPSAppDEField()?.dataType;
                modelArray.push(temp);
            })
        }
        //TODO   关联主实体的主键

        const searchFormInstance: IPSDESearchForm = ModelTool.findPSControlByType("SEARCHFORM", (this.$DataViewInstance.getParentPSModelObject() as IPSAppDEDataView).getPSControls() || []);
        if(searchFormInstance) {
          (searchFormInstance.getPSDEFormItems?.() || []).forEach((formItem: IPSDEFormItem)=>{
              let temp: any = { name: formItem.id, prop: formItem.id };
              if(formItem.getPSAppDEField?.()){
                  temp.dataType = 'QUERYPARAM';
              }
              modelArray.push(temp);
          });
        }
        // 界面主键标识
        modelArray.push({
            name: (appDataEntity as IPSAppDataEntity).codeName.toLowerCase(),
            prop: (ModelTool.getAppEntityKeyField(appDataEntity as IPSAppDataEntity) as IPSAppDEField)?.codeName,
            dataType: 'FRONTKEY',
        })
        return modelArray;
    }
}