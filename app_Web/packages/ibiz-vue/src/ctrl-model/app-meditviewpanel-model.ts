import { IBizMEditViewPanelModel } from 'ibiz-core';

/**
 * AppKanbanModel 部件模型
 *
 * @export
 * @class AppKanbanModel
 */
export class AppMEditviewPanelModel {
    /**
     * 多编辑视图实例对象
     *
     * @memberof AppMEditviewPanelModel
     */
    public meditViewPanelInstance!: IBizMEditViewPanelModel;

    /**
     * Creates an instance of AppMEditviewPanelModel.
     *
     * @param {*} [opts={}]
     * @memberof AppMEditviewPanelModel
     */
    constructor(opts: any) {
        this.meditViewPanelInstance = opts;
    }

    /**
     * 获取数据项集合
     *
     * @returns {any[]}
     * @memberof AppMEditviewPanelModel
     */
    public getDataItems(): any[] {
        let modelArray: any[] = [];
        if (this.meditViewPanelInstance.appDataEntity) {
            const appDataEntity = this.meditViewPanelInstance.appDataEntity;
            if (appDataEntity.getAllPSAppDEFields) {
                appDataEntity.getAllPSAppDEFields.forEach((defield: any) => {
                    let obj: any = {};
                    if (defield.keyField) {
                        obj.name = appDataEntity.codeName.toLowerCase();
                        obj.prop = defield.codeName.toLowerCase();
                    } else {
                        obj.name = defield.codeName.toLowerCase();
                    }
                    modelArray.push(obj);
                });
            }
        }

        // 关联主实体的主键
        if (this.meditViewPanelInstance.appDataEntity) {
            const appDataEntity = this.meditViewPanelInstance.appDataEntity;
            if (appDataEntity.major == false && appDataEntity.getMinorPSAppDERSs) {
                appDataEntity.getMinorPSAppDERSs.forEach((minorAppDERSs: any) => {
                    let obj: any = {};
                    if (minorAppDERSs.getMajorPSAppDataEntity) {
                        const majorAppDataEntity = minorAppDERSs.getMajorPSAppDataEntity;
                        obj.name = majorAppDataEntity.codeName.toLowerCase();
                        if (majorAppDataEntity.getPSDER1N) {
                            obj.prop = majorAppDataEntity.getPSDER1N.getPSPickupDEField.codeName.toLowerCase();
                        }
                    }
                    modelArray.push(obj);
                });
            }
        }
        return modelArray;
    }
}
