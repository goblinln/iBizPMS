import { Plugins } from '@capacitor/core';
import { Util } from '../../utils';
const { App } = Plugins;


export class AppCapacitorService {

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {AppCapacitorService}
     * @memberof AppCapacitorService
     */
    private static AppCapacitorService: AppCapacitorService;

    /**
     * 视图
     *
     * @type {*}
     * @memberof AppCapacitorService
     */
    public view: any;

    /**
     * Creates an instance of AppCapacitorService.
     * @memberof AppCapacitorService
     */
    constructor() { }

    /**
     * 获取AppServiceBase单例对象
     *
     * @static
     * @returns {AppCapacitorService}
     * @memberof AppCapacitorService
     */
    public static getInstance(): AppCapacitorService {
        if (!this.AppCapacitorService) {
            this.AppCapacitorService = new AppCapacitorService();
            this.AppCapacitorService.doBackEvent();
        }
        return this.AppCapacitorService;
    }

    /**
     * 设置视图
     *
     * @param {*} view
     * @memberof AppCapacitorService
     */
    public viewInit(view: any) {
        this.view = view;
    }

    /**
     * 手机返回事件
     *
     * @memberof AppCapacitorService
     */
    private doBackEvent() {
        App.addListener('backButton', (_state: any) => {
            if (this.view && Util.isFunction(this.view.backFunction)) {
                this.view.backFunction();
            }
        });
    }

    /**
     * 移除所有监听事件
     *
     * @memberof AppCapacitorService
     */
    public removeAllListeners() {
        App.removeAllListeners();
    }


    /**
     * 退出应用
     *
     * @memberof AppCapacitorService
     */
    public exitApp() {
        this.removeAllListeners();
        App.exitApp();
    }

}