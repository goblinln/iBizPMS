import { ControlServiceBase, Util } from 'ibiz-core';
import { AppMobCalendarModel, Errorlog } from 'ibiz-vue';
import { GlobalService } from 'ibiz-service';
import { IPSDECalendar, IPSSysCalendar, IPSSysCalendarItem } from '@ibiz/dynamic-model-api';

export class AppMobCalendarService extends ControlServiceBase {

    /**
    * 日历实例对象
    *
    * @memberof AppMobCalendarService
    */
   public controlInstance !: IPSDECalendar;

   /**
    * 数据服务对象
    *
    * @type {any}
    * @memberof AppMobCalendarService
    */
   public appEntityService!: any;

   private $itemEntityServiceMap: Map<string, any> = new Map();

   /**
    * 远端数据
    *
    * @type {*}
    * @memberof AppMobCalendarService
    */
   private remoteCopyData: any = {};

   /**
    * 初始化服务参数
    *
    * @type {boolean}
    * @memberof AppMobCalendarService
    */
   public async initServiceParam(opts: any) {
       this.controlInstance = opts;
       this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
   }

   /**
    * Creates an instance of AppMobFormService.
    * 
    * @param {*} [opts={}]
    * @memberof AppMobCalendarService
    */
   constructor(opts: any = {}) {
       super(opts);
       this.model = new AppMobCalendarModel(opts);
       this.initServiceParam(opts);
       this.initEventsConfig();
   }


    /**
     * 事件配置集合
     *
     * @public
     * @type {any[]}
     * @memberof AppMobCalendarService
     */
    public eventsConfig: any[] = [];

    /**
     * 初始化事件配置集合
     * 
     * @memberof AppMobCalendarService
     */
    public initEventsConfig() {
        let tempArr: any[] = [];
        const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        if(calendarItems?.length>0) {
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
        const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        if(calendarItems?.length>0) {
            for (const item of calendarItems) {
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
     * @memberof AppMobCalendarService
     */
    @Errorlog
    public async search(action: string, context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let _this = this;
        const calendarItems = (_this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
        if(calendarItems.length != _this.$itemEntityServiceMap.size) {
            await this.initItemEntityService();
        }
        return new Promise((resolve: any, reject: any) => {
            let item: any = {};
            let promises: any[] = [];
            const items = (_this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
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
                        _response.data[index].color = _this.eventsConfig[resIndex].color;
                        _response.data[index].textColor = _this.eventsConfig[resIndex].textColor;
                        _response.data[index].itemType = _this.eventsConfig[resIndex].itemType;
                    });
                    _this.handleResponse(action, _response,false,_this.eventsConfig[resIndex]?.itemType);
                    let itemType = items[resIndex].itemType.toLowerCase();
                    Object.assign(item, { [itemType]: _response.data });
                });
                // 排序
                // _data.sort((a:any, b:any)=>{
                //     let dateA = new Date(Date.parse(a.start.replace(/-/g, "/")));
                //     let dateB = new Date(Date.parse(b.start.replace(/-/g, "/")));
                //     return dateA > dateB ? 1 : -1 ;
                // });
                let result = {status: 200, data: item};
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
     * @memberof AppMobCalendarService
     */
    @Errorlog
    public update(itemType: string, context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            let result: any;
            let tempRequest:any;
            const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
            const item = calendarItems.find((_item: IPSSysCalendarItem) => {return _item.itemType == itemType});
            if(item) {
                tempRequest = this.handleRequestData("", context, data, false, itemType);
                let codeName: any = item.getPSAppDataEntity()?.codeName;
                if(codeName && this.$itemEntityServiceMap.get(codeName)) {
                    let service: any = this.$itemEntityServiceMap.get(codeName);
                    result = service.Update(tempRequest.context, tempRequest.data, isloading);
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
     * 删除数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isLoading]
     * @returns {Promise<any>}
     * @memberof AppMobCalendarService
     */
    public async delete(itemType: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            let result: any;
            let tempRequest:any;
            const calendarItems = (this.controlInstance as IPSSysCalendar).getPSSysCalendarItems() || [];
            const item = calendarItems.find((_item: IPSSysCalendarItem) => {return _item.itemType == itemType});
            if(item) {
                tempRequest = this.handleRequestData("", context, data, false, itemType);
                let codeName: any = item.getPSAppDataEntity()?.codeName;
                if(codeName && this.$itemEntityServiceMap.get(codeName)) {
                    let service: any = this.$itemEntityServiceMap.get(codeName);
                    result = service.Remove(tempRequest.context, tempRequest.data, isloading);
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
        let model: AppMobCalendarModel = this.getMode();
        model.itemType = itemType;
        super.handleResponse(action,response,isCreate);
    }


    /**
     * 处理返回数据
     *
     * @param {string} action
     * @param {*} response
     * @memberof ControlService
     */
     public handleResponseData(action: string, data: any = {},isCreate?:boolean,codelistArray?:any){
      let model: any = this.getMode();
      if (!model && model.getDataItems instanceof Function) {
          return data;
      }
      const tempData: any = data;
      let dataItems: any[] = model.getDataItems();
      dataItems.forEach(dataitem => {
          let val = tempData.hasOwnProperty(dataitem.prop) ? tempData[dataitem.prop] : null;
          if (!val) {
              val = tempData.hasOwnProperty(dataitem.name) ? tempData[dataitem.name] : null;
          }
          tempData[dataitem.name] = val;
      });
      return tempData;
  }
}