import { AppServiceBase } from "../app-service/app-base.service";
import { DynamicDataService } from "./dynamic-data-service";

/**
 * 动态服务基类
 * 
 * @memberof DynamicService
 */
export class DynamicService {

    /**
     * 动态模型数据服务
     * @type {DynamicDataService}
     * 
     * @memberof DynamicService
     */
    private dynamicDataService !: DynamicDataService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {DynamicService}
     * @memberof DynamicService
     */
    private static dynamicServiceMap: Map<string, any> = new Map();

    /**
     * 动态模型基准服务
     *
     * @private
     * @type {DynamicService}
     * @memberof DynamicService
     */
    private static dynamicBasicService: DynamicService;

    /**
     * 应用上下文
     *
     * @private
     * @type {*}
     * @memberof DynamicService
     */
    private context: any = {};

    /**
     * 应用视图缓存
     * 
     * @memberof DynamicService
     */
    private appCacheViews: Map<string, any> = new Map();

    /**
     * 应用实体缓存
     * 
     * @memberof DynamicService
     */
    private appCacheDataEntities: Map<string, any> = new Map();

    /**
     * 应用代码表缓存
     * 
     * @memberof DynamicService
     */
    private appCacheCodeLists: Map<string, any> = new Map();

    /**
     * 获取 DynamicService 单例对象
     *
     * @returns {DynamicService}
     * @memberof DynamicService
     */
    public static getInstance(context: any): DynamicService {
        if (!this.dynamicBasicService) {
            this.dynamicBasicService = new DynamicService(context);
        }
        if (!context.srfdynainstid) {
            return this.dynamicBasicService;
        } else {
            if (!DynamicService.dynamicServiceMap.get(context.srfdynainstid)) {
                DynamicService.dynamicServiceMap.set(context.srfdynainstid, new DynamicService(context));
            }
            return DynamicService.dynamicServiceMap.get(context.srfdynainstid);
        }
    }

    /**
     * 初始化DynamicService
     * @param opts 额外参数
     * 
     * @memberof DynamicService
     */
    public constructor(opts: any = {}) {
        this.context = opts;
        this.dynamicDataService = new DynamicDataService(this.context);

    }

    /**
     * 获取视图JSON模型数据
     * 
     * @param path 动态路径
     * @param param 额外参数
     * 
     * @memberof DynamicService
     */
    public async getAppViewModelJsonData(path: string, param?: any) {
        if (!path) {
            console.warn(`获取视图JSON数据异常，JSON路径为空`);
            return;
        }
        try {
            if (this.appCacheViews.get(path)) {
                return this.appCacheViews.get(path);
            } else {
                this.triggerViewLoadingService(this.context.srfsessionid, true);
                let res: any = await this.dynamicDataService.getModelData(path, "VIEW", param);
                return res;
            }
        } catch (error) {
            console.warn(`获取视图JSON数据异常，JSON路径为：${path}`);
            return this.getBasicServiceModelData(path, "VIEW", param);
        } finally{
            this.triggerViewLoadingService(this.context.srfsessionid, false);
        }
    }

    /**
     * 获取代码表JSON模型数据
     * 
     * @param path 动态路径
     * @param param 额外参数
     * 
     * @memberof DynamicService
     */
    public async getAppCodeListJsonData(path: string, param?: any) {
        if (!path) {
            console.warn(`获取代码表JSON数据异常，JSON路径为空`);
            return;
        }
        try {
            if (this.appCacheCodeLists.get(path)) {
                return this.appCacheCodeLists.get(path);
            } else {
                let res: any = await this.dynamicDataService.getModelData(path, "CODELIST", param);
                return res;
            }
        } catch (error) {
            console.warn(`获取代码表JSON数据异常，JSON路径为：${path}`);
            return this.getBasicServiceModelData(path, "CODELIST", param);
        }

    }

    /**
     * 获取应用JSON模型数据
     * 
     * @param path 动态路径
     * @param param 额外参数
     * 
     * @memberof DynamicService
     */
    public async getAppMpdeJsonData(path: string, param?: any) {
        try {
            let appModelData: any = await this.dynamicDataService.getModelData(path, "APP", param);
            this.initCacheModelData(appModelData);
            return appModelData;
        } catch (error) {
            console.warn(`获取应用JSON数据异常，JSON路径为：${path}`);
            return this.getBasicServiceModelData(path, "APP", param);
        }
    }

