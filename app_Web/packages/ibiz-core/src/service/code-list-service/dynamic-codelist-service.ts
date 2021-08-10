import { IPSAppCodeList } from "@ibiz/dynamic-model-api";
import { AppModelService, GetModelService, LogUtil, ModelTool } from "ibiz-core";

export class DynamicCodeListService {

    /**
     * 动态代码表实例对象
     * 
     * @type {IPSAppCodeList}
     * @memberof DynamicCodeListService
     */
    protected codeListInstance!: IPSAppCodeList;

    /**
     * 是否启用缓存
     *
     * @type boolean
     * @memberof DynamicCodeListService
     */
    public isEnableCache:boolean = false;

    /**
     * 过期时间
     *
     * @type any
     * @memberof DynamicCodeListService
     */
    public static expirationTime:any;

    /**
     * 预定义类型
     *
     * @type string
     * @memberof DynamicCodeListService
     */
    public predefinedType:string ='';

    /**
     * 缓存超长时长
     *
     * @type any
     * @memberof DynamicCodeListService
     */
    public cacheTimeout:any = -1;

    /**
     * 代码表模型对象
     *
     * @type any
     * @memberof DynamicCodeListService
     */
    public codelistModel:any = { };

    /**
     * 获取过期时间
     *
     * @type any
     * @memberof DynamicCodeListService
     */
    public getExpirationTime(){
        return DynamicCodeListService.expirationTime;
    }

    /**
     * 设置过期时间
     *
     * @type any
     * @memberof DynamicCodeListService
     */
    public setExpirationTime(value:any){
        DynamicCodeListService.expirationTime = value; 
    }

    /**
     * 自定义参数集合
     *
     * @type any
     * @memberof DynamicCodeListService
     */
    public userParamNames:any ={ };

    /**
     * 查询参数集合
     *
     * @type any
     * @memberof DynamicCodeListService
     */
    public queryParamNames:any ={};

    /**
     * 加载动态代码表实例对象
     *
     * @type any
     * @memberof DynamicCodeListService
     */
    public loaded(tag: any, context: any = {}): Promise<boolean> {
        if (tag == '' || tag == undefined) {
            return Promise.resolve(false);
        }
        return new Promise((resolve, reject) => {
            GetModelService(context).then((appModelService: AppModelService) => {
                if (appModelService?.app) {
                    this.codeListInstance = appModelService?.app.getAllPSAppCodeLists()?.find((item: IPSAppCodeList) => {
                        return item.codeName == tag;
                    }) as IPSAppCodeList;
                    if (this.codeListInstance) {
                        this.codeListInstance.fill(true).then(() => {
                            this.isEnableCache = this.codeListInstance.enableCache;
                            this.predefinedType = this.codeListInstance.predefinedType || '';
                            this.cacheTimeout = this.codeListInstance.cacheTimeout;
                            this.codelistModel = { codelistid: this.codeListInstance.codeName };
                            this.initUserParams();
                            this.initQueryParams();
                            resolve(true);
                        }).catch((error: any) => {
                            resolve(false);
                        })
                    } else {
                        resolve(false);
                    }
                } else {
                    resolve(false);
                }
            });
        })
    }

    /**
     * 初始化用户参数
     *
     * @memberof DynamicCodeListService
     */
    public initUserParams() {
        this.userParamNames = this.codeListInstance?.userParams || {};
    }

    /**
     * 初始化查询参数
     *
     * @memberof DynamicCodeListService
     */
    public initQueryParams() {
        const minorSortDir = this.codeListInstance?.minorSortDir;
        const sortField = this.codeListInstance?.getMinorSortPSAppDEField()?.codeName;
        if (minorSortDir && sortField) {
            this,this.queryParamNames = { sort: `${sortField.toLowerCase()},${minorSortDir.toLowerCase()}` };
        }
    }

    /**
     * 处理数据
     *
     * @param items 数据
     * @memberof DynamicCodeListService
     */
    public doItems(items: any[]): any[] {
        //是否为子系统代码表
        if (this.codeListInstance.subSysCodeList) {
            return this.doItemsForSubSysCodeList(items);
        } else {
            return this.doItemsForMainSysCodeList(items);
        }
    }

    /**
     * 获取数据项
     *
     * @param {*} data
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DynamicCodeListService
     */
    public getItems(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        //是否为子系统代码表
        if (this.codeListInstance.subSysCodeList) {
            return this.getItemsForSubSysCodeList(context, data, isloading);
        } else {
            return this.getItemsForMainSysCodeList(context, data, isloading);
        }
    }

    /**
     * 处理数据（非子系统代码表）
     * 
     * @param items 
     * @memberof DynamicCodeListService
     */
    public doItemsForMainSysCodeList(items: any[]): any[] {
        const dataEntity = this.codeListInstance.getPSAppDataEntity();
        const dataSet = this.codeListInstance.getPSAppDEDataSet();
        let _items: any[] = [];
        //  存在应用实体和数据集时
        if (dataEntity && dataSet) {
            const valueField = this.codeListInstance.getValuePSAppDEField()?.codeName;
            const textField = this.codeListInstance.getTextPSAppDEField()?.codeName;
            const pValueField = this.codeListInstance.getPValuePSAppDEField()?.codeName;
            const iconPathField = this.codeListInstance.getIconPathPSAppDEField()?.codeName;
            const iconClsField = this.codeListInstance.getIconClsPSAppDEField()?.codeName;
            const keyField = ModelTool.getAppEntityKeyField(dataEntity)?.codeName;
            const majorField = ModelTool.getAppEntityMajorField(dataEntity)?.codeName;
            if (items && items.length > 0) {
                items.forEach((item: any) => {
                    let itemdata: any = {
                        id: valueField ? item[valueField.toLowerCase()] : keyField ? item[keyField.toLowerCase()] : '',
                        value: valueField ? item[valueField.toLowerCase()] : keyField ? item[keyField.toLowerCase()] : '',
                        text: textField ? item[textField.toLowerCase()] : majorField ? item[majorField.toLowerCase()] : '',
                        label: textField ? item[textField.toLowerCase()] : majorField ? item[majorField.toLowerCase()] : '',
                    };
                    if (pValueField) {
                        Object.assign(itemdata, { pvalue: item[pValueField.toLowerCase()] });
                    }
                    // if (iconPathField) {
                    //     Object.assign(itemdata, { pvalue: item[iconPathField.toLowerCase()] });
                    // }
                    // if (iconClsField) {
                    //     Object.assign(itemdata, { pvalue: item[iconClsField.toLowerCase()] });
                    // }
                    _items.push(itemdata);
                })
            }
        }
        return _items;
    }

