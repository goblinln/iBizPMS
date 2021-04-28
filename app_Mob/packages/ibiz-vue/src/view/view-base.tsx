import Vue from 'vue';
import { Subject, Subscription } from 'rxjs';
import { Util, ViewTool, ThirdPartyService, ViewContext, ViewState, AppServiceBase, GetModelService, AppModelService, ModelTool } from 'ibiz-core';
import { CounterServiceRegister } from 'ibiz-service';
import { IPSAppView, IPSControl, IPSAppDEView } from '@ibiz/dynamic-model-api';
/**
 * 视图基类
 *
 * @export
 * @class ViewBase
 * @extends {Vue}
 */
export class ViewBase extends Vue {

    /**
     * 环境文件
     * 
     * @type {any}
     * @protected
     * @memberof ViewBase
     */
    protected Environment: any = AppServiceBase.getInstance().getAppEnvironment();

    /**
     * 传入视图上下文
     *
     * @type {any}
     * @memberof ViewBase
     */
    public _context!: any;

    /**
     * 传入视图参数
     *
     * @type {any}
     * @memberof ViewBase
     */
    public _viewparams!: any;

    /**
     * 是否为子视图
     *
     * @type {boolean}
     * @memberof ViewBase
     */
    public isChildView: boolean = false;

    /**
     * 视图默认使用(路由：true,非路由：false)
     *
     * @type {string}
     * @memberof ViewBase
     */
    public viewDefaultUsage!: string;

    /**
     * 视图传递对象
     *
     * @type {Subject}
     * @memberof ViewBase
     */
    public viewState: Subject<any> = new Subject();

    /**
      * 界面关系通讯对象
      *
      * @type {Subject<ViewState>}
      * @memberof IndexEntityGridViewBase
      */
    public formDruipartState?: Subject<ViewState>;

    /**
     * 模型数据
     *
     * @type {boolean}
     * @memberof ViewBase
     */
    public modelData: any;

    /**
     * 导航模式（tab分页、路由）
     *
     * @type {*}
     * @memberof ViewBase
     */
    public navModel: any;

    /**
     * 实体服务对象
     *
     * @type {*}
     * @memberof ViewBase
     */
    public appEntityService: any;

    /**
     * 应用导航服务
     *
     * @type {*}
     * @memberof ViewBase
     */
    public navDataService: any;

    /**
     * 实体UI服务对象
     *
     * @type {*}
     * @memberof ViewBase
     */
    public appUIService: any;

    /**
     * 视图codeName
     *
     * @type {string}
     * @memberof ViewBase
     */
    public viewCodeName!: string;

    /**
     * 视图标识
     *
     * @type {string}
     * @memberof ViewBase
     */
    public viewtag: string = '';

    /**
     * 自定义视图导航上下文集合
     *
     * @type {*}
     * @memberof ViewBase
     */
    public customViewNavContexts: any = {};

    /**
     * 自定义视图导航参数集合
     *
     * @type {*}
     * @memberof ViewBase
     */
    public customViewParams: any = {};

    /**
     * 视图模型数据
     *
     * @type {*}
     * @memberof ViewBase
     */
    public model: any = {};

    /**
     * 容器模型
     *
     * @type {*}
     * @memberof ViewBase
     */
    public containerModel: any = {}

    /**
    * 视图状态订阅对象
    *
    * @public
    * @type {(Subscription | undefined)}
    * @memberof ViewBase
    */
    public serviceStateEvent: Subscription | undefined;

    /**
     * 门户部件状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof ViewBase
     */
    public panelStateEvent: Subscription | undefined;

    /**
     * 门户部件状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof ViewBase
     */
    public formDruipartStateEvent: Subscription | undefined;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof ViewBase
     */
    public context: any = {};

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof ViewBase
     */
    public viewparams: any = {};

    /**
     * 门户部件状态对象
     *
     * @type {*}
     * @memberof MainViewBase
     */
    public panelState?: any;

    /**
     * 视图缓存数据
     *
     * @type {*}
     * @memberof ViewBase
     */
    public viewCacheData: any;

    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof ViewBase
     */
    public counterServiceArray: Array<any> = [];

    /**
     * 视图实例
     * 
     * @memberof ViewBase
     */
    public viewInstance!: IPSAppView;

