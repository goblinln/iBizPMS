import { IBizWizardPanelModel } from 'ibiz-core';

export class AppWizardPanelModel {

    /**
     * 向导面板模型实例
     * 
     * @memberof AppWizardPanelModel
     */
    public controlInstance!: IBizWizardPanelModel;

    /**
     * Creates an instance of AppWizardPanelModel.
     * 
     * @param {*} [opts={}]
     * @memberof AppWizardPanelModel
     */
    constructor(opts: any) {
        this.controlInstance = opts;
    }

    /**
    * 获取数据项集合
    *
    * @returns {any[]}
    * @memberof AppWizardPanelModel
    */
    public getDataItems(): any[] {
        let modelArray: Array<any> = [];
        let appDataEntity: any = this.controlInstance.appDataEntity;
        if (appDataEntity && appDataEntity.getAllPSAppDEFields?.length > 0) {
            appDataEntity.getAllPSAppDEFields.forEach((field: any) => {
                modelArray.push({
                    name: field.keyField ? appDataEntity.codeName.toLowerCase() : field.codeName.toLowerCase(),
                    prop: field.codeName.toLowerCase()
                })
            })
        }
        return modelArray;
    }
}