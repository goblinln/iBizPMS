import { IPSDETree, IPSAppDataEntity, IPSAppDEField, IPSAppDERS } from '@ibiz/dynamic-model-api';


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
    public TreeInstance !: IPSDETree;

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
        const appDataEntity = this.TreeInstance.getPSAppDataEntity();
        // 主实体所有属性
        let allAppDeFields = appDataEntity?.getAllPSAppDEFields() || [];
        if (allAppDeFields.length > 0) {
            allAppDeFields.forEach((field: IPSAppDEField) => {
                let obj: any = {};
                if (field.keyField) {
                    obj.name = appDataEntity?.codeName.toLowerCase();
                    obj.prop = field.codeName.toLowerCase();
                } else {
                    obj.name = field.codeName.toLowerCase();
                }
                modelArray.push(obj);
            });
        }
      
        // 关联主实体的主键（todo 需要加载关系实体json数据）
        if (appDataEntity?.major == false) {
            let minorPSAppDERSs = appDataEntity.getMinorPSAppDERSs() || [];
            if (minorPSAppDERSs.length > 0) {
                minorPSAppDERSs.forEach((minorAppDERSs: IPSAppDERS) => {
                    if (minorAppDERSs.getMajorPSAppDataEntity()) {
                        const majorAppDataEntity = minorAppDERSs.getMajorPSAppDataEntity();
                        let obj: any = {
                            name: majorAppDataEntity?.codeName.toLowerCase(),
                            dataType: 'FRONTKEY',
                        };
                        if (majorAppDataEntity?.getPSDER1N()) {
                            obj.prop = majorAppDataEntity?.getPSDER1N()?.pickupDEFName.toLowerCase();
                        }
                        modelArray.push(obj);
                    }
                });
            }
        }

        return modelArray;
    }

}