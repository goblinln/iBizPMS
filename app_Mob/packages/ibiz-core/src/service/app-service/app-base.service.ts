/**
 * 应用服务基类
 *
 * @export
 * @class AppServiceBase
 */
export class AppServiceBase {

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {AppServiceBase}
     * @memberof AppServiceBase
     */
    private static appService: AppServiceBase;

    /**
     * 应用模型数据对象
     *
     * @public
     * @type {(any)}
     * @memberof AppServiceBase
     */
    private static appModelDataObject: any;

    /**
     * 应用存储对象
     *
     * @public
     * @type {(any)}
     * @memberof AppServiceBase
     */
    private static appStore: any;

    /**
     * 应用消息通知中心
     *
     * @public
     * @type {(any)}
     * @memberof AppServiceBase
     */
    private static appMessageCenter: any;

    /**
     * 应用导航中心
     *
     * @public
     * @type {(any)}
     * @memberof AppServiceBase
     */
    private static appNavDataService: any;

    /**
     * 环境配置
     *
     * @public
     * @type {(any)}
     * @memberof AppServiceBase
     */
    private static appEnvironment: any;

    /**
     * 国际化
     *
     * @public
     * @type {(any)}
     * @memberof AppServiceBase
     */
    private static i18n: any;

    /**
     * 路由对象
     *
     * @public
     * @type {(any)}
     * @memberof AppServiceBase
     */
    private static router: any;

    /**
     * 获取AppServiceBase单例对象
     *
     * @static
     * @returns {AppServiceBase}
     * @memberof AppServiceBase
     */
    public static getInstance(): AppServiceBase {
        if (!this.appService) {
            this.appService = new AppServiceBase();
        }
        return this.appService;
    }

    /**
     * 获取应用模型数据对象
     *
     * @public
     * @memberof AppServiceBase
     */
    public getAppModelDataObject() {
        return AppServiceBase.appModelDataObject;
    }

    /**
     * 设置应用模型数据对象
     *
     * @public
     * @memberof AppServiceBase
     */
    public setAppModelDataObject(opts: any) {
        AppServiceBase.appModelDataObject = opts;
    }

    /**
     * 获取应用存储对象
     *
     * @public
     * @memberof AppServiceBase
     */
    public getAppStore() {
        return AppServiceBase.appStore;
    }

    /**
     * 设置应用存储对象
     *
     * @public
     * @memberof AppServiceBase
     */
    public setAppStore(opts: any) {
        AppServiceBase.appStore = opts;
    }

    /**
     * 获取应用消息通知中心
     *
     * @public
     * @memberof AppServiceBase
     */
    public getAppMessageCenter() {
        return AppServiceBase.appMessageCenter;
    }

    /**
     * 设置应用消息通知中心
     *
     * @public
     * @memberof AppServiceBase
     */
    public setAppMessageCenter(opts: any) {
        AppServiceBase.appMessageCenter = opts;
    }

    /**
     * 获取应用导航服务
     *
     * @public
     * @memberof AppServiceBase
     */
    public getAppNavDataService() {
        return AppServiceBase.appNavDataService;
    }

    /**
     * 设置应用导航服务
     *
     * @public
     * @memberof AppServiceBase
     */
    public setAppNavDataService(opts: any) {
        AppServiceBase.appNavDataService = opts;
    }

    /**
     * 获取环境配置对象
     *
     * @public
     * @memberof AppServiceBase
     */
    public getAppEnvironment() {
        return AppServiceBase.appEnvironment;
    }

    /**
     * 设置环境配置对象
     *
     * @public
     * @memberof AppServiceBase
     */
    public setAppEnvironment(opts: any) {
        AppServiceBase.appEnvironment = opts;
    }

    /**
     * 获取国际化信息
     *
     * @public
     * @memberof AppServiceBase
     */
    public getI18n() {
        return AppServiceBase.i18n;
    }

    /**
     * 设置国际化信息
     *
     * @public
     * @memberof AppServiceBase
     */
    public setI18n(opts: any) {
        AppServiceBase.i18n = opts;
    }

    /**
     * 获取全局路由对象
     *
     * @public
     * @memberof AppServiceBase
     */
    public getRouter() {
        return AppServiceBase.router;
    }

    /**
     * 设置全局路由对象
     *
     * @public
     * @memberof AppServiceBase
     */
    public setRouter(opts: any) {
        AppServiceBase.router = opts;
    }

}