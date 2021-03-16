import { ControlServiceBase, Util, IBizGridModel, IBizEntityModel } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppGridModel, Errorlog } from 'ibiz-vue';


/**
 * 表格部件服务对象
 *
 * @export
 * @class AppGridService
 */
export class AppGridService extends ControlServiceBase {


    /**
    * 表格实例对象
    *
    * @memberof AppGridService
    */
    public controlInstance !: IBizGridModel;

    /**
     * 数据服务对象
     *
     * @type {any}
     * @memberof AppGridService
     */
    public appEntityService!: any;

    /**
     * 备份原生数据
     *
     * @type {*}
     * @memberof AppGridService
     */
    private copynativeData:any;

    /**
     * 远端数据
     *
     * @type {*}
     * @memberof AppGridService
     */
    private remoteCopyData: any = {};

    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppGridService
     */
    public async initServiceParam(opts: any) {
        this.controlInstance = opts;
        this.appEntityService = await new GlobalService().getService(this.controlInstance.appDataEntity.codeName);
        this.model = new AppGridModel(opts);
    }

    /**
     * Creates an instance of AppGridService.
     * 
     * @param {*} [opts={}]
     * @memberof AppGridService
     */
    constructor(opts: any = {}) {
        super(opts);
        this.initServiceParam(opts);
    }

    /**
     * 处理数据
     *
     * @private
     * @param {Promise<any>} promise
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    private doItems(promise: Promise<any>, deKeyField: string, deName: string): Promise<any> {
        return new Promise((resolve, reject) => {
            promise.then((response: any) => {
                if (response && response.status === 200) {
                    const data = response.data;
                    data.forEach((item: any, index: number) => {
                        item[deName] = item[deKeyField];
                        data[index] = item;
                    });
                    resolve(data);
                } else {
                    reject([])
                }
            }).catch((response: any) => {
                reject([])
            });
        });
    }

    /**
     * 获取跨实体数据集合
     *
     * @param {string} serviceName 服务名称
     * @param {string} interfaceName 接口名称
     * @param {*} data
     * @param {boolean} [isloading]
     * @returns {Promise<any[]>}
     * @memberof  AppGridService
     */
    @Errorlog
    public async getItems(serviceName: string, interfaceName: string, context: any = {}, data: any, isloading?: boolean): Promise<any[]> {
        data.page = data.page ? data.page : 0;
        data.size = data.size ? data.size : 1000;
        const editItems: Array<any> = this.controlInstance.allEditColumns;
        if(editItems?.length>0) {
            let item: any = editItems.find((item: any) => {
                let flag: boolean = false;
                const editor: any = item.editorInstance;
                if(editor && 
                    editor.appEntity && 
                    item.getPSEditor?.getPSAppDEDataSet && 
                    serviceName &&
                    Object.is(editor.appEntity.codeName, serviceName) &&
                    interfaceName &&
                    Object.is(item.getPSEditor?.getPSAppDEDataSet.id, interfaceName)) {
                        flag = true;
                }
                return flag;
            })
            if(item) {
                const service: any = await new GlobalService().getService(serviceName);
                if(service && service[interfaceName] && Util.isFunction(service[interfaceName])) {
                    return this.doItems(service[interfaceName](JSON.parse(JSON.stringify(context)),data, isloading), item.editorInstance.appEntity.keyField.codeName.toLowerCase(), item.editorInstance.appEntity.codeName.toLowerCase());
                }
            }
        }
        return Promise.reject([])
    }