    /**
     * 获取应用实体JSON模型数据
     * 
     * @param path 动态路径
     * @param param 额外参数
     * 
     * @memberof DynamicService
     */
    public async getAppEntityModelJsonData(path: string, param?: any) {
        if (!path) {
            console.warn(`获取应用实体JSON数据异常，JSON路径为空`);
            return;
        }
        try {
            if (this.appCacheDataEntities.get(path)) {
                return this.appCacheDataEntities.get(path);
            } else {
                let res: any = await this.dynamicDataService.getModelData(path, "ENTITY", param);
                return res;
            }
        } catch (error) {
            console.warn(`获取应用实体JSON数据异常，JSON路径为：${path}`);
            return this.getBasicServiceModelData(path, "ENTITY", param);
        }

    }

    /**
     * 获取应用部件JSON模型数据
     * 
     * @param path 动态路径
     * @param param 额外参数
     * 
     * @memberof DynamicService
     */
    public async getAppCtrlModelJsonData(path: string, param?: any) {
        if (!path) {
            console.warn(`获取部件JSON数据异常，JSON路径为空`);
            return;
        }
        try {
            let res: any = await this.dynamicDataService.getModelData(path, "CTRL", param);
            return res;
        } catch (error) {
            console.warn(`获取部件JSON数据异常，JSON路径为：${path}`);
            return this.getBasicServiceModelData(path, "CTRL", param);
        }
    }

    /**
     * 初始化应用缓存数据
     * 
     * @memberof DynamicService
     */
    private initCacheModelData(opts: any) {
        if (opts.cache && opts.cache.getPSAppViews && opts.cache.getPSAppViews.length > 0) {
            opts.cache.getPSAppViews.forEach((appView: any) => {
                this.appCacheViews.set(appView.dynaModelFilePath, appView);
            });
        }
        if (opts.cache && opts.cache.getPSAppDataEntities && opts.cache.getPSAppDataEntities.length > 0) {
            opts.cache.getPSAppDataEntities.forEach((appEntity: any) => {
                this.appCacheDataEntities.set(appEntity.dynaModelFilePath, appEntity);
            });
        }
        if (opts.getAllPSAppCodeLists && opts.getAllPSAppCodeLists.length > 0) {
            opts.getAllPSAppCodeLists.forEach((codelist: any) => {
                this.appCacheCodeLists.set(codelist.dynaModelFilePath, codelist);
            });
        }
    }

    /**
     * 获取基准服务模型数据
     * 
     * @param path 动态路径
     * @param type 类型
     * @param param 额外参数
     * 
     * @memberof DynamicService
     */
    private async getBasicServiceModelData(path: string, type: string, param?: any): Promise<any> {
        try {
            let dynamicBasicService: DynamicService = DynamicService.dynamicBasicService;
            if (!this.context.srfdynainstid || (this.context.srfdynainstid === dynamicBasicService.context.srfdynainstid)) {
                throw new Error("获取标准模型异常");
            }
            switch (type) {
                case "APP":
                    return await dynamicBasicService.getAppMpdeJsonData(path, param);
                case "VIEW":
                    return await dynamicBasicService.getAppViewModelJsonData(path, param);
                case "CTRL":
                    return await dynamicBasicService.getAppCtrlModelJsonData(path, param);
                case "ENTITY":
                    return await dynamicBasicService.getAppEntityModelJsonData(path, param);
                case "CODELIST":
                    return await dynamicBasicService.getAppCodeListJsonData(path, param);
                default:
                    throw new Error("获取标准模型异常");
            }
        } catch (error) {
            try {
                let localPath: string = this.dynamicDataService.computedTargetPath(type, path);
                return (await this.dynamicDataService.getLocalModelData(localPath))['data'];
            } catch (error) {
                let errorPath: string = this.dynamicDataService.computedTargetPath('ERROR', type);
                return (await this.dynamicDataService.getLocalModelData(errorPath))['data'];
            }
        }
    }

    /**
     * 开关视图加载服务
     *
     * @param {string} srfsessionid 主视图srfsessionid
     * @param {boolean} tag 开关变量，true为开启，false为关闭
     * @memberof DynamicService
     */
    public triggerViewLoadingService(srfsessionid: string, tag: boolean){
        if(!srfsessionid){
            return;
        }
        const appStore: any = AppServiceBase.getInstance().getAppStore();
        let services = appStore.getters['loadingService/getViewLoadingServicesByRefViewPath'](srfsessionid);
        if(services?.length > 0){
            services.forEach((service: any) => {
                tag ? service.beginLoading() : service.endLoading();
            });
        }
    }
}