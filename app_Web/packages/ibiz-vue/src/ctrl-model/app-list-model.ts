import { IBizListModel } from "ibiz-core";

/**
 * AppListModel 部件模型
 *
 * @export
 * @class AppListModel
 */
export class AppListModel {

    /**
    * 列表实例对象
    *
    * @memberof AppListModel
    */
    public ListInstance !: IBizListModel;

    /**
     * Creates an instance of AppListModel.
     * 
     * @param {*} [opts={}]
     * @memberof AppListModel
     */
    constructor(opts: any) {
        this.ListInstance = opts;
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
        
        if (this.ListInstance.allListDataItems) {
          this.ListInstance.allListDataItems.forEach((dataitem:any)=>{
            let obj:any = {};
            obj.name = dataitem.name.toLowerCase();
            // 附加属性
            if(dataitem.getPSAppDEField){
              let dataEntity: any = this.ListInstance.appDataEntity.getFieldByCodeName(dataitem.getPSAppDEField.codeName);
              obj.prop = dataitem.getPSAppDEField.codeName.toLowerCase();
              obj.dataType = dataEntity.dataType;

            }
            modelArray.push(obj);
          });
          // 附加界面主键
          const flag:boolean = this.ListInstance.allListDataItems.find((item:any) =>{
            return item.getPSAppDEField && (item.getPSAppDEField.codeName.toLowerCase() == this.ListInstance.appDataEntity.keyField.codeName.toLowerCase());
          })
          if(flag){
            modelArray.push({
              name: this.ListInstance.appDataEntity.codeName.toLowerCase(),
              prop: this.ListInstance.appDataEntity.keyField.codeName.toLowerCase(),
              dataType: 'FRONTKEY',
            })
          }
        }
        return modelArray;
    }

}