import { IPSAppDataEntity, IPSAppDEField, IPSSysMap, IPSSysMapItem } from "@ibiz/dynamic-model-api";
import { ModelTool } from "ibiz-core";

/**
 * AppMapModel 部件模型
 *
 * @export
 * @class AppMapModel
 */
export class AppMapModel {

    /**
    * 地图实例对象
    *
    * @memberof AppMapModel
    */
    public mapInstance !: IPSSysMap;

    /**
     * 地图项类型
     *
     * @returns {string}
     * @memberof AppGridModel
     */
    public itemType: string = '';

    /**
     * Creates an instance of AppMapModel.
     * 
     * @param {*} [opts={}]
     * @memberof AppMapModel
     */
    constructor(opts: any) {
        this.mapInstance = opts;
    }

    /**
    * 获取数据项集合
    *
    * @returns {any[]}
    * @memberof AppMapModel
    */
    public getDataItems(): any[] {
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
                name: 'itemType',
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
        //地图项实体映射
        const calendarItems: Array<IPSSysMapItem> = this.mapInstance.getPSSysMapItems() || [];
        const item: IPSSysMapItem = calendarItems?.find((_item: IPSSysMapItem) => {
            return _item.itemType == this.itemType;
        }) as IPSSysMapItem;
        if (item) {
            const entity = item.getPSAppDataEntity() as IPSAppDataEntity;
            const idField: IPSAppDEField = item.getIdPSAppDEField() as IPSAppDEField;
            const textField: IPSAppDEField = item.getTextPSAppDEField() as IPSAppDEField;
            const bKColorField: IPSAppDEField = item.getBKColorPSAppDEField() as IPSAppDEField;
            const colorField: IPSAppDEField = item.getColorPSAppDEField() as IPSAppDEField;
            const contentField: IPSAppDEField = item.getContentPSAppDEField() as IPSAppDEField;
            const longitudeField: IPSAppDEField = item.getLongitudePSAppDEField() as IPSAppDEField;
            const latitudeField: IPSAppDEField = item.getLatitudePSAppDEField() as IPSAppDEField;
            const iconField: IPSAppDEField = item.getIconPSAppDEField() as IPSAppDEField;
            const groupField: IPSAppDEField = item.getGroupPSAppDEField() as IPSAppDEField;
            const fontcolorField: IPSAppDEField = item.getColorPSAppDEField() as IPSAppDEField;
            const sortField: IPSAppDEField = item.getOrderValuePSAppDEField() as IPSAppDEField;
            let tempArr: any[] = [{
                name: entity?.codeName?.toLowerCase(),
                prop: idField?.codeName ? idField.codeName.toLowerCase()
                    : ((ModelTool.getAppEntityKeyField(entity) as IPSAppDEField)?.codeName || '').toLowerCase()
            }, {
                name: 'title',
                prop: textField?.codeName ? textField.codeName.toLowerCase()
                    : ((ModelTool.getAppEntityKeyField(entity) as IPSAppDEField)?.codeName || '').toLowerCase()
            }, {
                name: 'longitude',
                prop: longitudeField?.codeName ? longitudeField.codeName.toLowerCase() : longitudeField?.name?.toLowerCase()
            }, {
                name: 'latitude',
                prop: latitudeField?.codeName ? latitudeField.codeName.toLowerCase() : latitudeField?.name?.toLowerCase()
            }, {
                name: 'bkcolor',
                prop: bKColorField?.codeName ? bKColorField.codeName.toLowerCase() : bKColorField?.name?.toLowerCase()
            }, {
                name: 'icon',
                prop: iconField?.codeName ? iconField.codeName.toLowerCase() : iconField?.name?.toLowerCase()
            }, {
                name: 'group',
                prop: groupField?.codeName ? groupField.codeName.toLowerCase() : groupField?.name?.toLowerCase()
            }, {
                name: 'fontcolor',
                prop: fontcolorField?.codeName ? fontcolorField.codeName.toLowerCase() : fontcolorField?.name?.toLowerCase()
            }, {
                name: 'color',
                prop: colorField?.codeName ? colorField.codeName.toLowerCase() : colorField?.name?.toLowerCase()
            },{
                name: 'sort',
                prop: sortField?.codeName ? sortField.codeName.toLowerCase() : sortField?.name?.toLowerCase()
            }];
            if (contentField) {
                tempArr.push({
                    name: 'content',
                    prop: contentField?.codeName ? contentField.codeName.toLowerCase() : contentField?.name?.toLowerCase()
                })
            }
            modelArray = [...modelArray, ...tempArr];
        }
        return modelArray;
    }

}