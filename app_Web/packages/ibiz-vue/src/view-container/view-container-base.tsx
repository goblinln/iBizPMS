import Vue from 'vue';
import qs from 'qs';
import { DynamicInstanceConfig, IPSAppView } from '@ibiz/dynamic-model-api';
import { AppServiceBase, GetModelService, SandboxInstance, Util } from 'ibiz-core';
import { AppComponentService, AppNavHistory } from '../app-service';
import { CommunicationService } from '@ibiz/model-location';

/**
 * 视图容器基类
 *
 * @export
 * @class ViewContainerBase
 * @extends {Vue}
 */
export class ViewContainerBase extends Vue {
    /**
     * 部件静态参数
     *
     * @memberof ViewContainerBase
     */
    public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof ViewContainerBase
     */
    public dynamicProps!: any;

    /**
     * 动态模型文件路径
     *
     * @public
     * @type {StringConstructor}
     * @memberof ViewContainerBase
     */
    public dynaModelFilePath: string = '';

    /**
     * 视图标识
     *
     * @type {string}
     * @memberof ViewContainerBase
     */
    public viewtag: string = '';

    /**
     * 视图容器
     *
     * @type {any}
     * @memberof ViewContainerBase
     */
    public viewContainerName: string = '';

    /**
     * 临时动态视图上下文环境参数
     *
     * @type {ViewContext}
     * @memberof ViewBase
     */
    public tempViewContext: any = {};

    /**
     * 动态视图上下文环境参数
     *
     * @type {*}
     * @memberof ViewBase
     */
    public viewContext: any = {};

    /**
     * 模型数据实例
     *
     * @type {boolean}
     * @memberof ViewContainerBase
     */
    public modeldata!: IPSAppView;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof ViewContainerBase
     */
    public context: any = {};

    created(): void {
        const env = AppServiceBase.getInstance().getAppEnvironment();
        if (env.devMode) {
            this.selfPreview = this.selfPreview.bind(this);
            const m = CommunicationService.getInstance();
            m.evt.on('preview-view', this.selfPreview);
        }
    }

    destroyed(): void {
        const env = AppServiceBase.getInstance().getAppEnvironment();
        if (env.devMode) {
            const m = CommunicationService.getInstance();
            m.evt.off('preview-view', this.selfPreview);
        }
    }

    protected async selfPreview(res: any): Promise<void> {
        const model = res.model;
        if (model.dynaModelFilePath === this.dynaModelFilePath) {
            const s = await GetModelService(this.context);
            s.store.modelObject.delete(model.dynaModelFilePath);
            const view = await s.getPSAppView(model);
            this.initViewContext({ modeldata: view });
            this.viewContainerName = '';
            setTimeout(() => {
                this.viewContainerName = AppComponentService.getViewComponents(
                    view?.viewType,
                    view?.viewStyle,
                    view?.getPSSysPFPlugin?.pluginCode,
                );
                this.$forceUpdate();
            }, 100);
            this.$forceUpdate();
        }
    }

    /**
     * 视图容器初始化
     *
     * @memberof ViewContainerBase
     */
    public async ViewContainerInit() {
        if (this.dynamicProps && this.dynamicProps.viewdata) {
            if (typeof this.dynamicProps.viewdata == 'string') {
                this.context = JSON.parse(this.dynamicProps.viewdata);
            } else {
                this.context = Util.deepCopy(this.dynamicProps.viewdata);
            }
            // 初始化沙箱实例
            if (this.context && this.context.hasOwnProperty('srfsandboxtag')) {
                await this.initSandBoxInst(this.context);
            }
        }
        await this.computeDynaModelFilePath();
        // 路由打开
        if (this.$route && this.$route.fullPath && this.$route.fullPath.indexOf('?') > -1) {
            let tempViewParam: any = {};
            const tempViewparam: any = this.$route.fullPath.slice(this.$route.fullPath.indexOf('?') + 1);
            const viewparamArray: Array<string> = decodeURIComponent(tempViewparam).split(';');
            if (viewparamArray.length > 0) {
                viewparamArray.forEach((item: any) => {
                    Object.assign(tempViewParam, qs.parse(item));
                });
            }
            // 初始化沙箱实例
            if (tempViewParam && tempViewParam.hasOwnProperty('srfsandboxtag')) {
                await this.initSandBoxInst(tempViewParam);
            }
            if (tempViewParam.srfinsttag && tempViewParam.srfinsttag2) {
                let dynainstParam: DynamicInstanceConfig = (await GetModelService({ srfsandboxtag: tempViewParam.srfsandboxtag, instTag: tempViewParam.srfinsttag, instTag2: tempViewParam.srfinsttag2 })).getDynaInsConfig();
                this.context = { srfdynainstid: dynainstParam.id };
            }
            if (tempViewParam.srfdynainstid) {
                this.context = { srfdynainstid: tempViewParam.srfdynainstid };
            }
            // 补充沙箱实例参数（路由）
            if (tempViewParam && tempViewParam.hasOwnProperty('srfsandboxtag')) {
                Object.assign(this.context, { 'srfsandboxtag': tempViewParam.srfsandboxtag });
            }
        }
        this.loadDynamicModelData();
    }

    /**
     * 初始化沙箱实例
     *
     * @memberof ViewContainerBase
     */
    public async initSandBoxInst(args: any) {
        if (args && args.srfsandboxtag) {
            const tempSandboxInst: SandboxInstance = new SandboxInstance(args);
            await tempSandboxInst.initSandBox();
        }
    }

