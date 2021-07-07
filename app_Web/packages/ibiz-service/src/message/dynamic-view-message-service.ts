import { IPSAppDEDataSetViewMsg } from "@ibiz/dynamic-model-api";
import { LogUtil } from "ibiz-core";
import { descSort, ascSort } from 'qx-util';

/**
 * 动态模式（实体数据集合）类型视图消息服务
 * 
 * @class DynamicViewMessageService
 */
export class DynamicViewMessageService {

    /**
     * 视图消息（实体数据集）实例对象
     * 
     * @memberof DynamicViewMessageService
     */
    public viewMsgInstance: IPSAppDEDataSetViewMsg;

    /**
     * 过期时间
     * 
     * @memberof DynamicViewMessageService
     */
    private expirationTime: any;

    /**
     * 构造动态视图消息服务
     * 
     * @memberof DynamicViewMessageService
     */
    constructor(opts: IPSAppDEDataSetViewMsg) {
        this.viewMsgInstance = opts;
    }

    /**
     * 获取过期时间
     * 
     * @memberof DynamicViewMessageService
     */
    public getExpirationTime() {
        return this.expirationTime;
    }

    /**
     * 设置过期时间
     * 
     * @param value 过期时间
     * @memberof DynamicViewMessageService
     */
    public setExpirationTime(value: any) {
        this.expirationTime = value;
    }

    /**
     * 获取远程数据
     *
     * @param {*} context 应用上下文
     * @param {*} data 视图参数
     * @param {boolean} [isloading] 加载状态
     * @returns {Promise<any>}
     *
     * @memberof DynamicViewMessageService
     */
    public getItems(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const _this: any = this;
        const appDataEntityCodeName = _this.viewMsgInstance?.getPSAppDataEntity?.()?.codeName;
        const appDataSetCodeName = _this.viewMsgInstance?.getPSAppDEDataSet?.().codeName;
        let tempContext:any = context ? context : {};
        let tempData:any = data ? data : {};
        return new Promise((resolve, reject) => {
            if (appDataEntityCodeName && appDataSetCodeName) {
                ___ibz___.gs.getService(appDataEntityCodeName).then((service:any) =>{
                    if(service[appDataSetCodeName] && service[appDataSetCodeName] instanceof Function){
                        const promise: Promise<any> = service[appDataSetCodeName](tempContext, tempData, isloading);
                        promise.then((response: any) => {
                            if (response && response.status === 200) {
                                const data =  response.data;
                                resolve(_this.doItems(data, tempContext, tempData));
                            } else {
                                resolve([]);
                            }
                        }).catch((response: any) => {
                            LogUtil.error(response);
                            reject(response);
                        });
                    }
                })
            } else {
                resolve([]);
            }
        });
    }

    /**
     * 处理数据
     *
     * @param {any[]} items 源数据
     * @param {*} context 应用上下文
     * @param {*} data 视图参数
     * @returns {any[]}
     *
     * @memberof DynamicViewMessageService
     */
    public doItems(items: any[], context: any = {}, data: any = {}): any[] {
        const _this: any = this;
        let tempItems: any[] = [];
        const { name, codeName, title, position, titleLanResTag, message, messageType, removeMode, enableRemove } = this.viewMsgInstance;
        if (items.length > 0) {
            //  排序
            if (this.viewMsgInstance.getOrderValuePSAppDEField()?.codeName) {
                _this.sortItems(items);
            }
            items.forEach((item: any) => {
                let tempData: any = {
                    position: position || 'TOP',
                    name: name,
                    codeName: codeName?.toLowerCase(),
                    type: messageType,
                    title: title,
                    titleLanResTag: titleLanResTag || this.viewMsgInstance.getTitlePSLanguageRes()?.lanResTag,
                    content: message,
                    removeMode: removeMode,
                    enableRemove: enableRemove
                };
                _this.formatDynamicViewMsg(tempData, context, data, item);
                _this.translateMessageTemp(tempData, context, data, item);
                tempItems.push(tempData);
            })
        }
        return tempItems;
    }

