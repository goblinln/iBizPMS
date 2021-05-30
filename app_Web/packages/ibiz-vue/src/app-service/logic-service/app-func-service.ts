import { IPSAppDataEntity } from '@ibiz/dynamic-model-api';
import { ViewTool, Util, LogUtil } from 'ibiz-core';

/**
 * 应用功能服务
 *
 * @memberof AppFuncService
 */
export class AppFuncService {
    /**
     * 单例变量声明
     *
     * @memberof AppFuncService
     */
    private static appFuncService: AppFuncService;

    /**
     * 构造 AppFuncService 对象
     *
     * @memberof AppViewLogicService
     */
    constructor() {}

    /**
     * vue对象
     *
     * @memberof AppFuncService
     */
    public v!: any;

    /**
     * 初始化
     *
     * @memberof AppFuncService
     */
    public init(vueInstance: any): void {
        this.v = vueInstance;
    }

    /**
     * 获取 AppFuncService 单例对象
     *
     * @memberof AppFuncService
     */
    public static getInstance() {
        if (!this.appFuncService) {
            this.appFuncService = new AppFuncService();
        }
        return this.appFuncService;
    }

    /**
     * 执行应用功能
     *
     * @memberof AppFuncService
     */
    public async executeApplication(appFunc: any, context: any) {
        if (appFunc) {
            let viewParam: any = {};
            if (appFunc?.getPSAppView) {
                await appFunc.getPSAppView.fill();
            }
            if (appFunc.getPSNavigateContexts) {
                const localContext = Util.formatNavParam(appFunc.getPSNavigateContexts);
                Object.assign(context, localContext);
            }
            if (appFunc.getPSNavigateParams) {
                const localViewParam = Util.formatNavParam(appFunc.getPSNavigateParams);
                Object.assign(viewParam, localViewParam);
            }
            switch (appFunc.appFuncType) {
                case 'APPVIEW':
                    this.openAppView(appFunc, context, viewParam);
                    return;
                case 'OPENHTMLPAGE':
                    this.openHtmlPage(appFunc, context, viewParam);
                    return;
                case 'PDTAPPFUNC':
                    this.openPdAppFunc(appFunc, context, viewParam);
                    return;
                case 'JAVASCRIPT':
                    this.executeJavaScript(appFunc, context, viewParam);
                    return;
                case 'CUSTOM':
                    this.custom(appFunc, context, viewParam);
                    return;
                default:
                  LogUtil.warn('无该应用功能');
            }
        }
    }

    /**
     * 打开应用视图
     *
     * @memberof AppFuncService
     */
    public async openAppView(appFunc: any, context: any, viewparam: any = {}) {
        const appView = appFunc.getPSAppView;
        if (Object.values(appView).length == 0) {
            console.error('未找到应用视图');
            return;
        }
        if (appView.redirectView) {
            this.v.$warning('重定向视图暂不支持应用功能打开','openAppView');
        } else {
            if (appView.openViewParam) {
                Object.assign(viewparam, appView.openViewParam);
            }
            const deResParameters: any[] = [];
            const parameters: any[] = [];
            await this.processingParameter(context, appView, deResParameters, parameters);
            if (appView.openMode && Object.is(appView.openMode, 'INDEXVIEWTAB')) {
                this.openIndexViewTab(context, viewparam, deResParameters, parameters);
            } else if (appView.openMode && Object.is(appView.openMode, 'POPUP')) {
                this.openPopup(viewparam, deResParameters, parameters);
            } else if (appView.openMode && Object.is(appView.openMode, 'POPUPMODAL')) {
                this.openModal(context, viewparam, appView);
            } else if (appView.openMode && Object.is(appView.openMode, 'POPUPAPP')) {
                this.openApp(context, viewparam, deResParameters, parameters);
            } else if (appView.openMode && Object.is(appView.openMode, 'POPOVER')) {
                this.openPopover(context, viewparam, appView);
            } else if (appView.openMode && appView.openMode.indexOf('DRAWER') != -1) {
                this.openDrawer(context, viewparam, appView);
            } else if (appView.openMode && appView.openMode.indexOf('USER') != -1) {
                this.openUser(context, viewparam, deResParameters, parameters);
            } else {
                this.openIndexViewTab(context, viewparam, deResParameters, parameters);
            }
        }
    }

