import { IPSAppDERS, IPSSysMap, IPSSysMapItem } from "@ibiz/dynamic-model-api";
import { DataTypes, ModelTool } from "ibiz-core";

/**
 * AppMobMapModel 部件模型
 *
 * @export
 * @class AppMobMapModel
 */
export class AppMobMapModel {

    /**
    * 地图实例对象
    *
    * @memberof AppMobMapModel
    */
    public MapInstance !: IPSSysMap;

    /**
     * 地图项类型
     *
     * @returns {any[]}
     * @memberof AppMobMapModel
     */
    public itemType: string = "";

    /**
     * Creates an instance of AppMobMapModel.
     * 
     * @param {*} [opts={}]
     * @memberof AppMobMapModel
     */
    constructor(opts: any) {
        this.MapInstance = opts;
    }

    /**
    * 获取数据项集合
    *
    * @returns {any[]}
    * @memberof AppMobMapModel
    */
    public getDataItems(): any[] {
        let modelArray:any[] = [
          {
              name: 'itemType',
          },
        ]
        // 关联主实体的主键
        const appDataEntity = this.MapInstance.getPSAppDataEntity();
        if (appDataEntity?.major && appDataEntity.getMinorPSAppDERSs()) {
          const minorPSAppDERSs = appDataEntity.getMinorPSAppDERSs();
          minorPSAppDERSs?.forEach((minorAppDERS:IPSAppDERS) => {
            const majorAppDataEntity = minorAppDERS.getMajorPSAppDataEntity();
            const keyField =  ModelTool.getAppEntityKeyField(majorAppDataEntity);
            let tempArr: any[] = [{
              name : majorAppDataEntity?.codeName?.toLowerCase(),
              prop : keyField?.codeName.toLowerCase()
            }]
            modelArray.push(tempArr)
          });
        }
        if(!this.itemType) {
            return modelArray;
        }
        //地图项实体映射
        const mapItems = (this.MapInstance as IPSSysMap).getPSSysMapItems() || [];
        const item = mapItems?.find((_item: IPSSysMapItem) => {
            return _item.itemType == this.itemType;
        })
        if(item) {
            const longitude = item.getLongitudePSAppDEField(),
                  latitude = item.getLatitudePSAppDEField(),
                  color = item.getColorPSAppDEField(),
                  content = item.getContentPSAppDEField(),
                  group = item.getGroupPSAppDEField(),
                  icon = item.getIconPSAppDEField()
            let tempArr: any[] = [{
                name: 'longitude',
                prop: longitude?.codeName.toLowerCase()
            }, {
                name: 'latitude',
                prop: latitude?.codeName.toLowerCase()
            }, {
                name: 'color',
                prop: color?.codeName.toLowerCase()
            }, {
                name: 'content',
                prop: content?.codeName.toLowerCase()
            }, {
                name: 'group',
                prop: group?.codeName.toLowerCase()
            }, {
                name: 'icon',
                prop: icon?.codeName.toLowerCase()
            }];
            modelArray = [...modelArray, ...tempArr];
        }
        return modelArray;
      }    

}