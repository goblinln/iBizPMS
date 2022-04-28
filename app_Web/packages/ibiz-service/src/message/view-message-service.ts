import { IPSAppDEDataSetViewMsg, IPSAppMsgTempl, IPSAppViewMsg, IPSAppViewMsgGroup, IPSAppViewMsgGroupDetail } from '@ibiz/dynamic-model-api';
import { Util } from 'ibiz-core';
import { DynamicViewMessageService } from './dynamic-view-message-service';

/**
 * 视图消息服务
 *
 * @export
 * @class ViewMessageService
 */
export class ViewMessageService {

    /**
     * 视图消息集合实例对象
     *
     * @type {IPSAppViewMsgGroup}
     * @memberof ViewMessageService
     */
    protected viewMessageGroup!: IPSAppViewMsgGroup;

    /**
     * 视图消息集合
     *
     * @type {any[]}
     * @memberof ViewMessageService
     */
    protected viewMessageDetails: any[] = [];

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof ViewMessageService
     */
    public context: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof ViewMessageService
     */
    public viewparams: any;

    /**
     * 视图消息缓存(加载中)
     *
     * @type {Map<string,any>}
     * @memberof ViewMessageService
     */
    public static messageCache:Map<string,any> = new Map();

    /**
     * 视图消息缓存(已完成)
     *
     * @type {Map<string,any>}
     * @memberof ViewMessageService
     */
    public static messageCached:Map<string,any> = new Map();

    /**
     * 获取视图消息实例
     * 
     * @param position 视图消息位置
     * @memberof ViewMessageService
     */
    public getViewMsgDetails(position?: string): any[] {
        if (!position) {
            return this.viewMessageDetails;
        }
        if (position == 'TOP') {
            return this.viewMessageDetails.filter((detail: any) => {
                return detail.position == 'TOP' || detail.position == 'POPUP';
            });
        } else {
            return this.viewMessageDetails.filter((detail: any) => {
                return detail.position == position;
            });
        }
    }

    /**
     * 初始化基础参数
     * 
     * @memberof ViewMessageService
     */
    public async initBasicParam(opts: IPSAppViewMsgGroup, context?: any, viewparams?: any) {
        this.viewMessageGroup = opts;
        this.context = context;
        this.viewparams = viewparams;
        await this.initViewMsgDetails();
    }

    /**
     * 初始化视图消息集合
     * 
     * @memberof ViewMessageService
     */
    public async initViewMsgDetails() {
        const viewMsgGroupDetails: Array<IPSAppViewMsgGroupDetail> = this.viewMessageGroup?.getPSAppViewMsgGroupDetails?.() || [];
        if (viewMsgGroupDetails.length == 0) {
            return [];
        }
        for (let i = 0; i<viewMsgGroupDetails.length; i++) {
            const viewMsg = viewMsgGroupDetails[i].getPSAppViewMsg() as IPSAppViewMsg;
            await viewMsg.fill(true);
            let items: any[] = [];
            if (viewMsg.dynamicMode == 0) { //  动态模式为 静态
                items = await this.initStaticViewMessage(viewMsg);
            } else if (viewMsg.dynamicMode == 1) {  //  动态模式为 实体数据集
                items = await this.initDynamicViewMessage(viewMsg as IPSAppDEDataSetViewMsg, this.context, this.viewparams);
            }
            this.viewMessageDetails.push(...items);
        }
    }

    /**
     * 初始化动态模式（静态）类型视图消息
     * 
     * @param {IPSAppViewMsg} 动态模式（静态）类型视图消息实例
     * @memberof ViewMessageService
     */
    public async initStaticViewMessage(detail: IPSAppViewMsg): Promise<any[]> {
        const _this: any = this;
        return new Promise((resolve: any, reject: any) => {
            let viewMessage: any = {
                position: detail.position || 'TOP',
                name: detail.name,
                codeName: detail.codeName?.toLowerCase(),
                type: detail.messageType,
                title: detail.title,
                titleLanResTag: detail.titleLanResTag || detail.getTitlePSLanguageRes()?.lanResTag,
                content: detail.message,
                removeMode: detail.removeMode,
                enableRemove: detail.enableRemove,
            };
            _this.translateMessageTemp(viewMessage, detail);
            resolve([viewMessage]);
        })
        
    }

