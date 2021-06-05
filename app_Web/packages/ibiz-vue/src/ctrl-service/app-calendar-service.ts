import { ControlServiceBase, Util } from 'ibiz-core';
import { AppCalendarModel } from 'ibiz-vue';
import { GlobalService } from 'ibiz-service';
import { IPSAppDataEntity, IPSAppDEDataSet, IPSDECalendar, IPSSysCalendar, IPSSysCalendarItem } from '@ibiz/dynamic-model-api';

export class AppCalendarService extends ControlServiceBase {

    /**
    * 日历实例对象
    *
    * @memberof AppCalendarService
    */
   public controlInstance !: IPSDECalendar;

   /**
    * 数据服务对象
    *
    * @type {any}
    * @memberof AppCalendarService
    */
   public appEntityService!: any;

   private $itemEntityServiceMap: Map<string, any> = new Map();

   /**
    * 初始化服务参数
    *
    * @type {boolean}
    * @memberof AppCalendarService
    */
   public async initServiceParam(opts: any) {
       this.controlInstance = opts;
       this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
   }

   /**
    * Creates an instance of AppCalendarService.
    * 
    * @param {*} [opts={}]
    * @memberof AppCalendarService
    */
   constructor(opts: any = {}) {
       super(opts);
       this.model = new AppCalendarModel(opts);
       this.initServiceParam(opts);
       this.initEventsConfig();
   }


    /**
     * 事件配置集合
     *
     * @public
     * @type {any[]}
     * @memberof AppCalendarService
     */
    public eventsConfig: any[] = [];

    /**
     * 初始化事件配置集合
     * 
     * @memberof AppCalendarService
     */
    public initEventsConfig() {
        let tempArr: any[] = [];
        const calendarItems: Array<IPSSysCalendarItem> = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        if(calendarItems.length>0) {
            calendarItems.forEach((item: IPSSysCalendarItem) => {
                tempArr.push({
                    itemName: item.name,
                    itemType: item.itemType,
                    color: item.bKColor,
                    textColor: item.color
                });
            })
        }
        this.eventsConfig = [...tempArr];
    }

    public async initItemEntityService() {
        const calendarItems: Array<IPSSysCalendarItem> = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        if(calendarItems?.length>0) {
            for (const item of calendarItems) {
                const codeName = item.getPSAppDataEntity()?.codeName as string;
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
     * @memberof AppCalendarService
     */
    public async search(action: string, context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let _this = this;
        const calendarItems: Array<IPSSysCalendarItem> = (_this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        if(calendarItems.length != _this.$itemEntityServiceMap.size) {
            await this.initItemEntityService();
        }
        return new Promise((resolve: any, reject: any) => {
            let promises: any[] = [];
            if(calendarItems.length>0) {
                for(const item of calendarItems) {
                    const codeName = (item.getPSAppDataEntity() as IPSAppDataEntity)?.codeName || '' as string;
                    let service: any = _this.$itemEntityServiceMap.get(codeName);
                    let tempRequest: any = _this.handleRequestData(action, context, data, true, item.itemStyle || '' as string);
                    //TODO  需要codeName
                    const appDeDataSet: IPSAppDEDataSet = item.getPSAppDEDataSet() as IPSAppDEDataSet;
                    if(appDeDataSet.codeName && service[appDeDataSet.codeName]) {
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
                        _response.data[index].color = _this.eventsConfig[resIndex].color;
                        _response.data[index].textColor = _this.eventsConfig[resIndex].textColor;
                        _response.data[index].itemType = _this.eventsConfig[resIndex].itemType;
                        _response.data[index].curdata = Util.deepCopy(item);
                    });
                    _this.handleResponse(action, _response,false,_this.eventsConfig[resIndex]?.itemType);
                    _data.push(..._response.data);
                });
                // 排序
                // _data.sort((a:any, b:any)=>{
                //     let dateA = new Date(Date.parse(a.start.replace(/-/g, "/")));
                //     let dateB = new Date(Date.parse(b.start.replace(/-/g, "/")));
                //     return dateA > dateB ? 1 : -1 ;
                // });
                let result = {status: 200, data: _data};
                resolve(result);
            }).catch((response: any) => {
                reject(response);
            });  
        });
    }

    /**
     * 修改数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppCalendarService
     */
    public update(itemType: string, context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            let result: any;
            let tempRequest:any;
            const calendarItems: Array<IPSSysCalendarItem> = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
            const item: IPSSysCalendarItem = calendarItems.find((_item: IPSSysCalendarItem) => {return _item.itemType == itemType}) as IPSSysCalendarItem;
            if(item) {
                tempRequest = this.handleRequestData("", context, data, false, itemType);
                let codeName: any = item.getPSAppDataEntity()?.codeName;
                if(codeName && this.$itemEntityServiceMap.get(codeName)) {
                    let service: any = this.$itemEntityServiceMap.get(codeName);
                    result = service[codeName].Update(tempRequest.context, tempRequest.data, isloading);
                }     
            }
            if(result){
                result.then((response: any) => {
                    this.handleResponse("", response);
                    resolve(response);
                }).catch((response: any) => {
                    reject(response);
                });
            }else{
              reject("没有匹配的实体服务");
            }
        });
    }

    /**
     * 处理request请求数据
     * 
     * @param action 行为 
     * @param data 数据
     * @memberof AppCalendarService
     */
    public handleRequestData(action: string,context:any ={},data: any = {},isMerge:boolean = false,itemType:string=""){
        let model: AppCalendarModel = this.getMode();
        model.itemType = itemType;
        return super.handleRequestData(action,context,data,isMerge);
    }

    /**
     * 处理response返回数据
     *
     * @param {string} action
     * @param {*} response
     * @memberof AppCalendarService
     */
    public async handleResponse(action: string, response: any,isCreate:boolean = false,itemType:string=""){
        let model: AppCalendarModel = this.getMode();
        model.itemType = itemType;
        super.handleResponse(action,response,isCreate);
    }
}