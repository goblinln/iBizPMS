import { IPSAppDataEntity, IPSAppDEField, IPSControl } from '@ibiz/dynamic-model-api';
import { ModelTool } from '../../utils/util/model-tool';
import { AppServiceBase } from "../app-service/app-base.service";
import { CodeListServiceBase } from "../code-list-service/codelist-base.service";
/**
 * 部件服务基类
 *
 * @export
 * @class ControlServiceBase
 */
export class ControlServiceBase {

    /**
     * 应用状态对象
     *
     * @private
     * @type {(any | null)}
     * @memberof ControlServiceBase
     */
    private $store: any;

    /**
     * 部件模型
     *
     * @private
     * @type {(any | null)}
     * @memberof ControlServiceBase
     */
    public controlInstance: any;

    /**
     * 部件模型
     *
     * @type {(any | null)}
     * @memberof ControlServiceBase
     */
    public model: any | null = null;

    /**
     * 代码表服务对象
     *
     * @type {any}
     * @memberof ControlServiceBase
     */
    public codeListService: any;

    /**
     * 是否为从数据模式
     *
     * @type {boolean}
     * @memberof ControlServiceBase
     */
    public isTempMode: boolean = false;

    /**
     * Creates an instance of ControlServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof ControlServiceBase
     */
    constructor(opts: any = {}) {
        this.$store = AppServiceBase.getInstance().getAppStore();;
        this.setTempMode();
        this.codeListService = new CodeListServiceBase();
    }

    /**
     * 应用实体codeName
     *
     * @readonly
     * @memberof MainViewBase
     */
     get appDeCodeName(){
        return this.controlInstance?.getPSAppDataEntity()?.codeName || '';
    }

