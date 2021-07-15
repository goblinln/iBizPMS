import { IPSAppDataEntity, IPSAppDEDataSet, IPSSysMap, IPSSysMapItem } from '@ibiz/dynamic-model-api';
import { ControlServiceBase } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppMapModel } from 'ibiz-vue';

/**
 * 地图部件服务对象
 *
 * @export
 * @class AppMapService
 */
export class AppMapService extends ControlServiceBase {

    /**
    * 表单实例对象
    *
    * @memberof AppMapService
    */
    public controlInstance !: IPSSysMap;

    /**
     * 地图配置集合
     *
     * @type {*}
     * @memberof AppMapService
     */
    public mapConfig: any[] = [];

    /**
     * 数据服务对象
     *
     * @type {any}
     * @memberof AppMapService
     */
    public appEntityService!: any;

    /**
     * 地图项服务集合
     *
     * @type {boolean}
     * @memberof AppMapService
     */
    private $itemEntityServiceMap: Map<string, any> = new Map();

    /**
     * 初始化地图项服务集合
     *
     * @type {boolean}
     * @memberof AppMapService
     */
    public async initItemEntityService() {
        const calendarItems: Array<IPSSysMapItem> = this.controlInstance.getPSSysMapItems() || [];
        if (calendarItems?.length > 0) {
            for (const item of calendarItems) {
                const codeName = item.getPSAppDataEntity()?.codeName as string;
                if (codeName) {
                    let service: any = await new GlobalService().getService(codeName);
                    this.$itemEntityServiceMap.set(codeName, service);
                }
            }
        }
    }

    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppMapService
     */
    public async initServiceParam(opts: any) {
        this.controlInstance = opts;
        this.appEntityService = await new GlobalService().getService(this.appDeCodeName, this.context);
        this.model = new AppMapModel(opts);
        this.initMapConfig();
    }

    /**
     * 初始化Map参数
     *
     * @memberof AppMapService
     */
    public initMapConfig() {
        const mapItems: IPSSysMapItem[] | null = this.controlInstance.getPSSysMapItems();
        if (mapItems) {
            mapItems.forEach((item: IPSSysMapItem, index: number) => {
                this.mapConfig.push({
                    itemName: item.name,
                    itemType: item.itemType,
                    color: item.bKColor,
                    textColor: item.color,
                });
            });
        }
    }

    /**
     * Creates an instance of AppMapService.
     * 
     * @param {*} [opts={}]
     * @memberof AppMapService
     */
    constructor(opts: any = {}, context?: any) {
        super(opts, context);
    }

    /**
     * async loaded
     */
    public async loaded(opts: any) {
        await this.initServiceParam(opts);
    }

    /**
     * 查询数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppMapService
     */
    public async search(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const _this: any = this;
        const mapItems: IPSSysMapItem[] | null = this.controlInstance.getPSSysMapItems();
        if (mapItems?.length != _this.$itemEntityServiceMap.size) {
            await this.initItemEntityService();
        }
        return new Promise((resolve: any, reject: any) => {
            let promises: any = [];
            if (mapItems) {
                for (const item of mapItems) {
                    const codeName = (item.getPSAppDataEntity() as IPSAppDataEntity)?.codeName || '' as string;
                    let service: any = _this.$itemEntityServiceMap.get(codeName);
                    let tempRequest: any = _this.handleRequestData(action, context, data, true, item.itemType || '' as string);
                    const appDeDataSet: IPSAppDEDataSet = item.getPSAppDEDataSet() as IPSAppDEDataSet;
                    if (appDeDataSet.codeName && service[appDeDataSet.codeName]) {
                        promises.push(service[appDeDataSet.codeName](tempRequest.context, tempRequest.data, isloading));
                    }
                }
            }
            Promise.all(promises).then((resArray: any) => {
                let _data: any = [];
                resArray.forEach((response: any, resIndex: number) => {
                    if (!response || response.status !== 200) {
                        return;
                    }
                    let _response: any = JSON.parse(JSON.stringify(response));
                    _response.data.forEach((item: any, index: number) => {
                        _response.data[index].color = _this.mapConfig[resIndex].color;
                        _response.data[index].textColor = _this.mapConfig[resIndex].textColor;
                        _response.data[index].itemType = _this.mapConfig[resIndex].itemType;
                    });
                    _this.handleResponse(action, _response, false, _this.mapConfig[resIndex].itemType);
                    _data.push(..._response.data);
                });
                let result = { status: 200, data: _data };
                resolve(result);
            }).catch((response: any) => {
                reject(response);
            });
        });
    }

    /**
     * 处理request请求数据
     * 
     * @param action 行为 
     * @param data 数据
     * @memberof AppMapService
     */
    public handleRequestData(action: string, context: any = {}, data: any = {}, isMerge: boolean = false, itemType: string = "") {
        let model: any = this.getMode();
        model.itemType = itemType;
        return super.handleRequestData(action, context, data, isMerge);
    }

    /**
     * 处理response返回数据
     *
     * @param {string} action
     * @param {*} response
     * @memberof AppMapService
     */
    public async handleResponse(action: string, response: any, isCreate: boolean = false, itemType: string = "") {
        let model: any = this.getMode();
        model.itemType = itemType;
        super.handleResponse(action, response, isCreate);
    }
}