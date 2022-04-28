import { Loading } from 'element-ui';
import { ElLoadingComponent } from 'element-ui/types/loading';
import { ViewLoadingService } from './view-loading-service';

/**
 * 部件加载服务类
 *
 * @export
 * @class CtrlLoadingService
 * @extends {LoadingServiceBase}
 */
export class CtrlLoadingService {
    /**
     * 视图loading服务
     *
     * @type {*}
     * @memberof CtrlLoadingService
     */
    viewLoadingService: ViewLoadingService;

    /**
     * 部件加载构造函数。
     * @memberof CtrlLoadingService
     */
    constructor(ViewLoadingService: any) {
        this.viewLoadingService = ViewLoadingService;
    }

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
     * 计算部件元素Id
     *
     * @private
     * @memberof CtrlLoadingService
     */
    private calcCtrlId(model: any) {
        return `#${model?.getPSAppDataEntity?.()?.codeName + model?.codeName}control`;
    }

    /**
     * 部件加载
     *
     * @public
     * @memberof CtrlLoadingService
     */
    public beginLoading(controlId: string) {
        const selection: any = document.querySelector(`#${controlId}`);
        if (!selection || this.isLoading) {
            return;
        }
        this.elLoadingComponent = Loading.service({
            fullscreen: true,
            target: selection,
            customClass: 'app-loading',
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
                        </div>`;
        const loadingEle = selection.lastChild;
        if (loadingEle) {
            loadingEle.innerHTML = userEle;
        }

        // 开启视图loading
        if (this.viewLoadingService) {
            this.viewLoadingService.beginLoading();
        }
    }

    /**
     * 部件加载2 （根据传入Key获取Dom，进行局部刷新）
     *
     * @public
     * @memberof CtrlLoadingService
     */
    public beginLoading2(key: string) {
        const selection: any = document.querySelector(key);
        if (!selection || this.isLoading) {
            return;
        }
        this.elLoadingComponent = Loading.service({
            fullscreen: false,
            target: selection,
            customClass: 'app-loading',
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
                        </div>`;
        const loadingEle = selection.lastChild;
        if (loadingEle) {
            loadingEle.innerHTML = userEle;
        }
        // 开启视图loading
        if (this.viewLoadingService) {
            this.viewLoadingService.beginLoading();
        }
    }

    /**
     * 加载结束
     *
     * @memberof CtrlLoadingService
     */
    public endLoading() {
        if (!this.isLoading) {
            return;
        }
        this.elLoadingComponent.close();
        this.isLoading = false;
        // 关闭视图loading
        if (this.viewLoadingService) {
            this.viewLoadingService.endLoading();
        }
    }
}