    /**
     * 启动工作流
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @param {*} [localdata]
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public wfstart(action: string, context: any = {}, data: any = {}, isloading?: boolean, localdata?: any): Promise<any> {
        data = this.handleWFData(data, true);
        context = this.handleRequestData(action, context, data).context;
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](context, data, localdata);
            } else {
                result = this.appEntityService.WFStart(context, data, localdata);
            }
            result.then((response) => {
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }

    /**
     * 提交工作流
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @param {*} [localdata]
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public wfsubmit(action: string, context: any = {}, data: any = {}, isloading?: boolean, localdata?: any): Promise<any> {
        data = this.handleWFData(data, true);
        context = this.handleRequestData(action, context, data, true).context;
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](context, data, localdata);
            } else {
                result = this.appEntityService.WFSubmit(context, data, localdata);
            }
            result.then((response) => {
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }

    /**
     * 添加数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @param {boolean} [isWorkflow] 是否在工作流中添加数据
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public add(action: string, context: any = {}, data: any = {}, isloading?: boolean, isWorkflow?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestDataWithUpdate(action, context, data);
        const editItems = this.controlInstance.allEditColumns;
        const keyField: any = this.controlInstance.appDataEntity.keyField.codeName.toLowerCase();
        if(editItems?.length>0) {
            editItems.forEach((item: any) => {
                if(Object.is(item.codeName.toLowerCase(), keyField) && !Object.is(item.codeName.toLowerCase(), "srfkey")) {
                    Object.assign(Data, { [keyField]: data[item.name], srffrontuf: '1' });
                }
            })
        }
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data);
            } else {
                result = this.appEntityService.Create(Context, Data);
            }
            result.then((response) => {
                if (isWorkflow) {
                    resolve(response);
                } else {
                    this.handleResponse(action, response);
                    resolve(response);
                }
            }).catch(response => {
                reject(response);
            });
        });
    }

    /**
     * 删除数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public delete(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestData(action, context, data);
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data);
            } else {
                result = this.appEntityService.Remove(Context, Data);
            }
            result.then((response) => {
                resolve(response);
            }).catch(response => {
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
     * @param {boolean} [isWorkflow] 是否在工作流中修改数据
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public update(action: string, context: any = {}, data: any = {}, isloading?: boolean, isWorkflow?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestDataWithUpdate(action, context, data);
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data);
            } else {
                result = this.appEntityService.Update(Context, Data);
            }
            result.then((response) => {
                if (isWorkflow) {
                    resolve(response);
                } else {
                    this.handleResponse(action, response);
                    resolve(response);
                }
            }).catch(response => {
                reject(response);
            });
        });
    }

    /**
     * 查询数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public get(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestData(action, context, data);
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data);
            } else {
                result = this.appEntityService.Get(Context, Data);
            }
            result.then((response) => {
                this.setRemoteCopyData(response);
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }

    /**
     * 加载草稿
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public loadDraft(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestData(action, context, data);
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data);
            } else {
                result = this.appEntityService.GetDraft(Context, Data);
            }
            result.then((response) => {
                if(response.data) {
                    Object.assign(response.data, { srfuf: '0' });
                    const keyField = this.controlInstance.appDataEntity.keyField.codeName.toLowerCase();
                    if(this.controlInstance.allEditColumns?.length>0) {
                        const editItem: any = this.controlInstance.allEditColumns.forEach((item: any) => {
                            return Object.is(item.name.toLowerCase(), keyField) && !Object.is(item.name.toLowerCase(), "srfkey");
                        })
                        if(editItem) {
                            response.data[keyField] = Util.createUUID();
                        }
                    }
                }
                this.setRemoteCopyData(response);
                this.handleResponse(action, response, true);
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }

    /**
    * 前台逻辑
    * @param {string} action
    * @param {*} [context={}]
    * @param {*} [data={}]
    * @param {boolean} [isloading]
    * @returns {Promise<any>}
    * @memberof AppGridService
    */
    @Errorlog
    public frontLogic(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestData(action, context, data);
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data);
            } else {
                return Promise.reject({ status: 500, data: { title: '失败', message: '系统异常' } });
            }
            result.then((response) => {
                this.handleResponse(action, response, true);
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        })
    }

    /**
     * 表格聚合加载数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public getAggData(action: string,context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        const {data:Data,context:Context} = this.handleRequestData(action,context,data,true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context,Data, isloading);
            }else{
                result = Promise.reject({});
            }
            result.then((response) => {
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });      
        });
    }

    /**
     * 处理请求数据
     * 
     * @param action 行为 
     * @param data 数据
     * @memberof AppGridService
     */
    public handleRequestData(action: string, context: any, data: any = {}, isMerge: boolean = false) {
        let mode: any = this.getMode();
        if (!mode && mode.getDataItems instanceof Function) {
            return data;
        }
        let formItemItems: any[] = mode.getDataItems();
        let requestData: any = {};
        if (isMerge && (data && data.viewparams)) {
            Object.assign(requestData, data.viewparams);
        }
        formItemItems.forEach((item: any) => {
            if (item && item.dataType && Object.is(item.dataType, 'FRONTKEY')) {
                if (item && item.prop) {
                    requestData[item.prop] = context[item.name];
                }
            } else {
                if (item && item.prop) {
                    requestData[item.prop] = data[item.name];
                } else {
                    if (item.dataType && Object.is(item.dataType, "FORMPART")) {
                        Object.assign(requestData, data[item.name]);
                    }
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
     * 通过属性名称获取表单项名称
     * 
     * @param name 实体属性名称 
     * @memberof AppGridService
     */
    public getItemNameByDeName(name: string): string {
        let itemName = name;
        let mode: any = this.getMode();
        if (!mode && mode.getDataItems instanceof Function) {
            return name;
        }
        let formItemItems: any[] = mode.getDataItems();
        formItemItems.forEach((item: any) => {
            if (item.prop === name) {
                itemName = item.name;
            }
        });
        return itemName.trim();
    }

    /**
     * 重写处理返回数据
     *
     * @param {string} action
     * @param {*} response
     * @memberof AppGridService
     */
    public handleResponseData(action: string, data: any = {}, isCreate?: boolean, codelistArray?: any) {
        let model: any = this.getMode();
        if (!model && model.getDataItems instanceof Function) {
            return data;
        }
        let item: any = {};
        let dataItems: any[] = model.getDataItems();
        dataItems.forEach(dataitem => {
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
        });
        item.srfuf = data.srfuf ? data.srfuf : (isCreate ? "0" : "1");
        item = Object.assign(data, item);
        return item;
    }

    /**
     * 设置远端数据
     * 
     * @param result 远端请求结果 
     * @memberof AppGridService
     */
    public setRemoteCopyData(result: any) {
        if (result && result.status === 200) {
            this.remoteCopyData = Util.deepCopy(result.data);
        }
    }

    /**
     * 获取远端数据
     * 
     * @memberof AppGridService
     */
    public getRemoteCopyData() {
        return this.remoteCopyData;
    }

    /**
     * 查询数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public search(action: string,context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        const {data:Data,context:Context} = this.handleRequestData(action,context,data,true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context,Data, isloading);
            }else{
                result =_appEntityService.FetchDefault(Context,Data, isloading);
            }
            result.then((response) => {
                this.setCopynativeData(response.data);
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });      
        });
    }

    /**
     * 查询实体导出数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public searchDEExportData(action: string,context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        const {data:Data,context:Context} = this.handleRequestData(action,context,data,true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context,Data, isloading);
            }else{
                result =_appEntityService.FetchDefault(Context,Data, isloading);
            }
            result.then((response) => {
                let model: any = this.getMode();
                model.isDEExport = true;
                this.handleResponse(action, response);
                model.isDEExport = false;
                resolve(response);
            }).catch(response => {
                reject(response);
            });      
        });
    }

        /**
     * 处理请求数据(修改或增加数据)
     * 
     * @param action 行为 
     * @param data 数据
     * @memberof AppGridService
     */
    public handleRequestDataWithUpdate(action: string,context:any ={},data: any = {},isMerge:boolean = false){
        let model: any = this.getMode();
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
                if(item && item.isEditable && item.prop && item.name && data.hasOwnProperty(item.name)){
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
     * 处理工作流数据
     * 
     * @param data 传入数据
     *  @memberof AppGridService
     */
    public handleWFData(data:any, isMerge:boolean = false){
        let model: any = this.getMode();
        if (!model && model.getDataItems instanceof Function) {
            return data;
        }
        let dataItems: any[] = model.getDataItems();
        let requestData:any = {};
        dataItems.forEach((item:any) =>{
            if(item && item.prop){
                if(item.dataType){
                    if(!Object.is(item.dataType,'QUERYPARAM')){
                        requestData[item.prop] = data[item.name];
                    }
                }else{
                    requestData[item.prop] = data[item.name];
                }  
            }
        });
        if(isMerge && (data.viewparams && Object.keys(data.viewparams).length > 0)){
            Object.assign(requestData,data.viewparams);
        }
        // 删除前端srffrontuf标识
        if(requestData.hasOwnProperty('srffrontuf')){
            delete requestData.srffrontuf;
        }
        //补充工作流所需主键
        requestData.srfkey = data[this.controlInstance.appDataEntity.codeName.toLowerCase()];
        //补充全量数据
        requestData = this.fillNativeData(requestData);
        return requestData;
    }

    /**
     * 补充全量数据
     *
     * @param {*} [data]
     * @memberof AppGridService
     */
    public fillNativeData(data:any){
        if(this.copynativeData && this.copynativeData.length >0){
            const keyField = this.controlInstance.appDataEntity.keyField.codeName.toLowerCase();
            let targetData:any = this.copynativeData.find((item:any) =>{
                return item[keyField] === data.srfkey;
            })
            data = Object.assign(targetData,data);
            return data;
        }
    }

    /**
     * 提交工作流
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @param {*} [localdata]
     * @returns {Promise<any>}
     * @memberof AppGridService
     */
    @Errorlog
    public submitbatch(action: string,context: any = {}, data: any,localdata:any,isloading?: boolean): Promise<any> {
        let tempData:any = [];
        if(data && data.length > 0){
            data.forEach((item:any) => {
                let data:any = this.handleWFData(item,true);
                tempData.push(data);
            });
        }
        context = this.handleRequestData(action,context,data,true).context;
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](context,tempData, localdata,isloading);
            } else {
                result = this.appEntityService.wfSubmitBatch(context,tempData,localdata,isloading);
            }
            result.then((response) => {
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }

    /**
     * 设置备份原生数据
     * 
     * @param data 远端请求结果 
     * @memberof AppGridService
     */
    public setCopynativeData(data:any){
        this.copynativeData = Util.deepCopy(data);
    }    
    
    /**
    * 获取备份原生数据
    * 
    * @memberof AppGridService
    */
   public getCopynativeData(){
       return this.copynativeData;
   } 
}