    /**
     * 整合参数
     *
     * @memberof AppFuncService
     */
    public async processingParameter(context: any, appView: any, deResParameters: any[], parameters: any[]) {
        let params = [];
        if (appView.getPSAppDataEntity()) {
            let result: IPSAppDataEntity = appView.getPSAppDataEntity();
            await result.fill();
            if (!result) {
                console.error('未找到应用实体');
                return;
            }
            if (
                (appView.openMode && (appView.openMode == 'INDEXVIEWTAB' || appView.openMode == '')) ||
                !appView.openMode
            ) {
                params = [
                    {
                        pathName: Util.srfpluralize(result.codeName).toLowerCase(),
                        parameterName: result.codeName.toLowerCase(),
                    },
                    { pathName: 'views', parameterName: appView.getPSDEViewCodeName().toLowerCase() },
                ];
            } else {
                params = [
                    {
                        pathName: Util.srfpluralize(result.codeName).toLowerCase(),
                        parameterName: result.codeName.toLowerCase(),
                    },
                ];
            }
        } else {
            params = [{ pathName: 'views', parameterName: appView.name.toLowerCase() }];
        }
        Object.assign(parameters, params);
    }

    /**
     * 顶级分页打开
     *
     * @memberof AppFuncService
     */
    public openIndexViewTab(context: any, viewparam: any, deResParameters: any[], parameters: any[]) {
        if (context && context.srfdynainstid) {
            Object.assign(viewparam, { srfdynainstid: context.srfdynainstid });
        }
        const path: string = ViewTool.buildUpRoutePath(
            this.v.$route,
            context,
            deResParameters,
            parameters,
            [],
            viewparam,
        );
        if (Object.is(this.v.$route.fullPath, path)) {
            return;
        }
        this.v.$nextTick(() => {
            this.v.$router.push(path);
        });
    }

    /**
     * 非模式弹出
     *
     * @memberof AppFuncService
     */
    public openPopup(viewparam: any, deResParameters: any[], parameters: any[]) {
        LogUtil.log('-----POPUP-----非模式弹出，暂时不实现');
    }

    /**
     * 模态打开
     *
     * @memberof AppFuncService
     */
    public openModal(context: any, viewparam: any, appView: any) {
        const view = {
            viewname: 'app-view-shell',
            title: appView.title,
            height: appView.height,
            width: appView.width,
        };
        const appmodal = this.v.$appmodal.openModal(view, Util.deepCopy(context), viewparam);
        appmodal.subscribe((result: any) => {
            LogUtil.log(result);
        });
    }

    /**
     * 独立程序弹出
     *
     * @memberof AppFuncService
     */
    public openApp(context: any, viewparam: any, deResParameters: any[], parameters: any[]) {
        if (context && context.srfdynainstid) {
            Object.assign(viewparam, { srfdynainstid: context.srfdynainstid });
        }
        const routePath = ViewTool.buildUpRoutePath(this.v.$route, context, deResParameters, parameters, [], viewparam);
        window.open('./#' + routePath, '_blank');
    }

    /**
     * 气泡打开
     *
     * @memberof AppFuncService
     */
    public openPopover(context: any, viewparam: any, appView: any) {
        const view = {
            viewname: 'app-view-shell',
            title: appView.title,
            height: appView.height,
            width: appView.width,
            placement: appView.openMode,
        };
        const appPopover = this.v.$apppopover.openPop({}, view, Util.deepCopy(context), viewparam);
        appPopover.subscribe((result: any) => {
            LogUtil.log(result);
        });
    }

    /**
     * 抽屉打开
     *
     * @memberof AppFuncService
     */
    public openDrawer(context: any, viewparam: any, appView: any) {
        const view = {
            viewname: 'app-view-shell',
            title: appView.title,
            height: appView.height,
            width: appView.width,
            placement: appView.openMode,
        };
        const appdrawer = this.v.$appdrawer.openDrawer(view, Util.getViewProps(context, viewparam), {
            viewModelData: appView,
        });
        appdrawer.subscribe((result: any) => {
            LogUtil.log(result);
        });
    }

    /**
     * 用户自定义
     *
     * @memberof AppFuncService
     */
    public openUser(context: any, viewparam: any, deResParameters: any[], parameters: any[]) {
        LogUtil.log('用户自定义，暂时不实现');
    }

    /**
     * 打开HTML页面
     *
     * @memberof AppFuncService
     */
    public openHtmlPage(appFunc: any, context: any, viewparam: any) {
        const url = appFunc.htmlPageUrl;
        window.open(url, '_blank');
    }

    /**
     * 预置应用功能
     *
     * @memberof AppFuncService
     */
    public openPdAppFunc(appFunc: any, context: any, viewparam: any) {
        this.v.$warning('预置应用功能暂不支持','openPdAppFunc');
    }

    /**
     * 执行JS
     *
     * @memberof AppFuncService
     */
    public executeJavaScript(appFunc: any, context: any, viewparam: any) {
        this.v.$warning('执行JS暂不支持','executeJavaScript');
    }

    /**
     * 自定义
     *
     * @memberof AppFuncService
     */
    public custom(appFunc: any, context: any, viewparam: any) {
        this.v.$warning('自定义暂不支持','custom');
    }
}
