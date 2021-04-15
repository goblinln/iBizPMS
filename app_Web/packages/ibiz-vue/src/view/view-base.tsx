import Vue from 'vue';
import { Subject, Subscription } from 'rxjs';
import { Util, ViewTool, AppServiceBase, ViewContext, ViewState, removeSessionStorage } from 'ibiz-core';
import { CounterServiceRegister, UIServiceRegister } from 'ibiz-service';
import { AppNavHistory, ViewLoadingService } from '../app-service';
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
    public viewdata!: any;

    /**
     * 传入视图参数
     *
     * @type {any}
     * @memberof ViewBase
     */
    public viewparam!: any;

    /**
     * 视图loading服务
     *
     * @type {ViewLoadingService}
     * @memberof ViewBase
     */
    public viewLoadingService: ViewLoadingService = new ViewLoadingService();

    /**
     * 视图默认使用(路由：true,非路由：false)
     *
     * @type {boolean}
     * @memberof ViewBase
     */
    public viewDefaultUsage!: boolean;

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
    public portletStateEvent: Subscription | undefined;

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
    public portletState?: any;

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
      * 当前视图环境参数
      * 
      * @memberof ViewBase
      */
    public ctx!: ViewContext;

    /**
     * 视图实例
     * 
     * @memberof ViewBase
     */
    public viewInstance!: any;

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
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ViewBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        // 处理viewparam
        if (newVal.viewparam && newVal.viewparam !== oldVal?.viewparam) {
            this.viewparam = newVal.viewparam;
            if (typeof this.viewparam == 'string') {
                this.viewparams = JSON.parse(this.viewparam);
            } else {
                this.viewparams = Util.deepCopy(this.viewparam);
            }
        }
        // 处理viewdata
        if (newVal.viewdata && newVal.viewdata !== oldVal?.viewdata) {
            const _this: any = this;
            this.viewDefaultUsage = this.staticProps?.viewDefaultUsage;
            this.viewdata = newVal.viewdata;
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
     * 设置已经绘制完成状态
     *
     * @memberof ViewBase
     */
    public setIsMounted() { }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ViewBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.beforeViewModelInit(newVal);
        this.viewModelInit().then((res: any) => {
            this.viewInit();
            this.viewIsLoaded = true;
            this.setIsMounted();
            setTimeout(() => {
                this.viewMounted();
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
        this.viewDefaultUsage = data.viewDefaultUsage !== false;
        this.navDataService = data.navDataService;
        this.portletState = data.portletState;
        this.viewtag = data.viewtag;
        this.formDruipartState = data.formDruipartState;
        const modelData: any = data.modeldata;
        this.customViewNavContexts = modelData?.getPSAppViewNavContexts ? modelData?.getPSAppViewNavContexts : [];
        this.customViewParams = modelData?.getPSAppViewNavParams ? modelData?.getPSAppViewNavParams : [];
        this.viewCodeName = modelData?.codeName;
        this.context = data.viewcontainer;
    }

    /**
     * 视图模型数据初始化实例
     *
     * @memberof ViewBase
     */
    public async viewModelInit() {
        // 初始化时需要计算context和viewparams
        this.parseViewParam();
        if(this.staticProps && this.staticProps.modeldata){
            this.initContainerModel(this.staticProps);
            await this.initCounterService(this.staticProps.modeldata);
            await this.initAppUIService();
        }
    }

    /**
     * 初始化containerModel
     * 
     * @memberof ViewBase
     */
    public initContainerModel(opts: any) {
        const { modeldata } = opts;
        if (!modeldata) {
            return;
        }
        if (Object.is(modeldata.viewType, 'DEPICKUPVIEW') || Object.is(modeldata.viewType, 'DEMPICKUPVIEW') || Object.is(modeldata.viewType, 'DEOPTVIEW') || Object.is(modeldata.viewType, 'DEWFSTARTVIEW') || Object.is(modeldata.viewType, 'DEWFACTIONVIEW')) {
            this.containerModel = {
                view_okbtn: { name: 'okbtn', type: 'button', text: '确定', disabled: true },
                view_cancelbtn: { name: 'cancelbtn', type: 'button', text: '取消', disabled: false },
                view_leftbtn: { name: 'leftbtn', type: 'button', text: '左移', disabled: true },
                view_rightbtn: { name: 'rightbtn', type: 'button', text: '右移', disabled: true },
                view_allleftbtn: { name: 'allleftbtn', type: 'button', text: '全部左移', disabled: true },
                view_allrightbtn: { name: 'allrightbtn', type: 'button', text: '全部右移', disabled: true },
            }
        }
    }

    /**
     * 初始化应用界面服务
     * 
     * @memberof ViewBase
     */
    public async initAppUIService() {
        if (this.viewInstance && this.viewInstance.appDataEntity && this.viewInstance.appDataEntity.codeName) {
            this.appUIService = await UIServiceRegister.getInstance().getService(this.context, this.viewInstance.appDataEntity.codeName.toLowerCase());
            if (this.appUIService) {
                await this.appUIService.loaded();
            }
        }
    }

    /**
     * 初始化计数器服务
     * 
     * @memberof ViewBase
     */
    public async initCounterService(param: any) {
        const { getPSAppCounterRefs: appCounterRef } = param;
        if (appCounterRef && appCounterRef.length > 0) {
            for (const counterRef of appCounterRef) {
                const targetCounterService: any = await CounterServiceRegister.getInstance().getService({ context: this.context, viewparams: this.viewparams }, counterRef.getPSAppCounter.dynaModelFilePath);
                const tempData: any = { id: counterRef.id, path: counterRef?.getPSAppCounter?.dynaModelFilePath, service: targetCounterService };
                this.counterServiceArray.push(tempData);
            }
        }
    }

    /**
     * 初始化视图标题数据
     *
     * @param {*} result
     * @memberof ViewBase
     */
    public initModel(view: any) {
        if (!view) {
            return;
        }
        this.model = { dataInfo: '' };
        if (view?.appDataEntity) {
            this.model.srfCaption = `entities.${view?.appDataEntity?.codeName?.toLowerCase()}.views.${view.getPSDEViewCodeName?.toLowerCase()}.caption`;
            this.model.srfTitle = `entities.${view?.appDataEntity?.codeName?.toLowerCase()}.views.${view.getPSDEViewCodeName?.toLowerCase()}.title`;
            this.model.srfSubTitle = `entities.${view?.appDataEntity?.codeName?.toLowerCase()}.views.${view.getPSDEViewCodeName?.toLowerCase()}.subtitle`;
        } else {
            this.model.srfCaption = `app.views.${view.codeName?.toLowerCase()}.caption`;
            this.model.srfTitle = `app.views.${view.codeName?.toLowerCase()}.title`;
            this.model.srfSubTitle = `app.views.${view.codeName?.toLowerCase()}.subtitle`;
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
        if (this.viewDefaultUsage) {
            const navHistory: AppNavHistory = AppServiceBase.getInstance().getAppNavDataService();
            if (navHistory) {
                navHistory.setViewTag(this.viewtag, _this.$route);
            }
        }
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
        if (_this.portletState) {
            _this.portletStateEvent = _this.portletState.subscribe((res: any) => {
                if (!Object.is(res.tag, _this.viewInstance.name)) {
                    return;
                }
                if (Object.is(res.action, 'refresh') && _this.refresh && _this.refresh instanceof Function) {
                    this.refresh();
                }
            })
        }
        if (_this.formDruipartState) {
            _this.formDruipartStateEvent = _this.formDruipartState.subscribe(({ action }: any) => {
                if (Object.is(action, 'save')) {
                    _this.viewState.next({ tag: _this?.viewInstance?.xDataControlName, action: action, data: _this.viewparams });
                }
                if (Object.is(action, 'load')) {
                    if (_this.engine) {
                        _this.engine.load();
                    }
                }
            });
        }

        // 视图加载服务初始化操作
        this.viewLoadingService.srfsessionid = this.context.srfsessionid;
        this.$store.commit("loadingService/addViewLoadingService",this.viewLoadingService)
    }

    /**
     *  视图挂载
     *
     * @memberof ViewBase
     */
    public viewMounted() {
        const _this: any = this;
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
            if (this.serviceStateEvent) {
                this.serviceStateEvent.unsubscribe();
            }
        }
        // 销毁计数器定时器
        if (this.counterServiceArray && this.counterServiceArray.length > 0) {
            this.counterServiceArray.forEach((item: any) => {
                let counterService = item.service;
                if (counterService && counterService.destroyCounter && counterService.destroyCounter instanceof Function) {
                    counterService.destroyCounter();
                }
            })
        }
        if (this.portletStateEvent) {
            this.portletStateEvent.unsubscribe();
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
     * 初始化导航数据(路由模式)
     *
     * @memberof ViewBase
     */
    public initNavDataWithRoute(data: any = null, isNew: boolean = false, isAlways: boolean = false) {
        if (isAlways || (this.viewDefaultUsage && Object.is(this.navModel, "route"))) {
            // this.navDataService.addNavData({id:'${srffilepath2(view.getCodeName())}',tag:this.viewtag,srfkey:isNew ? null : <#if appde??>this.context.${appde.getCodeName()?lower_case}<#else>null</#if>,title:this.$t(this.model.srfCaption),data:data,context:this.context,viewparams:this.viewparams,path:this.$route.fullPath});
        }
    }

    /**
     * 初始化导航数据(分页模式)
     *
     * @memberof ViewBase
     */
    public initNavDataWithTab(data: any = null, isOnlyAdd: boolean = true, isAlways: boolean = false) {
        if (isAlways || (this.viewDefaultUsage && !Object.is(this.navModel, "route"))) {
            // this.navDataService.addNavDataByOnly({id:'${srffilepath2(view.getCodeName())}',tag:this.viewtag,srfkey:<#if appde??>this.context.${appde.getCodeName()?lower_case}<#else>null</#if>,title:this.$t(this.model.srfCaption),data:data,context:this.context,viewparams:this.viewparams,path:this.$route.fullPath},isOnlyAdd);
        }
    }
    /**
     * 解析视图参数
     *
     * @public
     * @memberof ViewBase
     */
    public parseViewParam(inputvalue: any = null): void {
        let _this: any = this;
        this.context = {};
        if (AppServiceBase.getInstance()) {
            this.mergeAppData(AppServiceBase.getInstance().getAppStore().getters.getAppData());
        }
        if (!_this.viewDefaultUsage && _this.viewdata && !Object.is(_this.viewdata, '')) {
            if (typeof _this.viewdata == 'string') {
                Object.assign(_this.context, JSON.parse(_this.viewdata));
            }
            if (_this.context && _this.context.srfparentdename) {
                Object.assign(_this.viewparams, { srfparentdename: _this.context.srfparentdename });
            }
            if (_this.context && _this.context.srfparentkey) {
                Object.assign(_this.viewparams, { srfparentkey: _this.context.srfparentkey });
            }
            _this.handleCustomViewData();
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
        ViewTool.formatRouteParams(tempValue, _this.$route, _this.context, _this.viewparams);
        if (inputvalue && _this.viewInstance.appDeCodeName) {
            Object.assign(_this.context, { [_this.viewInstance.appDeCodeName.toLowerCase()]: inputvalue });
        }
        if (_this.viewInstance && _this.viewInstance.appDeCodeName) {
            Object.assign(_this.context, { srfsessionid: Util.createUUID() });
        }
        if (_this.viewparams && _this.viewparams.srfdynainstid) {
            Object.assign(_this.context, { srfdynainstid: this.viewparams.srfdynainstid });
        }
        _this.handleCustomViewData();
        _this.handleOtherViewData();
        //初始化导航数据
        _this.initNavDataWithRoute();
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
     * 处理其他数据(多实例)
     *
     * @memberof ViewBase
     */
     public handleOtherViewData() {
        let appEnvironment = AppServiceBase.getInstance().getAppEnvironment();
        let appModelObject = AppServiceBase.getInstance().getAppModelDataObject();
        if (appEnvironment.bDynamic) {
            if (this.context && this.context.srfdynainstid) {
                let dynainstParam: any = appModelObject.getPSDynaInsts.find((item: any) => {
                    return item.id === this.context.srfdynainstid;
                })
                Object.assign(this.viewparams, { srfinsttag: dynainstParam.instTag, srfinsttag2: dynainstParam.instTag2 });
            }
        }
    }


    /**
     * 处理指定视图控制关系将父键转为父实体上下文
     *
     * @memberof ViewBase
     */
    public async handleviewRes() {
        if (this.viewInstance?.getParentPSAppDataEntity) {
            // 先从导航上下文取数，没有再从导航参数（URL）取数，如果导航上下文和导航参数都没有则为null
            const parentEntityCodeName = this.viewInstance.getParentPSAppDataEntity.codeName.toLowerCase();
            if (this.context.srfparentkey) {
                Object.assign(this.context, { [parentEntityCodeName]: this.context.srfparentkey })
            } else if (this.viewparams.srfparentkey) {
                Object.assign(this.context, { [parentEntityCodeName]: this.viewparams.srfparentkey })
            }
        }
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
                let counterService = item.service;
                if (counterService && counterService.refreshCounterData && counterService.refreshCounterData instanceof Function) {
                    counterService.refreshCounterData();
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
        const modelData: any = this.staticProps?.modeldata;
        try {
            if (refs && modelData && modelData.xDataControlName && refs[modelData.xDataControlName]) {
                refs[modelData.xDataControlName]?.ctrl?.refresh();
            }
        } catch (error) {
            console.log(refs, modelData)
        }
    }

    /**
     * 关闭视图(视图样式为style2样式)
     *
     * @view {*} 当前视图
     * @memberof ViewBase
     */
    public closeViewWithStyle2(view: any) {
        const appNavDataService: any = AppServiceBase.getInstance().getAppNavDataService();
        const item: any = appNavDataService.historyList[appNavDataService.findHistoryIndex(view.$route)];
        appNavDataService.remove(item);
        if (appNavDataService.historyList.length > 0) {
            if (appNavDataService.isRouteSame(item.to, this.$route)) {
                let go: any = appNavDataService.historyList[
                    appNavDataService.historyList.length - 1
                ].to;
                this.$router.push({ path: go.path, params: go.params, query: go.query });
            }
        } else {
            const path: string | null = window.sessionStorage.getItem(AppServiceBase.getInstance().getAppEnvironment().AppName);
            if (path) {
                this.$router.push({ path: path });
            } else {
                const name: any = this.$route?.matched[0].name;
                const param = this.$route.params[name];
                const path = `/${name}${param ? `/${param}` : ''}`;
                this.$router.push({ path });
            }
        }
    }

    /**
     * 关闭视图(视图样式为默认样式)
     *
     * @view {*} 当前视图
     * @memberof ViewBase
     */
    public closeViewWithDefault(view: any) {
        view.$store.commit("deletePage", view.$route.fullPath);
        const length = view.$store.state.historyPathList.length;
        if (length > 0) {
            const path = view.$store.state.historyPathList[length - 1];
            if (Object.is(path, view.$route.fullPath)) {
                return;
            }
            const index = view.$store.state.pageTagList.findIndex((page: any) =>
                Object.is(page.fullPath, path)
            );
            if (index >= 0) {
                const page = view.$store.state.pageTagList[index];
                view.$router.push({
                    path: page.path,
                    params: page.params,
                    query: page.query
                });
            }
        } else {
            let path: string | null = window.sessionStorage.getItem(
                AppServiceBase.getInstance().getAppEnvironment().AppName
            );
            if (path) {
                this.$router.push({ path: path });
            } else {
                this.$router.push("/");
            }
        }
    }

    /**
     *  关闭视图
     *
     * @memberof ViewBase
     */
    public closeView(args: any[]) {
        let view: any = this;
        if (view.viewdata) {
            view.$emit('view-event', { action: 'viewdataschange', data: Array.isArray(args) ? args : [args] });
            view.$emit('view-event', { action: 'close', data: Array.isArray(args) ? args : [args] });
        } else {
            if (this.viewInstance && this.viewInstance.viewStyle && Object.is(this.viewInstance.viewStyle, "STYLE2")) {
                this.closeViewWithStyle2(view);
            } else {
                this.closeViewWithDefault(view);
            }
        }
        removeSessionStorage("tempOrgId");
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
        const viewMessageGroup = this.viewInstance?.viewMessageGroup;
        if (!viewMessageGroup || !viewMessageGroup.id) {
            return null;
        }
        return (
            <div slot="topMessage" class="view-top-message">
                <app-alert-group
                    position="TOP"
                    context={this.context}
                    viewparam={this.viewparam}
                    infoGroup={viewMessageGroup.id}
                    viewname={this.viewInstance?.codeName.toLowerCase()}
                ></app-alert-group>
            </div>
        );
    }

    /**
     * 渲染视图Body视图消息
     * 
     * @memberof ViewBase
     */
    public renderBodyMessage() {
        const viewMessageGroup = this.viewInstance?.viewMessageGroup;
        if (!viewMessageGroup || !viewMessageGroup.id) {
            return null;
        }
        return (
            <div slot="bodyMessage" class="view-body-message">
                <app-alert-group
                    position="BODY"
                    context={this.context}
                    viewparam={this.viewparam}
                    infoGroup={viewMessageGroup.id}
                    viewname={this.viewInstance?.codeName.toLowerCase()}
                ></app-alert-group>
            </div>
        );
    }

    /**
     * 渲染视图底部视图消息
     * 
     * @memberof ViewBase
     */
    public renderBottomMessage() {
        const viewMessageGroup = this.viewInstance?.viewMessageGroup;
        if (!viewMessageGroup || !viewMessageGroup.id) {
            return null;
        }
        return (
            <div slot="bottomMessage" class="view-bottom-message">
                <app-alert-group
                    position="BOTTOM"
                    context={this.context}
                    viewparam={this.viewparam}
                    infoGroup={viewMessageGroup.id}
                    viewname={this.viewInstance?.codeName.toLowerCase()}
                ></app-alert-group>
            </div>
        );
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof ViewBase
     */
    public renderMainContent() { }

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
    public handleItemClick(data: any, $event: any) { }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof ViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        let targetCtrlName: string = `app-control-shell`;
        let targetCtrlParam: any = {
            staticProps: {
                viewState: this.viewState,
                viewtag: this.viewtag,
                containerInstance: this.viewInstance,
                modelData: controlInstance,
                ref: controlInstance.name,
                viewLoadingService: this.viewLoadingService,
            },
            dynamicProps: {
                viewparams: this.viewparams,
                context: this.context,
            }
        };
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
        if (_this.engine) {
            _this.engine.onCtrlEvent(controlname, action, data);
        }
    }

    /**
     * 渲染内容区
     * 
     * @memberof ViewBase
     */
    public renderContent() { }

}
