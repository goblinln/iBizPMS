import { IBizKanbanModel } from 'ibiz-core';

/**
 * AppKanbanModel 部件模型
 *
 * @export
 * @class AppKanbanModel
 */
export class AppKanbanModel {
    /**
     * 列表实例对象
     *
     * @memberof AppKanbanModel
     */
    public KanbanInstance!: IBizKanbanModel;

    /**
     * Creates an instance of AppKanbanModel.
     *
     * @param {*} [opts={}]
     * @memberof AppKanbanModel
     */
    constructor(opts: any) {
        this.KanbanInstance = opts;
    }

    /**
     * 获取数据项集合
     *
     * @returns {any[]}
     * @memberof AppKanbanModel
     */
    public getDataItems(): any[] {
        let modelArray: any[] = [
            {
                name: 'size',
                prop: 'size',
            },
            {
                name: 'query',
                prop: 'query',
            },
            {
                name: 'sort',
                prop: 'sort',
            },
            {
                name: 'page',
                prop: 'page',
            },
            {
                name: 'srfparentdata',
                prop: 'srfparentdata',
            },
            // 前端新增修改标识，新增为"0",修改为"1"或未设值
            {
                name: 'srffrontuf',
                prop: 'srffrontuf',
                dataType: 'TEXT',
            },
        ];
        // 看板视图数据项
        if (this.KanbanInstance.getPSKanbanDataItems) {
            this.KanbanInstance.getPSKanbanDataItems.forEach((dataitem: any) => {
                let obj: any = {};
                switch (dataitem.name) {
                    case 'srfkey':
                        obj.name = 'srfkey';
                        obj.prop = this.KanbanInstance.appDataEntity.keyField.codeName.toLowerCase();
                        obj.dataType = this.KanbanInstance.appDataEntity.keyField.dataType;
                        break;
                    case 'srfmajortext':
                        obj.name = 'srfmajortext';
                        obj.prop = this.KanbanInstance.appDataEntity.majorField.codeName.toLowerCase();
                        obj.dataType = this.KanbanInstance.appDataEntity.majorField.dataType;
                        break;
                    default:
                        obj.name = dataitem.name.toLowerCase();
                        obj.prop = dataitem.getPSAppDEField ? dataitem.getPSAppDEField.codeName.toLowerCase() : dataitem.name.toLowerCase();
                        break;
                }
                modelArray.push(obj);
            });
        }

        // 关联主实体的主键
        if (this.KanbanInstance.getPSAppDataEntity) {
            const appDataEntity = this.KanbanInstance.getPSAppDataEntity;
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

        // 界面主键标识
        modelArray.push({
            name: this.KanbanInstance.appDataEntity.codeName.toLowerCase(),
            prop: this.KanbanInstance.appDataEntity.keyField.codeName.toLowerCase(),
            dataType: 'FRONTKEY',
        });
        return modelArray;
    }
}