    /**
     * 加载动态模型数据
     *
     * @type {Array<*>}
     * @memberof ViewContainerBase
     */
    public async loadDynamicModelData() {
        if (this.staticProps && this.staticProps.viewModelData) {
            this.modeldata = this.staticProps.viewModelData;
        } else {
            if (this.dynaModelFilePath) {
                this.modeldata = await ((await GetModelService(this.context)).getPSAppView(this.dynaModelFilePath)) as IPSAppView;
            }
        }
        //  未找到模型数据跳转404页面
        if (Util.isEmpty(this.modeldata)) {
            this.$router.push('/404');
            return;
        }
        // 视图壳加载视图数据
        await this.modeldata?.fill?.(true);
        this.initViewContext({ modeldata: this.modeldata });
        this.initViewMateInfo(this.modeldata);
        this.viewContainerName = AppComponentService.getViewComponents(
            this.modeldata?.viewType,
            this.modeldata?.viewStyle,
            this.modeldata?.getPSSysPFPlugin()?.pluginCode,
        );
        this.$forceUpdate();
    }

    /**
     * 初始化动态视图上下文环境参数
     *
     * @type {*} opts
     * @memberof ViewContainerBase
     */
    public initViewContext(opts: any) {
        let temp: any = {};
        Object.defineProperty(temp, 'modeldata', { enumerable: false, writable: true });
        Object.assign(temp, this.tempViewContext, opts, {
            viewtag: this.viewtag,
            viewcontainer: this.context,
            ...this.staticProps,
        });
        // 删除viewModelData，避免递归
        if (temp.viewModelData) {
            delete temp.viewModelData;
        }
        this.viewContext = temp;
    }

    /**
     * 初始化视图容器元数据
     *
     * @type {*} opts
     * @memberof ViewContainerBase
     */
    public initViewMateInfo(opts: any) {
        if (!this.dynamicProps || !this.dynamicProps.viewdata) {
            const initNavData: Function = () => {
                if (this.$route.meta && !this.$route.meta.ignoreAddPage) {
                    let navHistory: AppNavHistory = AppServiceBase.getInstance().getAppNavDataService();
                    if (!navHistory) {
                        AppServiceBase.getInstance().setAppNavDataService(new AppNavHistory);
                        navHistory = AppServiceBase.getInstance().getAppNavDataService();
                    }
                    navHistory.add(this.$route);
                }
            }
            // 设置路由meta数据
            let activedView: any = this.$route.meta.parameters.find((item: any) => {
                return item.pathName === 'views';
            });
            if (Object.is(activedView.parameterName, 'view') && Object.is(activedView.pathName, 'views')) {
                this.$route.meta.captionTag = opts.getCapPSLanguageRes()?.lanResTag;
                this.$route.meta.caption = opts?.caption;
                this.$route.meta.imgPath = opts?.getPSSysImage()?.imagePath;
                this.$route.meta.iconCls = opts?.getPSSysImage()?.cssClass;
                if (opts.accUserMode && ((opts.accUserMode == 0) || (opts.accUserMode == 3))) {
                    this.$route.meta.requireAuth = false;
                } else {
                    this.$route.meta.requireAuth = true;
                }
                this.$store.commit("setCurPageCaption", {
                    route: this.$route,
                    caption: this.$route.meta.caption,
                    info: '',
                });
                initNavData();
            } else {
                initNavData();
            }
        }
    }

    /**
     * 处理部件事件
     *
     * @memberof ViewContainerBase
     */
    public handleViewEvent(opts: any) {
        if (opts.action) {
            this.$emit(opts.action, opts.data ? opts.data : null, opts.viewName);
        }
    }

    /**
     * 计算视图动态路径
     *
     * @memberof ViewContainerBase
     */
    public async computeDynaModelFilePath() {
        if (this.dynamicProps && this.dynamicProps.viewdata) {
            // 嵌入视图
            if (this.context.viewpath) {
                this.dynaModelFilePath = this.context.viewpath;
                delete this.context.viewpath;
            }
        } else {
            // 路由打开
            if (this.$route && this.$route.meta && this.$route.meta.parameters) {
                let resource: string = this.$route.meta.resource ? this.$route.meta.resource.toLowerCase() : '';
                let activedView: any = this.$route.meta.parameters.find((item: any) => {
                    return item.pathName === 'views';
                });
                let localActivedView: any = Util.deepCopy(activedView);
                if (Object.is(localActivedView.parameterName, 'view') && Object.is(localActivedView.pathName, 'views')) {
                    localActivedView.parameterName = this.parseUrlDynamicParam().view;
                }
                if (localActivedView && localActivedView.parameterName) {
                    const path = (await GetModelService(this.context)).getPSAppViewPath(`${resource}${localActivedView.parameterName}`);
                    if (path) {
                        this.dynaModelFilePath = path;
                    }
                }
            }
        }
    }

    /**
     * 解析路由动态参数
     *
     * @memberof ViewContainerBase
     */
    public parseUrlDynamicParam(): any {
        const path = (this.$route.matched[this.$route.matched.length - 1]).path;
        const keys: Array<any> = [];
        const curReg = this.$pathToRegExp.pathToRegexp(path, keys);
        const matchArray = curReg.exec(this.$route.path);
        let tempValue: Object = {};
        keys.forEach((item: any, index: number) => {
            if (matchArray[index + 1]) {
                Object.defineProperty(tempValue, item.name, {
                    enumerable: true,
                    value: decodeURIComponent(matchArray[index + 1])
                });
            }
        });
        return tempValue;
    }
}