    /**
     * 模型数据是否加载完成
     * 
     * @memberof ViewBase
     */
    public viewIsLoaded: boolean = false;

    /**
     * 工具栏模型数据
     *
     * @protected
     * @type {boolean}
     * @memberof ViewBase
     */
    protected toolbarModels: any;

    /**
     * 视图动态参数
     *
     * @type {*}
     * @memberof ViewBase
     */
    public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {*}
     * @memberof ViewBase
     */
    public staticProps!: any;

    /**
     * 底部按钮样式
     * 
     * @memberof ViewBase
     */
    public button_style = "";

    /**
     * 挂载状态集合
     *
     * @type {Map<string,boolean>}
     * @memberof ControlBase
     */
    public mountedMap: Map<string, boolean> = new Map();

    /**
     * 是否视图已经完成viewMounted
     *
     * @type {boolean}
     * @memberof ControlBase
     */
    public hasViewMounted: boolean = false;

    /**
     * 模型服务
     *
     * @type {AppModelService}
     * @memberof ControlBase
     */
    public modelService !: AppModelService;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ViewBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        // 处理viewparam
        if (newVal._viewparams && newVal._viewparams !== oldVal?._viewparams) {
            this._viewparams = newVal._viewparams;
            if (typeof this._viewparams == 'string') {
                this.viewparams = JSON.parse(this._viewparams);
            } else {
                this.viewparams = Util.deepCopy(this._viewparams);
            }
        }
        // 处理_context
        if (newVal._context && newVal._context !== oldVal?._context) {
            const _this: any = this;
            this._context = newVal._context;
            this.parseViewParam();
            if (this.viewIsLoaded) {
                setTimeout(() => {
                    if (_this.engine && _this.engine.view) {
                        _this.engine.load();
                    } else if (_this.refresh && _this.refresh instanceof Function) {
                        _this.refresh();
                    }
                }, 0);
            }
        }
    }

    /**
     * 初始化挂载状态集合
     *
     * @memberof ControlBase
     */
    public initMountedMap() {
        let controls = this.viewInstance?.getPSControls?.();
        controls?.forEach((item: any) => {
            // searchForm后续需要删除
            if (item.controlType == "TOOLBAR" || item.controlType == "SEARCHBAR" || item.controlType == "SEARCHFORM") {
                this.mountedMap.set(item.name, true);
            } else {
                this.mountedMap.set(item.name, false);
            }
        })
        this.mountedMap.set('self', false);
    }

    /**
     * 设置已经绘制完成状态
     *
     * @memberof ViewBase
     */
    public setIsMounted(name: string = 'self') {
        this.mountedMap.set(name, true);
        if ([...this.mountedMap.values()].indexOf(false) == -1) {
            // 执行viewMounted
            if (!this.hasViewMounted) {
                this.$nextTick(() => {
                    this.viewMounted();
                })
            }
        }
    }

    /**
     * 视图激活
     *
     * @memberof ViewBase
     */
    public viewActived() {
        this.thirdPartyInit();
    }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ViewBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.beforeViewModelInit(newVal);
        // 初始化时需要计算context和viewparams
        this.viewModelInit().then((res: any) => {
            this.viewInit();
            this.viewIsLoaded = true;
            setTimeout(() => {
                this.setIsMounted();
            }, 0);
        });
    }

    /**
     * 执行初始化视图模型实例前逻辑
     * 
     * @param data 静态数据
     * @memberof ViewBase
     */
    public beforeViewModelInit(data: any) {
        this.viewDefaultUsage = data.hasOwnProperty('viewDefaultUsage') ? this.staticProps.viewDefaultUsage : 'routerView';
        this.navDataService = data.navDataService;
        this.panelState = data.panelState;
        this.viewtag = data.viewtag;
        this.viewInstance = data?.modeldata;
        this.formDruipartState = data.formDruipartState;
        this.isChildView = data.isChildView ? data.isChildView : this.isChildView;
        const modelData: any = data.modeldata;
        this.customViewNavContexts = this.viewInstance.getPSAppViewNavContexts() ? this.viewInstance.getPSAppViewNavContexts() : [];
        this.customViewParams = this.viewInstance.getPSAppViewNavParams() ? this.viewInstance.getPSAppViewNavParams() : [];
        this.viewCodeName = modelData?.codeName;
    }

    /**
     * 视图模型数据初始化实例
     *
     * @memberof ViewBase
     */
    public async viewModelInit() {
        try {
            await this.initModelService();
            await this.viewModelLoad()
            // 初始化时需要计算context和viewparams
            this.parseViewParam();
            if (this.staticProps && this.staticProps.modeldata) {
                await this.initCounterService(this.staticProps.modeldata);
                await this.initAppUIService();
            }
        } catch (error) {
            console.warn(error);
        }
    }

    /**
     * 视图模型数据加载
     *
     * @memberof ViewBase
     */
    public async viewModelLoad() {
        // 视图部件数据加载
        if (this.viewInstance.getPSControls()) {
            for (const control of this.viewInstance.getPSControls() as IPSControl[]) {
                await control.fill();
            }
        }
        this.initMountedMap();
        await this.viewInstance?.getPSAppDataEntity?.()?.fill();
        // await this.viewInstance.fill(true);
    }

    /**
     * 初始化计数器服务
     * 
     * @memberof ViewBase
     */
    public async initCounterService(param: any) {
        const appCounterRef = this.viewInstance.getPSAppCounterRefs();
        if (appCounterRef && appCounterRef.length > 0) {
            for (const counterRef of appCounterRef) {
                const path = counterRef.getPSAppCounter()?.modelPath;
                const targetCounterService: any = await CounterServiceRegister.getInstance().getService({ context: this.context, viewparams: this.viewparams }, path);
                const tempData: any = { id: counterRef.id, path: path, service: targetCounterService };
                this.counterServiceArray.push(tempData);
            }
        }
    }

    /**
     * 初始化应用界面服务
     * 
     * @memberof ViewBase
     */
    public async initAppUIService() { }

    /**
     * 初始化模型服务
     *
     * @memberof ViewBase
     */
    public async initModelService() {
        let _this: any = this;
        let tempContext: any = {};
        if (AppServiceBase.getInstance()) {
            this.mergeAppData(AppServiceBase.getInstance().getAppStore().getters.getAppData());
        }
        if (!(this.viewDefaultUsage === "routerView") && _this._context && !Object.is(_this._context, '')) {
            if (typeof _this._context == 'string') {
                Object.assign(tempContext, JSON.parse(_this._context));
            } else {
                tempContext = Util.deepCopy(_this._context);
            }
        } else {
            const path = (_this.$route.matched[_this.$route.matched.length - 1]).path;
            const keys: Array<any> = [];
            const curReg = _this.$pathToRegExp.pathToRegexp(path, keys);
            const matchArray = curReg.exec(_this.$route.path);
            let tempValue: Object = {};
            keys.forEach((item: any, index: number) => {
                if (matchArray[index + 1]) {
                    Object.defineProperty(tempValue, item.name, {
                        enumerable: true,
                        value: decodeURIComponent(matchArray[index + 1])
                    });
                }
            });
            ViewTool.formatRouteParams(tempValue, _this.$route, tempContext, _this.viewparams);
            if (_this.viewparams && _this.viewparams.srfdynainstid) {
                Object.assign(tempContext, { srfdynainstid: this.viewparams.srfdynainstid });
            }
            if (_this.viewparams.srfinsttag && _this.viewparams.srfinsttag2) {
                Object.assign(tempContext, { instTag: _this.viewparams.srfinsttag, instTag2: _this.viewparams.srfinsttag2 });
            }
        }
        this.modelService = await GetModelService(tempContext);
    }

    /**
     * 初始化视图标题数据
     *
     * @param {*} result
     * @memberof ViewBase
     */
    public initModel(view: IPSAppView) {
        if (!view) {
            return;
        }
        this.model = { dataInfo: '' };
        this.model.srfTitle = view?.title;
        this.model.srfSubCaption = view?.subCaption;
        if (view?.getPSAppDataEntity()) {
            this.model.srfCaption = `${view?.getPSAppDataEntity()?.codeName?.toLowerCase()}.views.${(view as IPSAppDEView).getPSDEViewCodeName()?.toLowerCase()}.caption`;
            this.model.viewname = `${view?.getPSAppDataEntity()?.codeName?.toLowerCase()}.${(view as IPSAppDEView).getPSDEViewCodeName()?.toLowerCase()}`;
        } else {
            this.model.srfCaption = `app.views.${view.codeName?.toLowerCase()}.caption`;
            this.model.viewname = `app.views.${view.codeName?.toLowerCase()}`;
        }
    }

    /**
     * 第三方容器初始化
     * 
     * @memberof ViewBase
     */
    protected thirdPartyInit() {
        if (!this.isChildView) {
            ThirdPartyService.getInstance().viewInit(this);
        }
    }

    /**
     *  视图初始化
     *
     * @memberof ViewBase
     */
    public viewInit() {
        let _this: any = this;
        this.initModel(this.viewInstance)
        this.viewtag = Util.createUUID();
        this.$store.commit("viewaction/createdView", {
            viewtag: this.viewtag,
            secondtag: this.viewtag
        });
        if (_this.navDataService) {
            _this.serviceStateEvent = _this.navDataService.serviceState.subscribe(({ action, name, data }: { action: string, name: any, data: any }) => {
                if (!Object.is(name, this.viewCodeName)) {
                    return;
                }
                if (Object.is(action, 'viewrefresh')) {
                    _this.$nextTick(() => {
                        _this.parseViewParam(data);
                        if (_this.engine) {
                            _this.engine.load();
                        }
                    });
                }
            });
        }
        if (_this.panelState) {
            _this.panelStateEvent = _this.panelState.subscribe((res: any) => {
                if (Object.is(res.action, 'refresh') && _this.refresh && _this.refresh instanceof Function) {
                    this.refresh();
                }
                if (Object.is(res.action, 'load')) {
                    if (_this.engine) {
                        _this.engine.load();
                    }
                }
                if (Object.is(res.action, 'save')) {
                    _this.viewState.next({ tag: _this?.viewInstance?.xDataControlName, action: res.action, data: _this.viewparams });
                }
            })
        }
        if (_this.formDruipartState) {
            _this.formDruipartStateEvent = _this.formDruipartState.subscribe(({ action }: any) => {
                if (Object.is(action, 'save')) {
                    if (_this.viewInstance) {
                        if (_this.viewInstance.xDataControlName) {
                            _this.viewState.next({ tag: _this.viewInstance.xDataControlName, action: action, data: _this.viewparams });
                        } else {
                            _this.$emit('view-event', { viewName: this.viewCodeName, action: 'drdatasaved', data: {} });
                        }
                    }
                }
                if (Object.is(action, 'load')) {
                    if (_this.engine) {
                        _this.engine.load();
                    }
                }
            });
        }
    }

    /**
     *  视图挂载
     *
     * @memberof ViewBase
     */
    public viewMounted() {
        const _this: any = this;
        // 设置挂载状态
        _this.hasViewMounted = true;
        _this.$emit('view-event', { viewname: this.viewInstance.name, action: 'viewIsMounted', data: true })
        if (_this.engine) {
            _this.engineInit();
        }
        if (_this.loadModel && _this.loadModel instanceof Function) {
            _this.loadModel();
        }
        _this.$emit('view-event', { viewName: this.viewCodeName, action: 'viewLoaded', data: null });
    }

    /**
     *  视图销毁
     *
     * @memberof ViewBase
     */
    public viewDestroyed() {
        if (this.viewDefaultUsage) {
            let localStoreLength = Object.keys(localStorage);
            if (localStoreLength.length > 0) {
                localStoreLength.forEach((item: string) => {
                    if (item.startsWith(this.context.srfsessionid)) {
                        localStorage.removeItem(item);
                    }
                })
            }
            if (Object.is(this.navModel, "tab")) {
                this.navDataService.removeNavDataByTag(this.viewtag);
            }
            if (this.serviceStateEvent) {
                this.serviceStateEvent.unsubscribe();
            }
        }
        // 销毁计数器定时器
        if (this.counterServiceArray && this.counterServiceArray.length > 0) {
            this.counterServiceArray.forEach((item: any) => {
                if (item?.service?.destroyCounter && item.service.destroyCounter instanceof Function) {
                    item.service.destroyCounter();
                }
            })
        }
        if (this.panelStateEvent) {
            this.panelStateEvent.unsubscribe();
        }
        if (this.formDruipartStateEvent) {
            this.formDruipartStateEvent.unsubscribe();
        }
    }

    /**
     *  合入应用数据到当前视图的导航参数中
     * 
     * @param 应用数据
     * @memberof ViewBase
     */
    public mergeAppData(appData: any) {
        for (let key in this.context) {
            delete this.context[key];
        }
        if (appData && appData.context) {
            Object.assign(this.context, appData.context);
        }
    }

    /**
     * 解析视图参数
     *
     * @public
     * @memberof ViewBase
     */
    public parseViewParam(inputvalue: any = null): void {
        const _this: any = this;
        const setSrfsessionid = () => {
            if (_this.viewInstance && ModelTool.getViewAppEntityCodeName(this.viewInstance)) {
                Object.assign(_this.context, { srfsessionid: Util.createUUID() });
            }
        }
        if (AppServiceBase.getInstance()) {
            this.mergeAppData(AppServiceBase.getInstance().getAppStore().getters.getAppData());
        }
        if (this._context && !Object.is(this._context, '')) {
            if (typeof this._context == 'string') {
                Object.assign(this.context, JSON.parse(this._context));
            }
            if (this.context && this.context.srfparentdename) {
                Object.assign(this.viewparams, { srfparentdename: this.context.srfparentdename });
            }
            if (this.context && this.context.srfparentkey) {
                Object.assign(this.viewparams, { srfparentkey: this.context.srfparentkey });
            }
            // 处理首页视图srfsessionid;
            if (this.viewDefaultUsage == 'indexView') {
                setSrfsessionid();
            }
            this.handleCustomViewData();
            return;
        }

        const path = (_this.$route.matched[_this.$route.matched.length - 1]).path;
        const keys: Array<any> = [];
        const curReg = _this.$pathToRegExp.pathToRegexp(path, keys);
        const matchArray = curReg.exec(_this.$route.path);
        let tempValue: Object = {};
        keys.forEach((item: any, index: number) => {
            Object.defineProperty(tempValue, item.name, {
                enumerable: true,
                value: matchArray[index + 1]
            });
        });
        ViewTool.formatRouteParams(tempValue, this.$route, this.context, this.viewparams);
        if (inputvalue && ModelTool.getViewAppEntityCodeName(this.viewInstance)) {
            Object.assign(_this.context, { [(ModelTool.getViewAppEntityCodeName(this.viewInstance) as string).toLowerCase()]: inputvalue });
        }
        setSrfsessionid();
        if (_this.viewparams && _this.viewparams.srfdynainstid) {
            Object.assign(_this.context, { srfdynainstid: this.viewparams.srfdynainstid });
        }
        this.handleCustomViewData();
    }

    /**
     * 处理自定义视图数据
     *
     * @memberof ViewBase
     */
    public handleCustomViewData() {
        this.handleviewRes();
        if (this.customViewNavContexts.length > 0) {
            this.customViewNavContexts.forEach((item: any) => {
                let tempContext: any = {};
                let curNavContext: any = item;
                this.handleCustomDataLogic(curNavContext, tempContext, item.key);
                Object.assign(this.context, tempContext);
            })
        }
        if (this.customViewParams.length > 0) {
            this.customViewParams.forEach((item: any) => {
                let tempParam: any = {};
                let curNavParam: any = item;
                this.handleCustomDataLogic(curNavParam, tempParam, item.key);
                Object.assign(this.viewparams, tempParam);
            })
        }
    }

    /**
     * 处理指定视图控制关系将父键转为父实体上下文
     *
     * @memberof ViewBase
     */
    public handleviewRes() {
        // <#assign viewRes = view.getPSDER1N()/>
        // <#if viewRes.getMajorPSDataEntity()??>
        // <#assign majorAppDataEntity = viewRes.getMajorPSDataEntity() />
        // if(this.context.srfparentkey){
        //     Object.assign(this.context,{'${majorAppDataEntity.getCodeName()?lower_case}':this.context.srfparentkey});
        // }
        // </#if>
    }

    /**
     * 处理自定义视图数据逻辑
     *
     * @memberof ViewBase
     */
    public handleCustomDataLogic(curNavData: any, tempData: any, item: string) {
        // 直接值直接赋值
        if (curNavData.rawValue) {
            if (Object.is(curNavData.value, "null") || Object.is(curNavData.value, "")) {
                Object.defineProperty(tempData, item.toLowerCase(), {
                    value: null,
                    writable: true,
                    enumerable: true,
                    configurable: true
                });
            } else {
                Object.defineProperty(tempData, item.toLowerCase(), {
                    value: curNavData.value,
                    writable: true,
                    enumerable: true,
                    configurable: true
                });
            }
        } else {
            // 先从导航上下文取数，没有再从导航参数（URL）取数，如果导航上下文和导航参数都没有则为null
            if (this.context[(curNavData.value).toLowerCase()] != null) {
                Object.defineProperty(tempData, item.toLowerCase(), {
                    value: this.context[(curNavData.value).toLowerCase()],
                    writable: true,
                    enumerable: true,
                    configurable: true
                });
            } else {
                if (this.viewparams[(curNavData.value).toLowerCase()] != null) {
                    Object.defineProperty(tempData, item.toLowerCase(), {
                        value: this.viewparams[(curNavData.value).toLowerCase()],
                        writable: true,
                        enumerable: true,
                        configurable: true
                    });
                } else {
                    Object.defineProperty(tempData, item.toLowerCase(), {
                        value: null,
                        writable: true,
                        enumerable: true,
                        configurable: true
                    });
                }
            }
        }
    }

    /**
     *  计数器刷新
     *
     * @memberof ViewBase
     */
    public counterRefresh() {
        if (this.counterServiceArray && this.counterServiceArray.length > 0) {
            this.counterServiceArray.forEach((item: any) => {
                if (item?.service?.refreshData && item.service.refreshData instanceof Function) {
                    item.service.refreshData();
                }
            })
        }
    }

    /**
     * 视图刷新
     *
     * @param {*} args
     * @memberof ViewBase
     */
    public refresh(args?: any): void {
        const refs: any = this.$refs;
        const modelData: any = this.staticProps.modeldata;
        if (refs && modelData && modelData.xDataControlName && refs[modelData.xDataControlName]) {
            refs[modelData.xDataControlName].refresh();
        }
    }

    /**
     *  关闭视图
     *
     * @memberof ViewBase
     */
    public closeView(args?: any[]) {
        let _view: any = this;
        if (this.viewDefaultUsage === "routerView") {
            _view.$store.commit("deletePage", _view.$route.fullPath);
            _view.$router.go(-1);
        }
        if (this.viewDefaultUsage === 'includedView') {
            this.$emit('view-event', { viewName: this.viewInstance.codeName, action: 'close', data: null });
        }
        if (window.history.length == 1 && ThirdPartyService.getInstance().platform && this.viewDefaultUsage === "indexView") {
            this.quitFun();
        }
    }

    /**
     * 第三方关闭视图
     *
     * @param {any[]} args
     * @memberof ViewBase
     */
    public quitFun() {
        if (!sessionStorage.getItem("firstQuit")) {  // 首次返回时
            // 缓存首次返回的时间
            window.sessionStorage.setItem("firstQuit", new Date().getTime().toString());
            // 提示再按一次退出
            this.$toast("再按一次退出");
            // 两秒后清除缓存（与提示的持续时间一致）
            setTimeout(() => { window.sessionStorage.removeItem("firstQuit") }, 2000);
        } else {
            // 获取首次返回时间
            let firstQuitTime: any = sessionStorage.getItem("firstQuit");
            // 如果时间差小于两秒 直接关闭
            if (new Date().getTime() - firstQuitTime < 2000) {
                ThirdPartyService.getInstance().thirdPartyEvent('close');
            }
        }
    }

    /**
     * 初始化工具栏数据
     * 
     * @memberof ViewBase
     */
    public initViewToolBar() { }

    /**
     * 渲染视图头部
     * 
     * @memberof ViewBase
     */
    public renderViewHeader() { }

    /**
     * 渲染视图头部视图消息
     * 
     * @memberof ViewBase
     */
    public renderTopMessage() {
        return (null);
    }

    /**
     * 渲染视图Body视图消息
     * 
     * @memberof ViewBase
     */
    public renderBodyMessage() {
        return (null);
    }

    /**
     * 渲染视图底部视图消息
     * 
     * @memberof ViewBase
     */
    public renderBottomMessage() {
        return (null);
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof ViewBase
     */
    public renderMainContent() { }

    /**
     * 渲染视图下拉刷新
     * 
     * @memberof ViewBase
     */
    public renderPullDownRefresh() {
        return <ion-refresher
            slot="fixed"
            ref="loadmore"
            pull-factor="0.5"
            pull-min="50"
            pull-max="100"
            on-ionRefresh={this.pullDownToRefresh}>
            <ion-refresher-content
                pulling-icon="arrow-down-outline"
                pulling-text={'app.pulling_text'}
                refreshing-spinner="circles"
                refreshing-text="">
            </ion-refresher-content>
        </ion-refresher>
    }

    /**
     * 下拉刷新
     *
     * @param {*} $event
     * @returns {Promise<any>}
     * @memberof ViewBase
     */
    public async pullDownToRefresh($event: any): Promise<any> {
        setTimeout(() => {
            $event.srcElement.complete();
        }, 2000);
    }

    /**
     * 工具栏点击
     * 
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * @param $event 事件源对象
     * 
     * @memberof ViewBase
     */
    public handleItemClick(data: any, $event: any) {
        if (this.Environment?.isPreviewMode) {
            return;
        }
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof ViewBase
     */
    public computeTargetCtrlData(controlInstance: IPSControl) {
        let targetCtrlName: string = `app-control-shell`;
        let targetCtrlParam: any = {
            staticProps: {
                viewState: this.viewState,
                viewtag: this.viewtag,
                containerInstance: this.viewInstance,
                modelData: controlInstance,
                ref: controlInstance.name,
            },
            dynamicProps: {
                viewparams: this.viewparams,
                context: this.context,
            }
        };
        Object.defineProperty(targetCtrlParam.staticProps, 'containerInstance', { enumerable: false, writable: true });
        Object.defineProperty(targetCtrlParam.staticProps, 'modelData', { enumerable: false, writable: true });
        let targetCtrlEvent: any = {
            'ctrl-event': ({ controlname, action, data }: { controlname: string, action: string, data: any }) => {
                this.onCtrlEvent(controlname, action, data);
            },
            closeView: ($event: any) => {
                this.closeView($event);
            }
        }
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof ViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        const _this: any = this;
        if (action == 'controlIsMounted') {
            _this.setIsMounted(controlname)
        } else {
            if (_this.engine) {
                _this.engine.onCtrlEvent(controlname, action, data);
            }

        }
    }


    /**
     * 渲染内容区
     * 
     * @memberof ViewBase
     */
    public renderContent() { }

    /**
     * 是否显示返回按钮
     *
     * @readonly
     * @type {boolean}
     * @memberof ViewBase
     */
    get isShowBackButton(): boolean {
        // 存在路由，非路由使用，嵌入
        if (this.viewDefaultUsage === "indexView") {
            return false;
        }
        return true;
    }

    /**
     * 绘制头部标题栏
     * 
     * @memberof ViewBase
     */
    public renderViewHeaderCaptionBar() {
        if (this.viewInstance.showCaptionBar === false) {
            return null;
        }
        const srfCaption = this.model.srfCaption;
        const dataInfo: string = this.model.dataInfo ? (`-${this.model.dataInfo}`) : '';
        return <ion-toolbar v-show="titleStatus" class="ionoc-view-header" slot="captionbar">
            <ion-buttons slot="start">
                {this.isShowBackButton &&
                    <ion-button on-click={this.closeView.bind(this)}>
                        <ion-icon name="chevron-back" />
            返回
          </ion-button>}
            </ion-buttons>
            <ion-title class="view-title">{Util.getI18n(this, srfCaption, dataInfo, this.viewInstance.title)}</ion-title>
        </ion-toolbar>
    }


    /**
     * 渲染视图工具栏
     * 
     * @memberof MainViewBase
     */
    public renderToolBar() {
        return (
            <view-toolbar
                slot='mobbottommenu'
                toolbarModels={this.toolbarModels}
                on-item-click={(data: any, $event: any) => { this.handleItemClick(data, $event) }}
            ></view-toolbar>
        );
    }


}