    /**
     * 处理数据（子系统代码表）
     * 
     * @param items 
     * @memberof DynamicCodeListService
     */
    public doItemsForSubSysCodeList(items: any[]): any[] {
        let _items: any[] = [];
        const dataEntity = this.codeListInstance.getPSAppDataEntity();
        const dataSet = this.codeListInstance.getPSAppDEDataSet();
        //  存在应用实体和数据集时
        if (dataEntity && dataSet) {
            const valueField = this.codeListInstance.getValuePSAppDEField()?.codeName;
            const textField = this.codeListInstance.getTextPSAppDEField()?.codeName;
            const pValueField = this.codeListInstance.getPValuePSAppDEField()?.codeName;
            const keyField = ModelTool.getAppEntityKeyField(dataEntity)?.codeName;
            const majorField = ModelTool.getAppEntityMajorField(dataEntity)?.codeName;
            items.forEach((item: any) => {
                let itemdata: any = {
                    id: valueField ? item[valueField.toLowerCase()] : keyField ? item[keyField.toLowerCase()] : '',
                    value: valueField ? item[valueField.toLowerCase()] : keyField ? item[keyField.toLowerCase()] : '',
                    text: textField ? item[textField.toLowerCase()] : majorField ? item[majorField.toLowerCase()] : ''
                };
                if (pValueField) {
                    Object.assign(itemdata, { pvalue: item[pValueField.toLowerCase()] });
                }
            })
        }
        return _items;
    }

    /**
     * 获取数据项（非子系统代码表）
     * 
     * @param {*} data
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DynamicCodeListService
     */
    public getItemsForMainSysCodeList(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const dataEntity = this.codeListInstance.getPSAppDataEntity();
        const dataSet = this.codeListInstance.getPSAppDEDataSet();
        if (dataEntity && dataSet) {
            return new Promise((resolve, reject) => {
                data = this.handleQueryParam(data);
                ___ibz___.gs.getService(dataEntity.codeName).then((service: any) => {
                    if (service && service[dataSet.codeName] && service[dataSet.codeName] instanceof Function) {
                        const promise: Promise<any> = service[dataSet.codeName](context, data);
                        promise.then((response: any) => {
                            if (response && response.status === 200) {
                                const data =  response.data;
                                resolve(this.doItems(data));
                            } else {
                                resolve([]);
                            }
                        }).catch((response: any) => {
                            console.error(response);
                            reject(response);
                        });
                    }
                }).catch((error: any) => {
                    LogUtil.error(error);
                })
            });
        } else {
            return Promise.reject([]);
        }
    }

    /**
     * 获取数据项（子系统代码表）
     * 
     * @param {*} data
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DynamicCodeListService
     */
    public getItemsForSubSysCodeList(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const dataEntity = this.codeListInstance.getPSAppDataEntity();
        const dataSet = this.codeListInstance.getPSAppDEDataSet();
        if (dataEntity && dataSet) {
            return new Promise((resolve, reject) => {
                data = this.handleQueryParam(data);
                ___ibz___.gs.getService(dataEntity.codeName).then((service: any) => {
                    if (service && service[dataSet.codeName] && service[dataSet.codeName] instanceof Function) {
                        const promise: Promise<any> = service[dataSet.codeName](context, data, isloading);
                        promise.then((response: any) => {
                            if (response && response.status === 200) {
                                const data =  response.data;
                                resolve(this.doItems(data));
                            } else {
                                resolve([]);
                            }
                        }).catch((response: any) => {
                            console.error(response);
                            reject(response);
                        });
                    }
                })
            });
        } else {
            return Promise.reject([]);
        }
    }

    /**
     * 处理查询参数
     * @param data 传入data
     * @memberof DynamicCodeListService
     */
    public handleQueryParam(data:any){
        let tempData:any = data?JSON.parse(JSON.stringify(data)):{};
        if(this.userParamNames && Object.keys(this.userParamNames).length >0){
            Object.keys(this.userParamNames).forEach((name: string) => {
                if (!name) {
                    return;
                }
                let value: string | null = this.userParamNames[name];
                if (value && value.startsWith('%') && value.endsWith('%')) {
                    const key = value.substring(1, value.length - 1);
                    if (this.codelistModel && this.codelistModel.hasOwnProperty(key)) {
                        value = (this.codelistModel[key] !== null && this.codelistModel[key] !== undefined) ? this.codelistModel[key] : null;
                    } else {
                        value = null;
                    }
                }
                Object.assign(tempData, { [name]: value });
            });
        }
        Object.assign(tempData,{page: 0, size: 1000});
        if(this.queryParamNames && Object.keys(this.queryParamNames).length > 0){
            Object.assign(tempData,this.queryParamNames);
        }
        return tempData;
    }
}