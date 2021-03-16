import { Loading } from 'element-ui';
import { ElLoadingComponent } from 'element-ui/types/loading';

/**
 * 加载服务基类
 *
 * @export
 * @class LoadingServiceBase
 */
export class LoadingServiceBase {

    /**
     * loading 对象
     *
     * @type {(ElLoadingComponent | any)}
     * @memberof LoadingServiceBase
     */
    public elLoadingComponent: ElLoadingComponent | any;

    /**
     * 是否加载
     *
     * @type {boolean}
     * @memberof LoadingServiceBase
     */
    public isLoading: boolean = false;

    /**
     * 加载结束
     *
     * @public
     * @memberof LoadingServiceBase
     */
    public endLoading(): void {
        if (!this.isLoading) {
            return
        }
        this.elLoadingComponent.close();
        this.isLoading = false;
    }

    /**
     * 开始加载
     *
     * @public
     * @memberof LoadingServiceBase
     */
    public beginLoading(selector: any): void {
        this.elLoadingComponent = Loading.service({
            fullscreen: true,
            target: selector,
            customClass: 'app-loading'
        });
        this.isLoading = true;
        // 自定义loading元素
        const userEle = `<div class="app-loading-icon">
                        <div class="icon-content">
                            <div class="app-loading-icon-item active-color"></div>
                            <div class="app-loading-icon-item"></div>
                        </div>
                        <div class="icon-content">
                            <div class="app-loading-icon-item"></div>
                            <div class="app-loading-icon-item"></div>
                        </div>
                    </div>`
        const loadingEle = selector.lastChild;
        if (loadingEle) {
            loadingEle.innerHTML = userEle;
        }
    }
}