    /**
     * 根据属性配置翻译视图消息
     *      
     * @target {*} target 返回目标数据
     * @param {*} context 应用上下文
     * @param {*} viewparam 视图参数
     * 
     * @memberof DynamicViewMessageService
     */
    public formatDynamicViewMsg(target: any, context: any = {}, viewparam: any = {}, item?: any) {
        if (!item) {
            return;
        }
        //  标题
        const titleField = this.viewMsgInstance.getTitlePSAppDEField?.()?.codeName?.toLowerCase();
        if (titleField && item[titleField]) {
            Object.assign(target, { title: item[titleField] } );
        }
        //  内容
        const contentField = this.viewMsgInstance.getContentPSAppDEField?.()?.codeName?.toLowerCase();
        if (contentField && item[contentField]) {
            Object.assign(target, { content: item[contentField] });
        }
        //  消息类型
        const msgTypeField = this.viewMsgInstance.getMsgTypePSAppDEField?.()?.codeName?.toLowerCase();
        if (msgTypeField) {
            const type = item[msgTypeField];
            if (type == 'INFO' || item[msgTypeField] == 'WARN' || type == 'ERROR' || type == 'CUSTOM') {
                Object.assign(target, { type: type });
            } else {
                LogUtil.warn("视图消息类型值异常，应为['INFO', 'WARN', 'ERROR', 'CUSTOM']中的类型");
            }
        }
        //  消息位置
        const msgPosField = this.viewMsgInstance.getMsgTypePSAppDEField?.()?.codeName?.toLowerCase();
        if (msgPosField) {
            const position = item[msgPosField];
            if (position == 'TOP' || position == 'BODY' || position == 'BOTTOM' || position == 'POPUP' || position == 'CUSTOM') {
                Object.assign(target, { position: position });
            } else {
                LogUtil.warn("视图消息位置值异常，应为['TOP', 'BODY', 'BOTTOM', 'POPUP', 'CUSTOM']中的位置");
            }
        }
        //  删除模式
        const removeFlagField = this.viewMsgInstance.getRemoveFlagPSAppDEField?.()?.codeName?.toLowerCase();
        if (removeFlagField) {
            const removeMode = item[removeFlagField];
            if (removeMode == 0 || removeMode == 1 || removeMode == 2) {
                Object.assign(target, { removeMode: removeMode });
            } else {
                LogUtil.warn("视图消息删除模式值异常，应为[0, 1, 2]中的值，分别表示['无关闭', '默认关闭', '本次关闭']");
            }
        }
        //  排序标识
        const orderValueField = this.viewMsgInstance.getOrderValuePSAppDEField?.()?.codeName?.toLowerCase();
        if (orderValueField && item[orderValueField]) {
            Object.assign(target, { orderKey: item[orderValueField] });
        }
        //  标题多语言
        const titleLanResTagField = this.viewMsgInstance.getTitleLanResTagPSAppDEField?.()?.codeName?.toLowerCase();
        if (titleLanResTagField && item[titleLanResTagField]) {
            Object.assign(target, { titleLanResTag: item[titleLanResTagField] });
        }
    }

    /**
     * 转化消息模板标题和内容
     *      
     * @target {*} target 返回目标数据
     * @param {*} context 应用上下文
     * @param {*} viewparam 视图参数
     * @param {*} item 源数据
     * 
     * @memberof DynamicViewMessageService
     */
    public translateMessageTemp(target: any, context: any = {}, data: any = {}, item?: any){
        const sysMsgTempl: any = (this.viewMsgInstance as any).getPSAppMsgTempl?.();
        if (!sysMsgTempl) {
            return;
        }
        //  系统消息模板待补充
        if (sysMsgTempl.getContentType) {
        }
    }

    /**
     * 排序(默认升序)
     * 
     * @param items 
     */
    public sortItems(items: any[], mode: string = 'ASC') {
        const _this: any = this;
        const orderValue = _this.viewMsgInstance.getOrderValuePSAppDEField().codeName.toLowerCase();
        if (mode == 'ASC') {
            items = ascSort(items, orderValue);
        } else {
            items = descSort(items, orderValue);
        }
    }
}