import { IBizDataViewModel } from 'ibiz-core'
export class AppDataViewModel {

    /**
     * 数据视图实例对象
     * 
     * @memberof AppDataViewModel
     */
    public $DataViewInstance: IBizDataViewModel;

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
        const { appDataEntity, dataViewItems } = this.$DataViewInstance;
        //数据项
        if(dataViewItems?.length>0){
          dataViewItems.forEach((dataItem: any)=>{
                let temp: any = {name: dataItem.name.toLowerCase()};
                temp.prop = dataItem.getPSAppDEField?.codeName.toLowerCase();
                temp.dataType = dataItem.getPSAppDEField?.dataType;
                modelArray.push(temp);
            })
        }
        //TODO   关联主实体的主键

        // 界面主键标识
        modelArray.push({
            name: appDataEntity.codeName.toLowerCase(),
            prop: appDataEntity.keyField.codeName.toLowerCase(),
            dataType: 'FRONTKEY',
        })
        return modelArray;
    }
}