import Vue from 'vue';
import { Subject, Subscription } from 'rxjs';
import { IPSAppCounterRef, IPSAppView, IPSControl, IPSDETBGroupItem, IPSDETBRawItem, IPSDEToolbar, IPSDEToolbarItem, IPSDEUIAction, IPSLanguageRes } from '@ibiz/dynamic-model-api';
import { Util, ViewTool, AppServiceBase, ViewContext, ViewState, ModelTool, GetModelService, AppModelService, LogUtil, SandboxInstance, ViewInterface, throttle, AppCustomEngine, AppCtrlEventEngine, AppTimerEngine, AppViewEventEngine } from 'ibiz-core';
import { CounterServiceRegister, ViewMessageService } from 'ibiz-service';
import { AppMessageBoxService, AppNavHistory, NavDataService, ViewLoadingService } from '../app-service';
import { DynamicInstanceConfig } from '@ibiz/dynamic-model-api/dist/types/core';
import { createUUID, isNilOrEmpty } from 'qx-util';

/**
 * 视图基类
 *
 * @export
 * @class ViewBase
 * @extends {Vue}
 * @implements {ViewInterface}
 */
export class ViewBase extends Vue implements ViewInterface {

    /**
     * 环境文件
     * 
     * @type {any}
     * @protected
     * @memberof ViewBase
     */
    protected Environment: any = AppServiceBase.getInstance().getAppEnvironment();

    /**
     * 注册事件逻辑分隔符
     * 
     * @memberof ViewBase
     */
    public registerEventSeparator: string = 'ibiz__';

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
     * 导航数据（用于数据穿透）
     *
     * @type {*}
     * @memberof ViewBase
     */
    public navdatas!: any;

    /**
     * 视图操作参数集合
     *
     * @type {*}
     * @memberof ViewBase
     */
    public viewCtx: any = {};

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
     * 是否禁用视图标题（不显示标题：true）
     *
     * @memberof ViewBase
     */
    public noViewCaption = false;

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
    public navDataService: NavDataService = NavDataService.getInstance(this.$store);

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
    public containerModel: any = {};

    public viewMessageService: ViewMessageService = new ViewMessageService();

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
     * 界面触发逻辑Map
     * 
     * @memberof ViewBase
     */
    public viewTriggerLogicMap: Map<string, any> = new Map();

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
     * 获取顶层视图
     *
     * @memberof ViewBase
     */
    public getTopView() {
        return this.viewCtx.topview;
    }

    /**
     * 获取父级视图
     *
     * @memberof ViewBase
     */
    public getParentView() {
        return this.viewCtx.parentview;
    }

