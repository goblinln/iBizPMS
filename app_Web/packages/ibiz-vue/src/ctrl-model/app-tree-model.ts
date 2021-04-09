import { IBizTreeModel } from "ibiz-core";

/**
 *  树视图部件模型
 *
 * @export
 * @class AppTreeModel
 */
export class AppTreeModel {

    /**
    * 表单实例对象
    *
    * @memberof AppTreeModel
    */
    public TreeInstance !: IBizTreeModel;

    /**
     * Creates an instance of AppTreeModel.
     * 
     * @param {*} [opts={}]
     * @memberof AppTreeModel
     */
    constructor(opts: any) {
        this.TreeInstance = opts;
    }

    /**
    * 获取数据项集合
    *
    * @returns {any[]}
    * @memberof AppTreeModel
    */
    public getDataItems(): any[] {
        let modelArray:any[] = []
        const { appDataEntity } = this.TreeInstance;
        // 主实体所有属性
        if (appDataEntity?.getAllAppDeFields()?.length > 0) {
            appDataEntity.getAllAppDeFields().forEach((field: any) => {
                let obj: any = {};
                if (field.keyField) {
                    obj.name = appDataEntity.codeName.toLowerCase();
                    obj.prop = field.codeName.toLowerCase();
                } else {
                    obj.name = field.codeName.toLowerCase();
                }
                modelArray.push(obj);
            });
        }
      
        // 关联主实体的主键（todo 需要加载关系实体json数据）
        if (appDataEntity.major == false && appDataEntity.getMinorPSAppDERSs?.length > 0) {
            appDataEntity.getMinorPSAppDERSs.forEach((minorAppDERSs: any) => {
                if (minorAppDERSs.getMajorPSAppDataEntity) {
                    const majorAppDataEntity = minorAppDERSs.getMajorPSAppDataEntity;
                    let obj: any = {
                        name: majorAppDataEntity.codeName.toLowerCase(),
                        dataType: 'FRONTKEY',
                    };
                    if (majorAppDataEntity.getPSDER1N) {
                        obj.prop = majorAppDataEntity.getPSDER1N.getPSPickupDEField.codeName.toLowerCase();
                    }
                    modelArray.push(obj);
                }
            });
        }

        return modelArray;
    }

}