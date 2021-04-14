import { IBizGridModel, DataTypes, IBizSearchFormModel, IBizFormItemModel } from 'ibiz-core';

export class AppGridModel {
    
    /**
    * 表格实例对象
    *
    * @memberof AppGridModel
    */
    public gridInstance !: IBizGridModel;

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
                name:'filter',
                prop:'filter',
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
        const { allDataItems, appDataEntity } = this.gridInstance;
        if(allDataItems?.length>0) {
            allDataItems.forEach((item: any) => {
				let temp: any = {
					name: item.name.toLowerCase()
				};
				if(item.getPSAppDEField) {
					temp.prop = item.getPSAppDEField.codeName.toLowerCase();
					temp.dataType = DataTypes.toString(item.dataType);
				}
				const editItem = this.gridInstance.getEditColumnByName(item.name.toLowerCase());
				if(editItem && editItem.getPSEditor) {
          //TODO 临时注释
          // temp.isEditable = editItem.getPSEditor.editable;
          temp.isEditable = true;
				}
				modelArray.push(temp);
      		})
		}
        //关联主实体的主键
        if (appDataEntity.major == false && appDataEntity.getMinorPSAppDERSs?.length > 0) {
          appDataEntity.getMinorPSAppDERSs.forEach((minorAppDERSs: any) => {
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
        if(this.gridInstance.getView() && this.gridInstance.getView().getControl('searchform')) {
          const searchForm: IBizSearchFormModel = this.gridInstance.getView()?.getControl('searchform');
          searchForm.formItems?.forEach((formItem:IBizFormItemModel)=>{
              let temp: any = { name: formItem.name, prop: formItem.name };
              if(formItem.appDeField){
                  temp.dataType = 'QUERYPARAM';
              }
              modelArray.push(temp);
          });
        }
        // TODO 搜索表单的关联主实体和界面主键。

        // 界面主键标识
        modelArray.push({
            name: this.gridInstance.appDataEntity.codeName.toLowerCase(),
            prop: this.gridInstance.appDataEntity.keyField.codeName.toLowerCase(),
		});
        return modelArray;
    }
}