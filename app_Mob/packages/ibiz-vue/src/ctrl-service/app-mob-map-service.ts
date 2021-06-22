import { ControlServiceBase } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppMobMapModel, Errorlog } from 'ibiz-vue';
import { IPSAppDERS, IPSSysMap, IPSSysMapItem } from "@ibiz/dynamic-model-api";

/**
 * 地图部件服务对象
 *
 * @export
 * @class AppMobMapService
 */
export class AppMobMapService extends ControlServiceBase {

    /**
    * 表单实例对象
    *
    * @memberof AppMobMapService
    */
    public controlInstance !: IPSSysMap;

    /**
     * 数据服务对象
     *
     * @type {any}
     * @memberof AppMobMapService
     */
    public appEntityService!: any;


    private $itemEntityServiceMap: Map<string, any> = new Map();

    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppMobMapService
     */
    public async initServiceParam(opts: any) {
        this.controlInstance = opts;
        this.appEntityService = await new GlobalService().getService(this.appDeCodeName);

    }

    /**
     * Creates an instance of AppMobMapService.
     * 
     * @param {*} [opts={}]
     * @memberof AppMobMapService
     */
    constructor(opts: any = {}) {
        super(opts);
        this.model = new AppMobMapModel(opts);
        this.initServiceParam(opts);
        this.initMapItemConfig();        
    }

    /**
     * 地图配置集合
     *
     * @protected
     * @type {any[]}
     * @memberof AppMobMapService
     */
     protected mapConfig: any = []

    /**
     * 初始化地图配置集合
     *
     * @protected
     * @type {any[]}
     * @memberof AppMobMapService
     */          
    public initMapItemConfig(){
        let tempArr: any[] = [];
        const mapItems = (this.controlInstance as IPSSysMap).getPSSysMapItems() || [];
        if(mapItems?.length>0) {
            mapItems.forEach((item: IPSSysMapItem) => {
                tempArr.push({
                    itemName: item.name,
                    itemType: item.itemType,
                    color: item.bKColor,
                    textColor: item.color
                });
            })
        }
        this.mapConfig = [...tempArr];
    }

    /**
     * 初始化地图项实体服务
     *
     * @protected
     * @type {any[]}
     * @memberof AppMobMapService
     */      
    public async initItemEntityService() {
        const mapItems = (this.controlInstance as IPSSysMap).getPSSysMapItems() || [];
        if(mapItems?.length>0) {
            for (const item of mapItems) {
                const codeName = item.getPSAppDataEntity()?.codeName;
                if(codeName) {
                    let service: any = await new GlobalService().getService(codeName);
                    this.$itemEntityServiceMap.set(codeName, service);
                }
            }
        }
    }

    /**
     * 查询数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppMobMapService
     */
    @Errorlog
    public async search(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        let _this = this;
        const mapItems = (_this.controlInstance as IPSSysMap).getPSSysMapItems() || [];
        if(mapItems.length != _this.$itemEntityServiceMap.size) {
            await this.initItemEntityService();
        }
        return new Promise((resolve: any, reject: any) => {
            let item: any = {};
            let promises: any[] = [];
            const items = (_this.controlInstance as IPSSysMap).getPSSysMapItems() || [];
            const appde = this.controlInstance.getPSAppDataEntity();
            if(items?.length>0) {
                for(let i: number = 0; i < items.length; i++) {
                    const { itemType } = items[i];
                    const appde = items[i].getPSAppDataEntity();
                    const appDeDataSet = items[i].getPSAppDEDataSet();
                    const codeName = appde?.codeName as string;
                    let service: any = _this.$itemEntityServiceMap.get(codeName);
                    let tempRequest: any = _this.handleRequestData(action, context, data, true, itemType);
                    //TODO  需要codeName
                    if(appDeDataSet?.codeName && service[appDeDataSet.codeName]) {
                        promises.push(service[appDeDataSet.codeName](tempRequest.context, tempRequest.data, isloading));
                    }
                }
            }
            Promise.all(promises).then((resArray: any) => {
                let _data:any = [];
                resArray.forEach((response:any,resIndex:number) => {
                    if (!response || response.status !== 200) {
                        return;
                    }
                    let _response: any = JSON.parse(JSON.stringify(response));
                    _response.data.forEach((item:any,index:number) =>{
                        _response.data[index].color = _this.mapConfig[resIndex].color;
                        _response.data[index].textColor = _this.mapConfig[resIndex].textColor;
                        _response.data[index].itemType = _this.mapConfig[resIndex].itemType;
                    });
                    _this.handleResponse(action, _response,false,_this.mapConfig[resIndex]?.itemType);
                    _data.push(..._response.data);
                });
                let result = {status: 200, data: _data};
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
     * @memberof ControlService
     */
    public handleRequestData(action: string,context:any ={},data: any = {},isMerge:boolean = false,itemType:string=""){
        let model: any = this.getMode();
        model.itemType = itemType;
        if (!model && model.getDataItems instanceof Function) {
            return data;
        }
        let dataItems: any[] = model.getDataItems();
        let requestData:any = {};
        if(isMerge && (data && data.viewparams)){
            Object.assign(requestData,data.viewparams);
        }
        dataItems.forEach((item:any) =>{
            if(item && item.dataType && Object.is(item.dataType,'FRONTKEY')){
                if(item && item.prop && item.name ){
                    requestData[item.prop] = context[item.name];
                }
            }else{
                if(item && item.prop){
                    requestData[item.prop] = data[item.name];
                }
            }
        });
        let tempContext:any = JSON.parse(JSON.stringify(context));
        if(tempContext && tempContext.srfsessionid){
            tempContext.srfsessionkey = tempContext.srfsessionid;
            delete tempContext.srfsessionid;
        }
        return {context:tempContext,data:requestData};
    }    

    /**
     * 处理response返回数据
     *
     * @param {string} action
     * @param {*} response
     * @memberof ControlService
     */
     public async handleResponse(action: string, response: any,isCreate:boolean = false,itemType:string=""){
        let model: AppMobMapModel = this.getMode();
        model.itemType = itemType;
        super.handleResponse(action,response,isCreate);
    }    

}