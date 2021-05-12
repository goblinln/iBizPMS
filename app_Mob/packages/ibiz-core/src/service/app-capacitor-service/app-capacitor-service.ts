import { Plugins } from '@capacitor/core';
import { is } from 'ramda';
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
     * 视图缓存
     *
     * @type {any[]}
     * @memberof AppCapacitorService
     */
    public viewCache: any[] = [];


    /**
     * 当前操作视图
     *
     * @readonly
     * @type {*}
     * @memberof AppCapacitorService
     */
    get view(): any {
        return this.viewCache.length - 1 >= 0 ? this.viewCache[this.viewCache.length - 1] : null
    }

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
     * 视图初始化
     *
     * @param {*} view 视图
     * @param {boolean} isDeletePrev 是否保留上一个视图
     * @memberof AppCapacitorService
     */
    public viewInit(view: any, isDeletePrev: boolean) {
        if (isDeletePrev) {
            this.viewCache = [];
        }
        this.viewCache.push(view);
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