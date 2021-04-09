import { mergeDeepLeft } from "ramda";

/**
 * 应用
 */
export class IBizAppModel {

    /**
     * 默认模型数据
     * 
     * @memberof IBizAppModel
     */
    protected defaultOption: any ={};

    /**
     * 应用数据
     * 
     * @memberof IBizAppModel
     */
    protected appData:any;

    /**
     * 初始化 IBizAppModel 对象
     * 
     * @param opts 额外参数
     * 
     * @memberof IBizAppModel
     */
    constructor(opts: any){
        this.appData = mergeDeepLeft(opts,this.defaultOption);
    }

    /**
     * 部署数据标识
     * 
     * @memberof IBizAppModel
     */
    get deployId(){
        return this.appData.deployId;
    }

    /**
     * 是否为移动端应用
     * 
     * @memberof IBizAppModel
     */
    get mobileApp(){
        return this.appData.mobileApp;
    }

    /**
     * 使用服务接口
     * 
     * @memberof IBizAppModel
     */
    get useServiceApi(){
        return this.appData.useServiceApi;
    }

    /**
     * 代码名称
     * 
     * @memberof IBizAppModel
     */
    get codeName(){
        return this.appData.codeName;
    }

    /**
     * 应用模式
     * 
     * @memberof IBizAppModel
     */
    get appMode(){
        return this.appData.appMode;
    }

    /**
     * 应用功能集合
     * 
     * @memberof IBizAppModel
     */
    get getAllPSAppFuncs(){
        return this.appData.getAllPSAppFuncs;
    }

    /**
     * 系统默认应用
     * 
     * @memberof IBizAppModel
     */
    get defaultFlag(){
        return this.appData.defaultFlag;
    }

    /**
     * 服务代码名称
     * 
     * @memberof IBizAppModel
     */
    get serviceCodeName(){
        return this.appData.serviceCodeName;
    }

    /**
     * 应用目录名称
     * 
     * @memberof IBizAppModel
     */
    get appFolder(){
        return this.appData.appFolder;
    }

    /**
     * 代码包名称
     * 
     * @memberof IBizAppModel
     */
    get pKGCodeName(){
        return this.appData.pKGCodeName;
    }

    /**
     * 应用版本
     * 
     * @memberof IBizAppModel
     */
    get appVersion(){
        return this.appData.appVersion;
    }

    /**
     * 流程应用模式
     * 
     * @memberof IBizAppModel
     */
    get wFAppMode(){
        return this.appData.wFAppMode;
    }

    /**
     * 支持动态数据看板
     * 
     * @memberof IBizAppModel
     */
    get enableDynaDashboard(){
        return this.appData.enableDynaDashboard;
    }

    /**
     * 支持搜索条件存储
     * 
     * @memberof IBizAppModel
     */
    get enableFilterStorage(){
        return this.appData.enableFilterStorage;
    }

    /**
     * 应用实体集合
     * 
     * @memberof IBizAppModel
     */
    get getAllPSAppDataEntities(){
        return this.appData.getAllPSAppDataEntities;
    }

    /**
     * 应用视图消息组集合
     * 
     * @memberof IBizAppModel
     */
    get getAllPSAppViewMsgGroups(){
        return this.appData.getAllPSAppViewMsgGroups;
    }

    /**
     * 启用统一认证登录
     * 
     * @memberof IBizAppModel
     */
    get enableUACLogin(){
        return this.appData.enableUACLogin;
    }

    /**
     * 界面行为集合
     * 
     * @memberof IBizAppModel
     */
    get getAllPSAppDEUIActions(){
        return this.appData.getAllPSAppDEUIActions;
    }

    /**
     * 代码表集合
     * 
     * @memberof IBizAppModel
     */
    get getAllPSAppCodeLists(){
        return this.appData.getAllPSAppCodeLists;
    }

    /**
     * 应用视图消息集合
     * 
     * @memberof IBizAppModel
     */
    get getAllPSAppViewMsgs(){
        return this.appData.getAllPSAppViewMsgs;
    }

    /**
     * 应用门户部件分组
     * 
     * @memberof IBizAppModel
     */
    get getAllPSAppPortletCats(){
        return this.appData.getAllPSAppPortletCats;
    }

    /**
     * 应用门户部件集合
     * 
     * @memberof IBizAppModel
     */
    get getAllPSAppPortlets(){
        return this.appData.getAllPSAppPortlets;
    }

    /**
     * 动态模型文件路径
     * 
     * @memberof IBizAppModel
     */
    get dynaModelFilePath(){
        return this.appData.dynaModelFilePath;
    }

    /**
     * 名称
     * 
     * @memberof IBizAppModel
     */
    get name(){
        return this.appData.name;
    }

    /**
     * 缓存
     * 
     * @memberof IBizAppModel
     */
    get cache(){
        return this.appData.cache;
    }
    
    /**
     * 所有视图
     * 
     * @memberof IBizAppModel
     */
     get getAllPSAppViews(){
        return this.appData.getAllPSAppViews;
    }

}