    /**
     * 转化动态模式（静态）类型视图消息模板标题和内容
     *      
     * @target {*} target 返回目标数据
     * @param {IPSAppViewMsg} 动态模式（静态）视图消息实例
     * 
     * @memberof ViewMessageService
     */
    public translateMessageTemp(target: any, detail: IPSAppViewMsg) {
        const format = (content: any) => {
            if (!Util.isExistAndNotEmpty(content)) {
                return content;
            }
            const params: any[] = content.match(/\${(.+?)\}/g) || [];
            if (params.length > 0) {
                params.forEach((param: any) => {
                    let _param: any = param.substring(2, param.length - 1).toLowerCase();
                    const arr: string[] = _param.split('.');
                    if (arr.length == 2) {
                        switch (arr[0]) {
                            case 'context':
                                content = this.context ? content.replace(param, this.context[arr[1]]) : content;
                                break;
                            case 'viewparams':
                                content = this.viewparams ? content.replace(param, this.viewparams[arr[1]]) : content;
                                break;
                        }
                    }
                })
            }
            return content;
        }
        const appMsgTempl: IPSAppMsgTempl = detail.getPSAppMsgTempl?.() as IPSAppMsgTempl;
        if (!appMsgTempl) {
            return;
        }
        Object.assign(target, {
            messageType: appMsgTempl.contentType == 'HTML' ? 'HTML' : 'TEXT',
            title: format(appMsgTempl.subject),
            titleLanResTag: appMsgTempl.getSubPSLanguageRes()?.lanResTag,
            content: format(appMsgTempl.content)
        });
    }

    /**
     * 初始化动态模式（实体数据集合）类型视图消息
     *
     * @param {any} tag 视图消息标识
     * @param {any} messageService 消息服务
     * @param {string} context
     * @returns {Promise<any[]>}
     * @memberof ViewMessageService
     */
    public async initDynamicViewMessage(detail: IPSAppDEDataSetViewMsg, context: any = {}, data: any = {}, isloading?: boolean): Promise<any[]> {
        if(context && context.srfsessionid){
            delete context.srfsessionid;
        }
        //  动态模式（实体数据集合）类型视图消息服务
        const dynamicViewMsgService: DynamicViewMessageService = new DynamicViewMessageService(detail);
        return new Promise((resolve:any,reject:any) =>{
            let isEnableCache: boolean = (detail as any).enableCache;
            let cacheTimeout :any = (detail as any).cacheTimeout;
            const tag: any = detail.codeName;
            // 启用缓存
            if(isEnableCache){
                const callback: Function = (context:any ={}, data:any ={}, tag:string, promise:Promise<any>) =>{
                    const callbackKey:string = `${tag}`;
                    promise.then((result:any) =>{
                        if(result.length > 0){
                            ViewMessageService.messageCached.set(callbackKey,{items:result});
                            ViewMessageService.messageCache.delete(callbackKey);
                            return resolve(result);
                        }else{
                            return resolve([]);
                        }
                    }).catch((result:any) =>{
                        return reject(result);
                    })
                }
                const key:string = `${tag}`;
                // 加载完成,从本地缓存获取
                if(ViewMessageService.messageCached.get(key)){
                    let items:any = ViewMessageService.messageCached.get(key).items;
                    if(items.length > 0){
                        if(new Date().getTime() <= dynamicViewMsgService.getExpirationTime()){
                            return resolve(items); 
                        }
                    }
                }
                // 加载中，UI又需要数据，解决连续加载同一消息服务问题
                if (dynamicViewMsgService) {
                    if(ViewMessageService.messageCache.get(key)){
                        callback(context, data, tag, ViewMessageService.messageCache.get(key));
                    }else{
                        let result: Promise<any> = dynamicViewMsgService.getItems(context, data, isloading);
                        ViewMessageService.messageCache.set(key,result);
                        dynamicViewMsgService.setExpirationTime(new Date().getTime() + cacheTimeout);
                        callback(context, data, tag, result);
                    }
                }
            }else{
                if (dynamicViewMsgService) {
                    dynamicViewMsgService.getItems(context,data,isloading).then((result:any) =>{
                        resolve(result);
                    }).catch((error:any) =>{
                        Promise.reject([]);
                    })
                } else {
                    return Promise.reject([]);
                } 
            }
        })

    }

}