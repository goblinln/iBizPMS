import { IPSAppDEField, IPSDEList, IPSDEListDataItem, IPSListDataItem } from "@ibiz/dynamic-model-api";
import { DataTypes } from "ibiz-core";

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
    public ListInstance !: IPSDEList;

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
        const appDataEntity = this.ListInstance.getPSAppDataEntity();
        let modelArray: any[] = [
            {
                name: 'size',
                prop: 'size'
            },
            {
                name: 'query',
                prop: 'query'
            },
            {
                name: 'sort',
                prop: 'sort'
            },
            {
                name: 'page',
                prop: 'page'
            },
            // 前端新增修改标识，新增为"0",修改为"1"或未设值
            {
                name: 'srffrontuf',
                prop: 'srffrontuf',
                dataType: 'TEXT',
            },
        ]
        this.ListInstance.getPSDEListDataItems()?.forEach((item: IPSListDataItem) => {
            const dataitem: IPSDEListDataItem = item as IPSDEListDataItem;
            let tempModel: any = {};
            tempModel.name = dataitem.name.toLowerCase();
            if (dataitem?.getPSAppDEField && dataitem?.getPSAppDEField()) {
                tempModel.prop = dataitem.getPSAppDEField()?.codeName?.toLowerCase();
                tempModel.dataType = DataTypes.toString((dataitem.getPSAppDEField() as IPSAppDEField).stdDataType);
            }
            modelArray.push(tempModel);
            // 附加界面主键
            if (dataitem.getPSAppDEField && dataitem?.getPSAppDEField()?.keyField) {
                modelArray.push({
                    name: this.ListInstance.getPSAppDataEntity()?.codeName.toLowerCase(),
                    prop: dataitem.getPSAppDEField()?.codeName.toLowerCase(),
                    dataType: 'FRONTKEY',
                })
            }
        })
        // 关联主实体的主键 
        // if (!appDataEntity?.major && appDataEntity?.getMinorPSAppDERSs()) {
        //     for (let index = 0; index < (appDataEntity?.getMinorPSAppDERSs() as any).length; index++) {
        //         const minorAppDERSs: IPSAppDERS = (appDataEntity?.getMinorPSAppDERSs() as any)[index];
        //         if (minorAppDERSs.getMajorPSAppDataEntity()) {
        //             {
        //                 name: minorAppDERSs.getMajorPSAppDataEntity()?.codeName,
        //                     prop: minorAppDERSs.getMajorPSAppDataEntity()?.getPSDER1N() ? minorAppDERSs.getMajorPSAppDataEntity()?.getPSDER1N()?.getPSPickupDEField
        //       }
        //         }
        //     }
        // }
        return modelArray;
    }

}