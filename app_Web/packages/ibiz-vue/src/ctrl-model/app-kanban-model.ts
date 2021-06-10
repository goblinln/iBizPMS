import { IPSAppDEField, IPSAppDERS, IPSDEDataViewDataItem, IPSDEKanban } from '@ibiz/dynamic-model-api';
import { DataTypes, ModelTool } from 'ibiz-core';

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
    public KanbanInstance!: IPSDEKanban;

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
        const appDeCodeName = this.KanbanInstance?.getPSAppDataEntity()?.codeName || ''
        this.KanbanInstance.getPSDEDataViewDataItems()?.forEach((dataitem: IPSDEDataViewDataItem) => {
            let obj: any = {};
            switch (dataitem.name) {
                case 'srfkey':
                    obj.name = 'srfkey';
                    obj.prop = ModelTool.getAppEntityKeyField(this.KanbanInstance.getPSAppDataEntity())?.codeName.toLowerCase();
                    obj.dataType = DataTypes.toString(ModelTool.getAppEntityKeyField(this.KanbanInstance.getPSAppDataEntity())?.stdDataType as number);
                    break;
                case 'srfmajortext':
                    obj.name = 'srfmajortext';
                    obj.prop = ModelTool.getAppEntityMajorField(this.KanbanInstance.getPSAppDataEntity())?.codeName.toLowerCase();
                    obj.dataType = DataTypes.toString(ModelTool.getAppEntityMajorField(this.KanbanInstance.getPSAppDataEntity())?.stdDataType as number);
                    break;
                default:
                    obj.name = dataitem.name.toLowerCase();
                    obj.prop = dataitem.getPSAppDEField ? dataitem.getPSAppDEField()?.codeName.toLowerCase() : dataitem.name.toLowerCase();
                    break;
            }
            modelArray.push(obj);
        });

        // 关联主实体的主键
        if (this.KanbanInstance.getPSAppDataEntity) {
            const appDataEntity: any = this.KanbanInstance.getPSAppDataEntity();
            if (appDataEntity?.major == false && appDataEntity.getMinorPSAppDERSs()) {
                appDataEntity.getMinorPSAppDERSs()?.forEach((minorAppDERSs: IPSAppDERS) => {
                    let obj: any = {};
                    if (minorAppDERSs.getMajorPSAppDataEntity()) {
                        const majorAppDataEntity: any = minorAppDERSs.getMajorPSAppDataEntity();
                        if (majorAppDataEntity) {
                            obj.name = majorAppDataEntity?.codeName?.toLowerCase();
                            if (minorAppDERSs?.getParentPSAppDEField()) {
                              obj.prop = minorAppDERSs.getParentPSAppDEField()?.codeName.toLowerCase();
                            } else {
                              obj.prop = (ModelTool.getAppEntityKeyField(majorAppDataEntity) as IPSAppDEField)?.codeName || '';
                            }
                        }                   
                    }
                    modelArray.push(obj);
                });
            }
        }

        // 界面主键标识
        modelArray.push({
            name: this.KanbanInstance.getPSAppDataEntity()?.codeName.toLowerCase(),
            prop: ModelTool.getAppEntityKeyField(this.KanbanInstance.getPSAppDataEntity())?.codeName.toLowerCase(),
            dataType: 'FRONTKEY',
        });
        return modelArray;
    }
}