    /**
     * 应用实体主键属性codeName
     *
     * @readonly
     * @memberof MainViewBase
     */
    get appDeKeyFieldName(){
        return (ModelTool.getAppEntityKeyField(this.controlInstance?.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName || '';
    }

    /**
     * 应用实体主信息属性codeName
     *
     * @readonly
     * @memberof MainViewBase
     */
    get appDeMajorFieldName(){
        return (ModelTool.getAppEntityMajorField(this.controlInstance?.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName || '';
    }

    /**
     * 获取应用状态对象
     *
     * @returns {(any | null)}
     * @memberof ControlServiceBase
     */
    public getStore() {
        return this.$store;
    }

    /**
     * 获取部件模型
     *
     * @returns {*}
     * @memberof ControlServiceBase
     */
    public getMode(): any {
        return this.model;
    }

    /**
     * 设置零时模式
     *
     * @returns {(any | null)}
     * @memberof ControlServiceBase
     */
    protected setTempMode() { }

    /**
     * 处理请求数据
     * 
     * @param action 行为 
     * @param data 数据
     * @memberof ControlServiceBase
     */
    public handleRequestData(action: string, context: any = {}, data: any = {}, isMerge: boolean = false) {
        let model: any = this.getMode();
        if (!(model && model.getDataItems instanceof Function)) {
            return data;
        }
        let dataItems: any[] = model.getDataItems();
        let requestData: any = {};
        if (isMerge && (data && data.viewparams)) {
            Object.assign(requestData, data.viewparams);
        }
        dataItems.forEach((item: any) => {
            if (item && item.dataType && Object.is(item.dataType, 'FRONTKEY')) {
                if (item && item.prop && item.name) {
                    requestData[item.prop] = context[item.name];
                }
            } else {
                if (item && item.prop && item.name && (data[item.name] || Object.is(data[item.name], 0))) {
                    requestData[item.prop] = data[item.name];
                }
            }
        });
        let tempContext: any = JSON.parse(JSON.stringify(context));
        if (tempContext && tempContext.srfsessionid) {
            tempContext.srfsessionkey = tempContext.srfsessionid;
            delete tempContext.srfsessionid;
        }
        return { context: tempContext, data: requestData };
    }

    /**
     * 处理response
     *
     * @param {string} action
     * @param {*} response
     * @memberof ControlServiceBase
     */
    public async handleResponse(action: string, response: any, isCreate: boolean = false) {
        let result = null;
        if (!response.data) {
            return
        }
        const handleResult: any = (action: string, response: any, isCreate: boolean, codelistArray?: any) => {
            if (response.data instanceof Array) {
                result = [];
                response.data.forEach((item: any) => {
                    result.push(this.handleResponseData(action, item, isCreate, codelistArray));
                });
            } else {
                result = this.handleResponseData(action, response.data, isCreate, codelistArray);
            }
            // response状态，头文件
            if (response.headers) {
                if (response.headers['x-page']) {
                    Object.assign(response, { page: Number(response.headers['x-page']) });
                }
                if (response.headers['x-per-page']) {
                    Object.assign(response, { size: Number(response.headers['x-per-page']) });
                }
                if (response.headers['x-total']) {
                    Object.assign(response, { total: Number(response.headers['x-total']) });
                }
            }
            response.data = result;
        }
        let codelistModel: Array<any> = this.handleCodelist();
        if (codelistModel.length > 0) {
            let res: any = await this.getAllCodeList(codelistModel);
            handleResult(action, response, isCreate, res);
        } else {
            handleResult(action, response, isCreate);
        }
    }

    /**
     * 处理返回数据
     *
     * @param {string} action
     * @param {*} response
     * @memberof ControlServiceBase
     */
    public handleResponseData(action: string, data: any = {}, isCreate?: boolean, codelistArray?: any) {
        let model: any = this.getMode();
        if (!(model && model.getDataItems instanceof Function)) {
            return data;
        }
        let item: any = {};
        let dataItems: any[] = model.getDataItems();
        dataItems.forEach(dataitem => {
            if (dataitem && (dataitem?.dataType !== "QUERYPARAM")) {
                let val = data.hasOwnProperty(dataitem.prop) ? data[dataitem.prop] : null;
                if (val === null) {
                    val = data.hasOwnProperty(dataitem.name) ? data[dataitem.name] : null;
                }
                if ((isCreate === undefined || isCreate === null) && Object.is(dataitem.dataType, 'GUID') && Object.is(dataitem.name, 'srfkey') && (val && !Object.is(val, ''))) {
                    isCreate = true;
                }
                item[dataitem.name] = val;
                // 转化代码表
                if (codelistArray && dataitem.codelist) {
                    if (codelistArray.get(dataitem.codelist.tag) && codelistArray.get(dataitem.codelist.tag).get(val)) {
                        item[dataitem.name] = codelistArray.get(dataitem.codelist.tag).get(val);
                    }
                }
            }
        });
        item.srfuf = data.srfuf ? data.srfuf : (isCreate ? "0" : "1");
        return item;
    }

    /**
     * 处理工作流数据
     * 
     * @param data 传入数据
     */
    public handleWFData(data: any, isMerge: boolean = false) {
        let model: any = this.getMode();
        if (!(model && model.getDataItems instanceof Function)) {
            return data;
        }
        let dataItems: any[] = model.getDataItems();
        let requestData: any = {};
        dataItems.forEach((item: any) => {
            if (item && item.prop) {
                requestData[item.prop] = data[item.name];
            } else {
                if (item && item.dataType && Object.is(item.dataType, "FORMITEM")) {
                    requestData[item.name] = data[item.name]
                }
            }
        });
        if (isMerge && (data.viewparams && Object.keys(data.viewparams).length > 0)) {
            Object.assign(requestData, data.viewparams);
        }
        return requestData;
    }

    /**
     * 处理代码表
     * 
     * @memberof ControlServiceBase
     */
    public handleCodelist() {
        let model: any = this.getMode();
        if (!model) {
            return [];
        }
        let dataItems: any[] = model.getDataItems();
        let codelistMap: Map<string, any> = new Map();
        if (dataItems && dataItems.length > 0) {
            dataItems.forEach((item: any) => {
                if (item.codelist) {
                    codelistMap.set(item.name, item.codelist);
                }
            })
        }
        if (codelistMap.size > 0) {
            return Array.from(codelistMap).map(item => item[1]);
        } else {
            return [];
        }
    }

    /**
     * 获取所有代码表
     * 
     * @param codelistArray 代码表模型数组
     * @memberof ControlServiceBase
     */
    public async getAllCodeList(codelistArray: Array<any>): Promise<any> {
        let codelistMap: Map<string, any> = new Map();
        for (let item of codelistArray) {
            let codeList: any = await this.getCodeList(item);
            let tempCodeListMap: Map<number, any> = new Map();
            if (codeList.length > 0) {
                codeList.forEach((codeListItem: any) => {
                    tempCodeListMap.set(codeListItem.value, codeListItem.text);
                })
            }
            codelistMap.set(item.tag, tempCodeListMap);
        }
        return codelistMap;
    }

    /**
     * 获取代码表
     * 
     * @param codeListObject 传入代码表对象
     * @memberof ControlServiceBase
     */
    public getCodeList(codeListObject: any): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            if (codeListObject.tag && codeListObject.codelistType) {
                this.codeListService.getDataItems({ tag: codeListObject.tag, type: codeListObject.codelistType, data: codeListObject }).then((res: any) => {
                    resolve(res);
                }).catch((error: any) => {
                    resolve([]);
                    console.log(`----${codeListObject.tag}----代码表不存在`);
                })
            }
        })
    }

    /**
     * 获取部件模型
     * 
     * @param codeListObject 传入代码表对象
     * @memberof ControlServiceBase
     */
    public getControlInstance() {
        return this.controlInstance;
    }

    /**
     * 根据后台标识获取数据标识名称
     * 
     * @param prop 后台标识
     * @memberof ControlServiceBase
     */
    public getNameByProp(prop: any) {
        let model: any = this.getMode();
        if (!model || !prop) {
            return false;
        }
        let dataItems: any[] = model.getDataItems();
        prop = prop.replace(/([A-Z])/g, "_$1").toLowerCase();
        let data: any = dataItems.find((item: any) => {
            return Object.is(prop, item.prop);
        });
        return data.name;
    }
}