    /**
     * 获取指定名称部件
     *
     * @memberof ViewBase
     */
    public getCtrlByName(name: string) {
        return (this.$refs[name] as any).ctrl;
    }

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
        // 处理navdatas
        if (newVal.navdatas && newVal.navdatas !== oldVal?.navdatas) {
            this.navdatas = newVal.navdatas;
        }
        this.initViewCtx();
    }

    /**
     * 挂载状态集合
     *
     * @type {Map<string,boolean>}
     * @memberof ControlBase
     */
    public mountedMap: Map<string, boolean> = new Map();

    /**
     * 模型服务
     *
     * @type {AppModelService}
     * @memberof ControlBase
     */
    public modelService !: AppModelService;

    /**
     * 是否视图已经完成viewMounted
     *
     * @type {boolean}
     * @memberof ControlBase
     */
    public hasViewMounted: boolean = false;

    /**
     * 初始化挂载状态集合
     *
     * @memberof ControlBase
     */
    public initMountedMap() {
        let controls = this.viewInstance?.getPSControls?.();
        controls?.forEach((item: any) => {
            if (item.controlType == "TOOLBAR" || item.controlType == "SEARCHBAR" || item.controlType == "CAPTIONBAR" || item.controlType == "DATAINFOBAR") {
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
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ViewBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (!(newVal?.modeldata)) {
            return
        }
        this.beforeViewModelInit(newVal);
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
        this.viewDefaultUsage = data.viewDefaultUsage !== false;
        this.noViewCaption = data.noViewCaption == true;
        this.portletState = data.portletState;
        this.viewtag = data.viewtag;
        this.formDruipartState = data.formDruipartState;
        this.viewInstance = data?.modeldata;
        this.customViewNavContexts = this.viewInstance.getPSAppViewNavContexts() ? this.viewInstance.getPSAppViewNavContexts() : [];
        this.customViewParams = this.viewInstance.getPSAppViewNavParams() ? this.viewInstance.getPSAppViewNavParams() : [];
        this.viewCodeName = this.viewInstance?.codeName;
        this.context = data.viewcontainer;
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
        // 视图应用实体加载
        await this.viewInstance?.getPSAppDataEntity?.()?.fill();
    }

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
        if (!_this.viewDefaultUsage && _this.viewdata && !Object.is(_this.viewdata, '')) {
            if (typeof _this.viewdata == 'string') {
                Object.assign(tempContext, JSON.parse(_this.viewdata));
            } else {
                tempContext = Util.deepCopy(_this.viewdata);
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
            // 补充沙箱实例参数（路由）
            if (_this.viewparams && _this.viewparams.hasOwnProperty('srfsandboxtag')) {
                Object.assign(tempContext, { 'srfsandboxtag': _this.viewparams.srfsandboxtag });
            }
        }
        try {
            this.modelService = await GetModelService(tempContext);
        } catch (error) {
            await this.initSandBoxInst(tempContext);
            this.modelService = await GetModelService(tempContext);
        }
    }

    /**
     * 初始化沙箱实例
     *
     * @memberof ViewBase
     */
    public async initSandBoxInst(args: any) {
        if (args && args.srfsandboxtag) {
            const tempSandboxInst: SandboxInstance = new SandboxInstance(args);
            await tempSandboxInst.initSandBox();
        }
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
            this.initMountedMap();
            // 初始化时需要计算context和viewparams
            this.parseViewParam();
            // 初始化viewCtx
            this.initViewCtx();
            if (this.staticProps && this.staticProps.modeldata) {
                this.initContainerModel(this.staticProps);
                await this.initViewMessageService(this.staticProps.modeldata);
                await this.initCounterService(this.staticProps.modeldata);
                await this.initAppUIService();
                await this.initViewLogic(this.viewInstance);
            }
        } catch (error) {
            LogUtil.warn(error);
        }
    }

    /**
     * 绘制视图部件集合
     * 
     * @memberof ViewBase
     */
    public renderViewControls() {
        const viewLayoutPanel = this.viewInstance.getPSViewLayoutPanel();
        if (viewLayoutPanel && viewLayoutPanel.useDefaultLayout) {
            return [];
        } else {
            const controlArray: Array<any> = [];
            if (this.viewInstance.getPSControls() && (this.viewInstance.getPSControls() as IPSControl[]).length > 0) {
                (this.viewInstance.getPSControls() as IPSControl[]).forEach((control: IPSControl) => {
                    const targetCtrl = this.renderTargetControl(control);
                    controlArray.push(targetCtrl);
                });
            }
            controlArray.push(this.renderCaptionBar());
            controlArray.push(this.renderDataInfoBar());
            return controlArray;
        }
    }

    /**
     * 绘制目标部件
     * 
     * @memberof ViewBase
     */
    public renderTargetControl(control: IPSControl) {
        if (Object.is(control.controlType, 'TOOLBAR')) {
            const viewToolBar: IPSDEToolbar = control as IPSDEToolbar;
            const targetViewToolbarItems: any[] = [];
            if (viewToolBar && viewToolBar.getPSDEToolbarItems()) {
                viewToolBar.getPSDEToolbarItems()?.forEach((toolbarItem: IPSDEToolbarItem) => {
                    targetViewToolbarItems.push(this.initToolBarItems(toolbarItem));
                });
            }
            return (
                <view-toolbar
                    slot={`layout-${control.name}`}
                    mode={this.viewInstance?.viewStyle || 'DEFAULT'}
                    counterServiceArray={this.counterServiceArray}
                    isViewLoading={this.viewLoadingService?.isLoading}
                    toolbarModels={targetViewToolbarItems}
                    on-item-click={(data: any, $event: any) => {
                        throttle(this.handleItemClick, [data, $event], this);
                    }}
                ></view-toolbar>
            );
        } else {
            let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(control);
            Object.assign(targetCtrlParam, {
                dynamicProps: {
                    viewparams: Util.deepCopy(this.viewparams),
                    context: Util.deepCopy(this.context),
                    viewCtx: this.viewCtx
                }
            });
            return this.$createElement(targetCtrlName, { slot: `layout-${control.name}`, props: targetCtrlParam, ref: control?.name, on: targetCtrlEvent });
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
        if (Object.is(modeldata.viewType, 'DEPICKUPVIEW')
            || Object.is(modeldata.viewType, 'DEPICKUPVIEW2')
            || Object.is(modeldata.viewType, 'DEPICKUPVIEW3')
            || Object.is(modeldata.viewType, 'DEMPICKUPVIEW')
            || Object.is(modeldata.viewType, 'DEMPICKUPVIEW2')
            || Object.is(modeldata.viewType, 'DEOPTVIEW')
            || Object.is(modeldata.viewType, 'DEWFSTARTVIEW')
            || Object.is(modeldata.viewType, 'DEWFACTIONVIEW')) {
            this.containerModel = {
                view_okbtn: { name: 'okbtn', type: 'button', text: this.$t('app.commonwords.ok'), disabled: true },
                view_cancelbtn: { name: 'cancelbtn', type: 'button', text: this.$t('app.commonwords.cancel'), disabled: false },
                view_leftbtn: { name: 'leftbtn', type: 'button', text: this.$t('app.button.leftbtn'), disabled: true },
                view_rightbtn: { name: 'rightbtn', type: 'button', text: this.$t('app.button.rightbtn'), disabled: true },
                view_allleftbtn: { name: 'allleftbtn', type: 'button', text: this.$t('app.button.allleftbtn'), disabled: true },
                view_allrightbtn: { name: 'allrightbtn', type: 'button', text: this.$t('app.button.allrightbtn'), disabled: true },
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
     * 初始化工具栏项
     *
     * @param {IPSDEToolbarItem} item
     * 
     * @@memberof ViewBase
     */
    initToolBarItems(item: IPSDEToolbarItem): void {
        if (item.itemType === 'ITEMS') {
            const items = (item as IPSDETBGroupItem).getPSDEToolbarItems();
            if (items && items.length != 0) {
                const models: Array<any> = [];
                const tempModel: any = {
                    name: item.name,
                    showCaption: item.showCaption,
                    caption: this.$tl((item.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, item.caption),
                    tooltip: this.$tl((item.getTooltipPSLanguageRes() as IPSLanguageRes)?.lanResTag, item.tooltip),
                    disabled: false,
                    visabled: true,
                    itemType: item.itemType,
                    dataaccaction: '',
                    actionLevel: (item as any).actionLevel
                };
                items.forEach((_item: any) => {
                    models.push(this.initToolBarItems(_item));
                });
                Object.assign(tempModel, {
                    model: models,
                });
                return tempModel;
            }
        }
        const img = item.getPSSysImage();
        const css = item.getPSSysCss();
        const uiAction = (item as any)?.getPSUIAction?.() as IPSDEUIAction;
        const tempModel: any = {
            name: item.name,
            showCaption: item.showCaption,
            caption: this.$tl((item.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, item.caption),
            tooltip: this.$tl((item.getTooltipPSLanguageRes() as IPSLanguageRes)?.lanResTag, item.tooltip),
            disabled: false,
            visabled: uiAction?.dataAccessAction && this.Environment.enablePermissionValid ? false : true,
            itemType: item.itemType,
            dataaccaction: uiAction?.dataAccessAction,
            noprivdisplaymode: uiAction?.noPrivDisplayMode,
            uiaction: uiAction,
            showIcon: item.showIcon,
            class: css ? css.cssName : '',
            getPSSysImage: img ? { cssClass: img.cssClass, imagePath: img.imagePath } : '',
            actionLevel: (item as any).actionLevel
        };
        if (item.itemType == 'RAWITEM') {
            Object.assign(tempModel, {
                rawType: (item as IPSDETBRawItem).contentType,
                rawContent: (item as IPSDETBRawItem).rawContent,
                htmlContent: (item as IPSDETBRawItem).htmlContent,
                style: {}
            })
            if (item.height) {
                Object.assign(tempModel.style, { height: item.height + 'px' });
            }
            if (item.width) {
                Object.assign(tempModel.style, { width: item.width + 'px' });
            }
        }
        return tempModel;
    }

    /**
     * 初始化计数器服务
     *
     * @param {*} param 视图实例
     * @memberof ViewBase
     */
    public async initCounterService(param: any) {
        const appCounterRef: Array<IPSAppCounterRef> = (param as IPSAppView).getPSAppCounterRefs() || [];
        if (appCounterRef && appCounterRef.length > 0) {
            for (const counterRef of appCounterRef) {
                const path = counterRef.getPSAppCounter?.()?.modelPath;
                if (path) {
                    const targetCounterService: any = await CounterServiceRegister.getInstance().getService({ context: this.context, viewparams: this.viewparams }, path);
                    const tempData: any = { id: counterRef.id, path: path, service: targetCounterService };
                    this.counterServiceArray.push(tempData);
                }
            }
        }
    }

    /**
     * 初始化视图消息服务
     *
     * @memberof ViewBase
     */
    public async initViewMessageService(param: any) {
        const viewMsgGroup: any = (param as IPSAppView).getPSAppViewMsgGroup?.();
        await this.viewMessageService.initBasicParam(viewMsgGroup, this.context, this.viewparams);
    }

    /**
     * 初始化视图标题数据
     *
     * @param {*} view 视图实例
     * @memberof ViewBase
     */
    public initModel(view: IPSAppView) {
        if (!view) {
            return;
        }
        this.model = { dataInfo: '' };
        Object.assign(this.model, { srfCaption: this.viewInstance.getCapPSLanguageRes() ? this.$tl((this.viewInstance.getCapPSLanguageRes() as IPSLanguageRes).lanResTag, this.viewInstance.caption) : this.viewInstance.caption });
        Object.assign(this.model, { srfTitle: this.viewInstance.getTitlePSLanguageRes() ? this.$tl((this.viewInstance.getTitlePSLanguageRes() as IPSLanguageRes).lanResTag, this.viewInstance.title) : this.viewInstance.title });
        Object.assign(this.model, { srfSubTitle: this.viewInstance.getSubCapPSLanguageRes() ? this.$tl((this.viewInstance.getSubCapPSLanguageRes() as IPSLanguageRes).lanResTag, this.viewInstance.subCaption) : this.viewInstance.subCaption });
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
                navHistory.setCaption({ tag: this.viewtag, caption: this.model.srfCaption, info: '' });
            }
        }
        if (_this.navDataService) {
            _this.serviceStateEvent = _this.navDataService.serviceState.subscribe(({ action, name, data }: { action: string, name: any, data: any }) => {
                if (!Object.is(name, this.viewCodeName)) {
                    return;
                }
                if (Object.is(action, 'viewrefresh')) {
                    _this.parseViewParam(data);
                    setTimeout(() => {
                        if (_this.engine) {
                            _this.engine.load();
                        }
                        _this.$forceUpdate();
                    }, 0);
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
                    if (_this?.viewInstance) {
                        if (_this.viewInstance.xDataControlName && _this.$refs &&
                            _this.$refs[_this?.viewInstance?.xDataControlName] &&
                            (_this.$refs[_this?.viewInstance?.xDataControlName] as any).ctrl &&
                            (_this.$refs[_this?.viewInstance?.xDataControlName] as any).ctrl.save &&
                            (_this.$refs[_this?.viewInstance?.xDataControlName] as any).ctrl.save instanceof Function) {
                            _this.viewState.next({ tag: _this?.viewInstance?.xDataControlName, action: action, data: Object.assign(_this.viewparams, { showResultInfo: false }) });
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

        // 视图加载服务初始化操作
        this.viewLoadingService.srfsessionid = this.context.srfsessionid;
        this.$store.commit("loadingService/addViewLoadingService", this.viewLoadingService);
        // 视图初始化向导航栈里面加数据
        this.$nextTick(() => {
            this.navDataService.addNavData({ title: this.model.srfCaption, viewType: this.viewInstance.viewType, path: this.$route?.fullPath, viewmode: this.viewDefaultUsage, tag: this.viewInstance.codeName, key: null, data: {} });
        })
        // 处理视图定时器逻辑
        this.handleTimerLogic();
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
            if (this.serviceStateEvent) {
                this.serviceStateEvent.unsubscribe();
            }
            if (AppServiceBase.getInstance() && AppServiceBase.getInstance().getAppStore()) {
                // 清除顶层路由参数
                AppServiceBase.getInstance().getAppStore().commit('removeRouteViewGlobal', this.context.srfsessionid);
                // 清除顶层视图
                AppServiceBase.getInstance().getAppStore().commit('removeView', this.context.srfsessionid);
            }
        }
        // 清除当前视图
        if (AppServiceBase.getInstance() && AppServiceBase.getInstance().getAppStore()) {
            if (this.viewInstance && this.viewInstance.modelPath) {
                AppServiceBase.getInstance().getAppStore().commit('removeView', this.viewInstance.modelPath);
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
        // 视图销毁从导航栈里面删除数据
        this.navDataService.removeNavData(this.viewInstance.codeName);
        // 销毁逻辑定时器
        this.destroyLogicTimer();
    }

    /**
     * 初始化视图操作参数
     *
     * @public
     * @memberof ViewBase
     */
    public initViewCtx(args?: any): void {
        this.viewCtx = { viewNavContext: this.context, viewNavParam: this.viewparams };
        if (AppServiceBase.getInstance() && AppServiceBase.getInstance().getAppStore()) {
            this.viewCtx['appGlobal'] = AppServiceBase.getInstance().getAppStore().getters.getAppGlobal()
        };
        if (AppServiceBase.getInstance().getAppStore().getters.getRouteViewGlobal(this.context.srfsessionid)) {
            this.viewCtx['routeViewGlobal'] = AppServiceBase.getInstance().getAppStore().getters.getRouteViewGlobal(this.context.srfsessionid);
        } else {
            AppServiceBase.getInstance().getAppStore().commit('addRouteViewGlobal', { tag: this.context.srfsessionid, param: {} });
            this.viewCtx['routeViewGlobal'] = AppServiceBase.getInstance().getAppStore().getters.getRouteViewGlobal(this.context.srfsessionid);
        }
        this.viewCtx['viewGlobal'] = {};
        this.viewCtx['viewNavData'] = {};
        this.viewCtx['messagebox'] = AppMessageBoxService.getInstance();
        this.viewCtx['app'] = AppServiceBase.getInstance();
        this.viewCtx['view'] = this;
        // 处理顶层视图
        if (!this.viewDefaultUsage && this.viewdata && !Object.is(this.viewdata, '')) {
            // 嵌入视图
            if (AppServiceBase.getInstance() && AppServiceBase.getInstance().getAppStore()) {
                this.viewCtx['topview'] = AppServiceBase.getInstance().getAppStore().getters.getView(this.context.srfsessionid);;
            }
        } else {
            // 顶层路由视图
            if (AppServiceBase.getInstance() && AppServiceBase.getInstance().getAppStore()) {
                AppServiceBase.getInstance().getAppStore().commit('addView', { tag: this.context.srfsessionid, param: this });
            }
            this.viewCtx['topview'] = this;
        }
        // 处理父层视图
        this.handleParentView();
    }

    /**
     * 处理父级视图数据
     *
     * @memberof ViewBase
     */
    public handleParentView() {
        if (this.context && this.context.parentviewpath) {
            if (AppServiceBase.getInstance() && AppServiceBase.getInstance().getAppStore()) {
                this.viewCtx['parentview'] = AppServiceBase.getInstance().getAppStore().getters.getView(this.context.parentviewpath);;
            } else {
                this.viewCtx['parentview'] = null;
            }
        } else {
            this.viewCtx['parentview'] = null;
        }
        if (this.viewInstance && this.viewInstance.modelPath) {
            if (AppServiceBase.getInstance() && AppServiceBase.getInstance().getAppStore()) {
                AppServiceBase.getInstance().getAppStore().commit('addView', { tag: this.viewInstance.modelPath, param: this });
            }
            Object.assign(this.context, { parentviewpath: this.viewInstance.modelPath });
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
        let _this: any = this;
        this.context = {};
        if (AppServiceBase.getInstance() && AppServiceBase.getInstance().getAppStore()) {
            this.mergeAppData(AppServiceBase.getInstance().getAppStore().getters.getAppData());
        }
        if (!_this.viewDefaultUsage && _this.viewdata && !Object.is(_this.viewdata, '')) {
            if (typeof _this.viewdata == 'string') {
                Object.assign(_this.context, JSON.parse(_this.viewdata));
            }
            if (isNilOrEmpty(_this.context.srfsessionid)) {
                _this.context.srfsessionid = createUUID();
            }
            if (_this.context && _this.context.srfparentdename) {
                Object.assign(_this.viewparams, { srfparentdename: _this.context.srfparentdename });
            }
            if (_this.context && _this.context.srfparentdemapname) {
                Object.assign(_this.viewparams, { srfparentdemapname: _this.context.srfparentdemapname });
            }
            if (_this.context && _this.context.srfparentkey) {
                Object.assign(_this.viewparams, { srfparentkey: _this.context.srfparentkey });
            }
            _this.handleCustomViewData();
            _this.handleOtherViewData();
            return;
        }
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
        ViewTool.formatRouteParams(tempValue, _this.$route, _this.context, _this.viewparams);
        if (inputvalue && ModelTool.getViewAppEntityCodeName(this.viewInstance)) {
            Object.assign(_this.context, { [(ModelTool.getViewAppEntityCodeName(this.viewInstance) as string).toLowerCase()]: inputvalue });
        }
        if (_this.viewInstance && ModelTool.getViewAppEntityCodeName(this.viewInstance)) {
            Object.assign(_this.context, { srfsessionid: Util.createUUID() });
        }
        if (_this.viewparams.srfinsttag && _this.viewparams.srfinsttag2 && this.modelService) {
            let dynainstParam: DynamicInstanceConfig = this.modelService.getDynaInsConfig();
            Object.assign(_this.context, { srfdynainstid: dynainstParam.id });
        }
        if (_this.viewparams && _this.viewparams.srfdynainstid) {
            Object.assign(_this.context, { srfdynainstid: this.viewparams.srfdynainstid });
        }
        // 补充沙箱实例参数（路由）
        if (_this.viewparams && _this.viewparams.hasOwnProperty('srfsandboxtag')) {
            Object.assign(_this.context, { 'srfsandboxtag': _this.viewparams.srfsandboxtag });
        }
        _this.handleCustomViewData();
        _this.handleOtherViewData();
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
        if (appEnvironment.bDynamic && this.modelService) {
            let dynainstParam: DynamicInstanceConfig = this.modelService.getDynaInsConfig();
            if (dynainstParam) {
                Object.assign(this.viewparams, { srfinsttag: dynainstParam.instTag, srfinsttag2: dynainstParam.instTag2 });
            }
        }
    }

    /**
     * 处理指定视图控制关系将父键转为父实体上下文
     *
     * @memberof ViewBase
     */
    public async handleviewRes() { }

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
            LogUtil.log(refs, modelData)
        }
    }

    /**
     *  关闭视图
     *
     * @memberof ViewBase
     */
    public closeView(args: any[]) {
        let view: any = this;
        if (window.opener && Boolean(this.$store.getters['getCustomParamByTag']('srffullscreen'))) {
            window.opener.postMessage({ type: 'CLOSE', data: args }, '*');
            window.close();
            return;
        }
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
        // 视图关闭从导航栈里面删除数据
        this.navDataService.removeNavDataLast();
    }

    /**
     * 关闭视图(视图样式为style2样式)
     *
     * @view {*} 当前视图
     * @memberof ViewBase
     */
    public closeViewWithStyle2(view: any) {
        const navHistory: any = AppServiceBase.getInstance().getAppNavDataService();
        const item: any = navHistory.historyList[navHistory.findHistoryIndex(view.$route)];
        navHistory.remove(item);
        if (navHistory.historyList.length > 0) {
            if (navHistory.isRouteSame(item.to, this.$route)) {
                let go: any = navHistory.historyList[
                    navHistory.historyList.length - 1
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
     * 初始化工具栏数据
     * 
     * @memberof ViewBase
     */
    public initViewToolBar() { }

    /**
     * 渲染视图头部视图消息
     * 
     * @memberof ViewBase
     */
    public renderTopMessage() {
        const msgDetails: any[] = this.viewMessageService.getViewMsgDetails('TOP');
        if (msgDetails.length == 0) {
            return null;
        }
        const topStyle = (this.viewInstance.getPSAppViewMsgGroup() as any)?.topStyle;
        return (
            <div slot="topMessage" class="view-top-message">
                <app-alert
                    position="TOP"
                    showStyle={topStyle}
                    messageDetails={msgDetails}
                    context={Util.deepCopy(this.context)}
                    viewparam={Util.deepCopy(this.viewparam)}
                    infoGroup={this.viewInstance.getPSAppViewMsgGroup()?.codeName}
                    viewname={this.viewInstance.codeName.toLowerCase()}
                ></app-alert>
            </div>
        );
    }

    /**
     * 渲染视图Body视图消息
     * 
     * @memberof ViewBase
     */
    public renderBodyMessage() {
        const msgDetails: any[] = this.viewMessageService.getViewMsgDetails('BODY');
        if (msgDetails.length == 0) {
            return null;
        }
        const bodyStyle = (this.viewInstance.getPSAppViewMsgGroup() as any)?.bodyStyle;
        return (
            <div slot="bodyMessage" class="view-body-message">
                <app-alert
                    position="BODY"
                    showStyle={bodyStyle}
                    messageDetails={msgDetails}
                    context={Util.deepCopy(this.context)}
                    viewparam={Util.deepCopy(this.viewparam)}
                    infoGroup={this.viewInstance.getPSAppViewMsgGroup()?.codeName}
                    viewname={this.viewInstance.codeName.toLowerCase()}
                ></app-alert>
            </div>
        );
    }

    /**
     * 渲染视图底部视图消息
     * 
     * @memberof ViewBase
     */
    public renderBottomMessage() {
        const msgDetails: any[] = this.viewMessageService.getViewMsgDetails('BOTTOM');
        if (msgDetails.length == 0) {
            return null;
        }
        const bottomStyle = (this.viewInstance.getPSAppViewMsgGroup() as any)?.bottomStyle;
        return (
            <div slot="bottomMessage" class="view-bottom-message">
                <app-alert
                    position="BOTTOM"
                    showStyle={bottomStyle}
                    messageDetails={msgDetails}
                    context={Util.deepCopy(this.context)}
                    viewparam={Util.deepCopy(this.viewparam)}
                    infoGroup={this.viewInstance.getPSAppViewMsgGroup()?.codeName}
                    viewname={this.viewInstance.codeName.toLowerCase()}
                ></app-alert>
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
    public computeTargetCtrlData(controlInstance: IPSControl) {
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
                viewCtx: this.viewCtx
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
     *  绘制标题栏
     *
     * @memberof ViewBase
     */
    public renderCaptionBar() {
        const captionBar: any = ModelTool.findPSControlByName('captionbar', this.viewInstance.getPSControls());
        if (this.viewInstance.showCaptionBar) {
            return (
                <div slot="layout-captionbar" class="app-captionbar-container">
                    <app-default-captionbar
                        viewModelData={this.viewInstance}
                        modelData={captionBar}
                        context={this.context}
                        viewparam={this.viewparam}
                    ></app-default-captionbar>
                </div>
            );
        }
    }

    /**
     *  绘制信息栏
     *
     * @memberof ViewBase
     */
    public renderDataInfoBar() {
        const datainfoBar: any = ModelTool.findPSControlByName('datainfobar', this.viewInstance.getPSControls());
        return (
            <div slot="layout-datainfobar" class="app-datainfobar-container">
                <app-default-datainfobar
                    modelData={datainfoBar}
                    viewInfo={this.model}
                    context={this.context}
                    viewparam={this.viewparam}
                ></app-default-datainfobar>
            </div>
        );
    }

    /**
     * 部件事件
     * 
     * @param controlname 部件名称 
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
            if (Object.is(action, 'authlimit')) {
                this.renderShade();
            } else {
                if (controlname && action && this.viewTriggerLogicMap.get(`${controlname.toLowerCase()}-${action.toLowerCase()}`)) {
                    if (this.viewTriggerLogicMap.get(`${controlname.toLowerCase()}-${action.toLowerCase()}`)) {
                        this.viewTriggerLogicMap.get(`${controlname.toLowerCase()}-${action.toLowerCase()}`).executeAsyncUILogic({ arg: data, utils: this.viewCtx, app: this.viewCtx.app, view: this, ctrl: (this.$refs[controlname] as any).ctrl }).then((args: any) => {
                            if (args && args?.hasOwnProperty('srfret') && !args.srfret) {
                                return;
                            }
                            if (_this.engine) {
                                _this.engine.onCtrlEvent(controlname, action, data);
                            }
                        })
                    }
                } else {
                    if (_this.engine) {
                        _this.engine.onCtrlEvent(controlname, action, data);
                    }
                }
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
     * 绘制遮罩
     *
     * @memberof ViewBase
     */
    public renderShade() {
        const currentViewKey = `${this.viewInstance.codeName}`;
        const el: any = currentViewKey ? document.getElementById(currentViewKey) : null;
        if (el) {
            el.classList.add('no-authority-shade');
            const shade = document.createElement('div');
            shade.setAttribute('class', 'no-authority-shade-child');
            el.appendChild(shade);
        }
    }

    /**
     * 初始化视图逻辑
     * 
     * @memberof ViewBase
     */
    public async initViewLogic(opts: any) {
        if (opts.getPSAppViewLogics() && opts.getPSAppViewLogics().length > 0) {
            opts.getPSAppViewLogics().forEach((element: any) => {
                // 目标逻辑类型类型为实体界面逻辑、系统预置界面逻辑、前端扩展插件、脚本代码
                if (element && element.logicTrigger && (Object.is(element.logicType, 'DEUILOGIC') ||
                    Object.is(element.logicType, 'SYSVIEWLOGIC') ||
                    Object.is(element.logicType, 'PFPLUGIN') ||
                    Object.is(element.logicType, 'SCRIPT'))) {
                    switch (element.logicTrigger) {
                        case 'TIMER':
                            this.viewTriggerLogicMap.set(element.name.toLowerCase(), new AppTimerEngine(element));
                            break;
                        case 'CTRLEVENT':
                            if (element?.getPSViewCtrlName() && element?.eventNames) {
                                this.viewTriggerLogicMap.set(`${element.getPSViewCtrlName()?.toLowerCase()}-${element.eventNames?.toLowerCase()}`, new AppCtrlEventEngine(element));
                            }
                            break;
                        case 'VIEWEVENT':
                            if (element?.eventNames) {
                                this.viewTriggerLogicMap.set(`${element.eventNames?.toLowerCase()}`, new AppViewEventEngine(element));
                            }
                            break;
                        default:
                            console.log(`视图${element.logicTrigger}类型暂未支持`);
                            break;
                    }
                }
                // 绑定用户自定义事件
                if (element.eventNames && element.eventNames.toLowerCase().startsWith(this.registerEventSeparator)) {
                    this.$on(element.eventNames, (...args: any) => {
                        this.handleViewCustomEvent(element.name?.toLowerCase(), null, args);
                    });
                }
            });
        }
    }

    /**
     * 处理视图自定义事件
     *
     * @memberof ViewBase
     */
    public handleViewCustomEvent(name: string, data: any, args: any) {
        if (this.viewTriggerLogicMap.get(name)) {
            this.viewTriggerLogicMap.get(name).executeAsyncUILogic({ arg: { sender: this, navContext: this.context, navParam: this.viewparams, navData: this.navdatas, data: data, args: args }, utils: this.viewCtx, app: this.viewCtx.app, view: this });
        }
    }

    /**
     * 处理视图定时器逻辑
     *
     * @memberof ViewBase
     */
    public handleTimerLogic() {
        if (this.viewTriggerLogicMap && this.viewTriggerLogicMap.size > 0) {
            for (let item of this.viewTriggerLogicMap.values()) {
                if (item && (item instanceof AppTimerEngine)) {
                    item.executeAsyncUILogic({ arg: { sender: this, navContext: this.context, navParam: this.viewparams, navData: this.navdatas, data: null }, utils: this.viewCtx, app: this.viewCtx.app, view: this });
                }
            }
        }
    }

    /**
     * 销毁视图定时器逻辑
     *
     * @memberof ViewBase
     */
    public destroyLogicTimer() {
        if (this.viewTriggerLogicMap && this.viewTriggerLogicMap.size > 0) {
            for (let item of this.viewTriggerLogicMap.values()) {
                if (item && (item instanceof AppTimerEngine)) {
                    item.destroyTimer();
                }
            }
        }
    }

}
