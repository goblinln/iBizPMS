import { mergeDeepLeft } from "ramda";

/**
 * 菜单应用功能
 *
 * @export
 * @class IBizAppFuncModel
 */
export class IBizAppFuncModel {

    /**
     * 应用功能模型数据
     * 
     * @memberof IBizAppFuncModel
     */
    protected appFuncModelData: any;

    /**
     * 默认模型数据
     * 
     * @memberof IBizAppFuncModel
     */
    protected defaultOption: any = {};

    /**
     * 应用上下文
     * 
     * @memberof IBizAppFuncModel
     */
    protected context: any = {};

    /**
     * 初始化 IBizAppFuncModel 对象
     * 
     * @param opts 额外参数
     * 
     * @memberof IBizAppFuncModel
     */
    constructor(opts: any, context?: any) {
        this.appFuncModelData = mergeDeepLeft(opts, this.defaultOption);
        this.context = context ? context : {};
    }

    /**
     * 应用功能名称
     * 
     * @memberof IBizAppFuncModel
     */
    get name() {
        return this.appFuncModelData.name;
    }

    /**
     * 应用功能标识
     * 
     * @memberof IBizAppFuncModel
     */
    get appfunctag() {
        return this.appFuncModelData.codeName;
    }

    /**
     * 应用功能类型
     * 
     * @memberof IBizAppFuncModel
     */
    get appFuncType() {
        return this.appFuncModelData.appFuncType;
    }

    /**
     * HTML页面路径
     * 
     * @memberof IBizAppFuncModel
     */
    get htmlPageUrl() {
        return this.appFuncModelData.htmlPageUrl;
    }

    /**
     * 打开方式
     * 
     * @memberof IBizAppFuncModel
     */
    get openMode() {
        return this.appFuncModelData.openMode;
    }

    /**
     * 应用视图
     * 
     * @memberof IBizAppFuncModel
     */
    get getPSAppView() {
        return this.appFuncModelData.getPSAppView;
    }

    /**
     * 导航上下文
     * 
     * @memberof IBizAppFuncModel
     */
    get getPSNavigateContexts(){
        return this.appFuncModelData.getPSNavigateContexts;
    }

    /**
     * 导航参数
     * 
     * @memberof IBizAppFuncModel
     */
    get getPSNavigateParams(){
        return this.appFuncModelData.getPSNavigateParams;
    }
}