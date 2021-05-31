import { IPSAppDERS, IPSDEMultiEditViewPanel } from '@ibiz/dynamic-model-api';

/**
 * AppMobMEditviewPanelModel 部件模型
 *
 * @export
 * @class AppMobMEditviewPanelModel
 */
export class AppMobMEditviewPanelModel {
    /**
     * 多编辑视图实例对象
     *
     * @memberof AppMEditviewPanelModel
     */
    public meditViewPanelInstance!: IPSDEMultiEditViewPanel;

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
        const appDataEntity = this.meditViewPanelInstance.getPSAppDataEntity();
        if (this.meditViewPanelInstance.getPSAppDataEntity()) {
            if (appDataEntity?.getAllPSAppDEFields()) {
                appDataEntity.getAllPSAppDEFields()?.forEach((defield: any) => {
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
        const minorAppDERSs: Array<IPSAppDERS> = appDataEntity?.getMinorPSAppDERSs() || [];
        if (appDataEntity && appDataEntity.major == false && minorAppDERSs.length > 0) {
            minorAppDERSs.forEach((minorAppDERSs: any) => {
                if (minorAppDERSs.getMajorPSAppDataEntity) {
                    const majorAppDataEntity = minorAppDERSs.getMajorPSAppDataEntity;
                    let obj: any = {
                        name: majorAppDataEntity.codeName?.toLowerCase(),
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
