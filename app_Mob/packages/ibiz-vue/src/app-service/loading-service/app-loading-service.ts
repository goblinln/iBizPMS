import { LoadingServiceBase } from './loading-service-base';

/**
 * 应用加载服务类
 *
 * @export
 * @class AppLoadingService
 * @extends {LoadingServiceBase}
 */
export class AppLoadingService extends LoadingServiceBase {

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {AppLoading}
     * @memberof AppLoadingService
     */
    private static appLoading: AppLoadingService;

    /**
     * 获取AppLoading单例对象
     *
     * @static
     * @returns {AppLoadingService}
     * @memberof AppLoadingService
     */
    public static getInstance(): AppLoadingService {
        if (!this.appLoading) {
            this.appLoading = new AppLoadingService();
        }
        return this.appLoading;
    }


    /**
     * 统计加载
     *
     * @type {number}
     * @memberof AppLoadingService
     */
    private loadingCount: number = 0;


    /**
     * 加载结束
     *
     * @public
     * @memberof AppLoadingService
     */
    public endLoading(): void {
        const body = document.querySelector('body');
        if (this.loadingCount > 0) {
            this.loadingCount--;
        }
        if (this.loadingCount === 0) {
            super.endLoading(body);
        }
    }

    /**
     * 应用加载
     *
     * @public
     * @memberof AppLoadingService
     */
    public beginLoading() {
        const body = document.querySelector('body');
        if (this.loadingCount === 0) {
            super.beginLoading(body)
        }
        this.loadingCount++;
    }

}