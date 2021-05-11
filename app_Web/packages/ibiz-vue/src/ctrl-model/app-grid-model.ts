import { IPSAppDataEntity, IPSAppDEField, IPSAppDERS, IPSDEGrid, IPSDEGridDataItem, IPSDEGridEditItem, IPSEditor } from '@ibiz/dynamic-model-api';
import { DataTypes, ModelTool } from 'ibiz-core';

export class AppGridModel {
    
    /**
    * 表格实例对象
    *
    * @memberof AppGridModel
    */
    public gridInstance !: IPSDEGrid;

   /**
    * Creates an instance of AppGridModel.
    * 
    * @param {*} [opts={}]
    * @memberof AppGridModel
    */
    constructor(opts: any) {
        this.gridInstance = opts;
    }

   	/**
	 * TODO 是否是实体数据导出(暂时未使用)
	 *
	 * @returns {any}
	 * @memberof AppGridModel
	 */
    public isDEExport: boolean = false;

   /**
   * 获取数据项集合
   *
   * @returns {any[]}
   * @memberof AppGridModel
   */
    public getDataItems(): any[] {
        let modelArray:any[] = [
            {
                name:'size',
                prop:'size',
                dataType: 'QUERYPARAM'
              },
              {
                name:'query',
                prop:'query',
                dataType: 'QUERYPARAM'
              },
              {
                name:'page',
                prop:'page',
                dataType: 'QUERYPARAM'
              },
              {
                name:'sort',
                prop:'sort',
                dataType: 'QUERYPARAM'
              },
              {
                name:'srfparentdata',
                prop:'srfparentdata',
                dataType: 'QUERYPARAM'
              },
              // 前端新增修改标识，新增为"0",修改为"1"或未设值
              {
                name: 'srffrontuf',
                prop: 'srffrontuf',
                dataType: 'TEXT',
              },
              // 预置工作流数据字段
              {
                name:'srfprocessdefinitionkey',
                prop: 'srfprocessdefinitionkey',
                dataType: 'TEXT'
              },
              {
                name:'srftaskdefinitionkey',
                prop: 'srftaskdefinitionkey',
                dataType: 'TEXT'
              },
              {
                name:'srftaskid',
                prop: 'srftaskid',
                dataType: 'TEXT'
              }
        ]
        const appDataEntity: IPSAppDataEntity = this.gridInstance.getPSAppDataEntity() as IPSAppDataEntity;
        const allDataItems: Array<IPSDEGridDataItem> = this.gridInstance.getPSDEGridDataItems() || [];
        if(allDataItems.length>0) {
            allDataItems.forEach((item: IPSDEGridDataItem) => {
				let temp: any = {
					name: item.name.toLowerCase()
        };
        const field: IPSAppDEField | null = item.getPSAppDEField();
				if(field) {
					temp.prop = field.codeName?.toLowerCase();
					temp.dataType = DataTypes.toString(field.stdDataType);
				}
				const editItem: IPSDEGridEditItem = ModelTool.getGridItemByCodeName(item.name, this.gridInstance) as IPSDEGridEditItem;
				if(editItem) {
          //TODO 临时注释
          temp.isEditable = (editItem.getPSEditor() as IPSEditor).editable;
				}
				modelArray.push(temp);
      		})
		}
        //关联主实体的主键
        const minorAppDERSs: Array<IPSAppDERS> = appDataEntity?.getMinorPSAppDERSs() || [];
        if (appDataEntity && appDataEntity.major == false && minorAppDERSs.length > 0) {
          minorAppDERSs.forEach((minorAppDERSs: any) => {
            if (minorAppDERSs.getMajorPSAppDataEntity) {
              const majorAppDataEntity = minorAppDERSs.getMajorPSAppDataEntity;
              let obj: any = {
                  name: majorAppDataEntity.codeName?.toLowerCase(),
                  dataType: 'FRONTKEY',
              };
              if (majorAppDataEntity.getPSDER1N) {
                  obj.prop = majorAppDataEntity.getPSDER1N.getPSPickupDEField.codeName.toLowerCase();
              }
              modelArray.push(obj);
            }
          });
        }

        // 搜索表单属性
        // if(this.gridInstance.getView() && this.gridInstance.getView().getControl('searchform')) {
        //   const searchForm: IBizSearchFormModel = this.gridInstance.getView()?.getControl('searchform');
        //   searchForm.formItems?.forEach((formItem:IBizFormItemModel)=>{
        //       let temp: any = { name: formItem.name, prop: formItem.name };
        //       if(formItem.appDeField){
        //           temp.dataType = 'QUERYPARAM';
        //       }
        //       modelArray.push(temp);
        //   });
        // }

        // 界面主键标识
        const keyField: string = (ModelTool.getAppEntityKeyField(this.gridInstance?.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName || '';
        modelArray.push({
            name: appDataEntity?.codeName.toLowerCase(),
            prop: keyField.toLowerCase(),
    });
        return modelArray;
    }
}