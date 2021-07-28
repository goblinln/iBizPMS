import Vue from 'vue';
import { Subscription } from 'rxjs';
import { AppCtrlEventEngine, AppCustomEngine, AppModelService, AppServiceBase, AppTimerEngine, ControlInterface, GetModelService, LogUtil, Util } from 'ibiz-core';
import { CounterServiceRegister } from 'ibiz-service';
import { PluginService } from 'ibiz-vue';
import { IPSControl, IPSAppCounterRef } from '@ibiz/dynamic-model-api';

/**
 * 部件基类
 *
 * @export
 * @class ControlBase
 * @extends {Vue}
 */
export class ControlBase extends Vue implements ControlInterface {

    /**
     * 环境文件
     * 
     * @type {any}
     * @protected
     * @memberof ControlBase
     */
    protected Environment: any = AppServiceBase.getInstance().getAppEnvironment();

    /**
     * 部件UI是否存在权限
     *
     * @type {boolean}
     * @memberof ControlBase
     */
    public enableControlUIAuth: boolean = true;

    /**
     * 视图标识
     *
     * @type {*}
     * @memberof ControlBase
     */
    public viewtag: any;

    /**
     * 插件工厂
     *
     * @type {*}
     * @memberof ControlBase
     */
    public PluginFactory: PluginService = PluginService.getInstance();

    /**
     * 显示处理提示
     *
     * @type {boolean}
     * @memberof ControlBase
     */
    public showBusyIndicator: boolean = true;

    /**
     * 是否启用动态模式
     *
     * @type {boolean}
     * @memberof ControlBase
     */
    public enableDynamicMode!: boolean;

    /**
     * 部件模型实例对象
     *
     * @type {*}
     * @memberof ControlBase
     */
    public controlInstance!: any;

    /**
     * 名称
     *
     * @type {string}
     * @memberof ControlBase
     */
    public name!: string;

    /**
     * 视图通讯对象
     *
     * @type {*}
     * @memberof ControlBase
     */
    public viewState: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof ControlBase
     */
    public context: any = {};

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof ControlBase
     */
    public viewparams: any = {};

    /**
     * 拷贝应用上下文
     *
     * @type {*}
     * @memberof ControlBase
     */
     public copyContext: any = {};

     /**
      * 拷贝视图参数
      *
      * @type {*}
      * @memberof ControlBase
      */
     public copyViewparams: any = {};

    /**
     * 模型数据是否加载完成
     * 
     * @memberof ControlBase
     */
    public controlIsLoaded: boolean = false;

    /**
     * 绘制参数
     *
     * @type {*}
     * @memberof AppDefaultSearchForm
     */
    public renderOptions: any = {
        controlClassNames: {}
    };

    /**
     * 部件服务对象
     *
     * @type {*}
     * @memberof ControlBase
     */
    public service: any;

    /**
     * 实体服务对象
     *
     * @type {*}
     * @memberof ControlBase
     */
    public appEntityService: any;

    /**
     * 订阅视图状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof ControlBase
     */
    public viewStateEvent: Subscription | undefined;

    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof ControlBase
     */
    public counterServiceArray: Array<any> = [];

    /**
     * 模型服务
     *
     * @type {AppModelService}
     * @memberof ControlBase
     */
    public modelService !: AppModelService;

    /**
     * 部件是否加载完成(判断是否加载中)
     *
     * @readonly
     * @memberof ControlBase
     */
    public isControlLoaded: boolean = false;

    /**
     * 部件ID
     * 
     * @memberof ControlBase
     */
    public controlId: string = '';

    /**
     * 外部传入数据对象
     *
     * @type {*}
     * @memberof ControlBase
     */
    public navdatas?: any;

    /**
     * 界面触发逻辑Map
     * 
     * @memberof ControlBase
     */
    public ctrlTriggerLogicMap: Map<string, any> = new Map();

    /**
     * 部件事件抛出方法
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname, action, data }
     * @memberof ControlBase
     */
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof ViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (action == 'controlIsMounted') {
            this.setIsMounted(controlname);
        } else {
            this.ctrlEvent({ controlname, action, data });
        }
    }

    /**
     * 初始化部件的绘制参数
     *
     * @type {Array<*>}
     * @memberof ViewBase
     */
    public initRenderOptions(opts?: any) {
        this.renderOptions = {};
        const { controlType, codeName } = this.controlInstance;
        // 部件类名
        const controlClassNames: any = {
            'control-container': true,
            [controlType?.toLowerCase()]: true,
            [Util.srfFilePath2(codeName)]: true,
        };
        Object.assign(controlClassNames, opts);
        if (this.controlInstance?.getPSSysCss?.()?.cssName) {
            Object.assign(controlClassNames, { [this.controlInstance.getPSSysCss()?.cssName]: true });
        }
        this.$set(this.renderOptions, 'controlClassNames', controlClassNames);
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {*} controlInstance 要绘制的部件实例
     * @param {*} [otherParam] 其他参数
     * @returns
     * @memberof ControlBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const targetCtrlName: string = `app-control-shell`;
        const targetCtrlParam: any = {
            dynamicProps: {
                viewparams: Util.deepCopy(this.viewparams),
                context: Util.deepCopy(this.context),
            },
            staticProps: {
                viewState: this.viewState,
                viewtag: this.viewtag,
                modelData: controlInstance,
            }
        };
        Object.defineProperty(targetCtrlParam.staticProps, 'modelData', { enumerable: false, writable: true });
        const targetCtrlEvent: any = {
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
     * 获取部件类型
     *
     * @returns {string}
     * @memberof ControlBase
     */
    public getControlType(): string {
        return this.controlInstance.controlType;
    }

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof ControlBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 静态输入属性
     *
     * @returns {any}
     * @memberof ControlBase
     */
    public staticProps!: any;

    /**
     * 动态输入属性
     *
     * @returns {any}
     * @memberof ControlBase
     */
    public dynamicProps!: any;

    /**
     * 获取单项数据
     *
     * @returns {*}
     * @memberof ControlBase
     */
    public getData(): any {
        return null;
    }

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal?.context && newVal.context !== oldVal?.context) {
            this.context = newVal.context;
            this.copyContext = Util.deepCopy(newVal.context);
        }
        if (newVal?.viewparams && newVal.viewparams !== oldVal?.viewparams) {
            this.viewparams = newVal.viewparams;
            this.copyViewparams = Util.deepCopy(newVal.viewparams);
        }
        if (newVal?.navdatas && newVal.navdatas !== oldVal?.navdatas) {
            this.navdatas = newVal.navdatas;
        }
    }

    /**
     * 监听导航数据参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ControlBase
     */
    public setNavdatas(args: any) {
        this.navdatas = args;
        if(Util.isExistData(this.navdatas)){
            this.handleCustomCtrlData();
        }else{
            this.context = Util.deepCopy(this.copyContext);
            this.viewparams = Util.deepCopy(this.copyViewparams);
        }
    }

    /**
     * 挂载状态集合
     *
     * @type {Map<string,boolean>}
     * @memberof ControlBase
     */
    public mountedMap: Map<string, boolean> = new Map();

    /**
     * 是否部件已经完成ctrlMounted
     *
     * @type {boolean}
     * @memberof ControlBase
     */
    public hasCtrlMounted: boolean = false;

    /**
     * 初始化挂载状态集合
     *
     * @memberof ControlBase
     */
    public initMountedMap() {
        let controls = this.controlInstance?.getPSControls?.();
        controls?.forEach((item: any) => {
            if (item.controlType == "CONTEXTMENU" || item.controlType == "TOOLBAR") {
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
     * @memberof ControlBase
     */
    public setIsMounted(name: string = 'self') {
        this.mountedMap.set(name, true);
        if ([...this.mountedMap.values()].indexOf(false) == -1) {
            // 执行ctrlMounted
            if (!this.hasCtrlMounted) {
                this.$nextTick(() => {
                    this.ctrlMounted();
                })
            }
        }
    }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.controlInstance = this.staticProps.modelData;
        this.viewState = this.staticProps.viewState;
        this.viewtag = this.staticProps.viewtag;
        this.controlId = this.staticProps.controlId;
        this.ctrlModelInit().then((res: any) => {
            this.ctrlInit();
            this.controlIsLoaded = true;
            setTimeout(() => {
                this.setIsMounted();
            }, 0);
        })
    }

    /**
    * 初始化模型服务
    *
    * @memberof ControlBase
    */
    public async initModelService() {
        this.modelService = await GetModelService(this.context);
    }

    /**
     * 部件模型数据加载
     *
     * @memberof ControlBase
     */
    public async ctrlModelLoad() {
        // 部件子部件数据加载
        if (this.controlInstance?.getPSControls?.()) {
            for (const control of this.controlInstance.getPSControls() as IPSControl[]) {
                await control.fill();
            }
        }
    }

    /**
     * 处理部件UI请求
     *
     * @memberof ControlBase
     */
    public onControlRequset(action: string, context: any, viewparam: any) { }

    /**
     * 处理部件UI响应
     *
     * @memberof ControlBase
     */
    public onControlResponse(action: string, response: any) { }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof ControlBase
     */
    public async ctrlModelInit(args?: any) {
        await this.initModelService();
        await this.ctrlModelLoad();
        this.initMountedMap();
        this.name = this.controlInstance.name ? this.controlInstance.name : this.controlInstance.codeName;
        this.showBusyIndicator = this.controlInstance.showBusyIndicator;
        this.initRenderOptions();
        await this.initCounterService(this.controlInstance);
        await this.initControlLogic(this.controlInstance);
    }

    /**
     * 部件初始化
     *
     * @memberof ControlBase
     */
    public ctrlInit(args?: any) { 
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(
                ({ tag, action, data }: { tag: string; action: string; data: any }) => {
                    if (!Object.is(tag, this.name)) {
                        return;
                    }
                    if (Object.is('reset', action)) {
                        this.onReset();
                    }
                },
            );
        }
    }

    /**
     * 部件挂载
     *
     * @memberof ControlBase
     */
    public ctrlMounted(args?: any) {
        this.hasCtrlMounted = true;
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'controlIsMounted',
            data: true
        })
    }

    /**
     * 部件销毁
     *
     * @memberof ControlBase
     */
    public ctrlDestroyed(args?: any) {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
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
    }

    /**
     *  计数器刷新
     *
     * @memberof ControlBase
     */
    public counterRefresh() {
        if (this.counterServiceArray && this.counterServiceArray.length > 0) {
            this.counterServiceArray.forEach((item: any) => {
                let counterService = item.service;
                if (counterService && counterService.refreshData && counterService.refreshData instanceof Function) {
                    counterService.refreshData();
                }
            })
        }
    }

    /**
     * 关闭视图
     *
     * @param {any} args
     * @memberof ControlBase
     */
    public closeView(args: any): void {
        this.$emit('closeView', [args]);
    }

    /**
     * 初始化计数器服务
     * 
     * @memberof ControlBase
     */
    public async initCounterService(param: any) {
        if (!param) {
            return;
        }
        const appCounterRef: Array<IPSAppCounterRef> = param.getPSAppCounterRefs?.() || [];
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
     * 初始化部件逻辑
     * 
     * @memberof ControlBase
     */
    public async initControlLogic(opts: any) {
        if (opts.getPSControlLogics() && opts.getPSControlLogics().length > 0) {
            opts.getPSControlLogics().forEach((element: any) => {
                // 目标逻辑类型类型为实体界面逻辑、系统预置界面逻辑、前端扩展插件、脚本代码
                if (element && element.triggerType && (Object.is(element.logicType, 'DEUILOGIC') ||
                    Object.is(element.logicType, 'SYSVIEWLOGIC') ||
                    Object.is(element.logicType, 'PFPLUGIN') ||
                    Object.is(element.logicType, 'SCRIPT'))) {
                    switch (element.triggerType) {
                        case 'CUSTOM':
                            this.ctrlTriggerLogicMap.set(element.name.toLowerCase(), new AppCustomEngine(element));
                            break;
                        case 'CTRLEVENT':
                            this.ctrlTriggerLogicMap.set(element.name.toLowerCase(), new AppCtrlEventEngine(element));
                            break;
                        case 'TIMER':
                            this.ctrlTriggerLogicMap.set(element.name.toLowerCase(), new AppTimerEngine(element));
                            break;
                        default:
                            console.log(`${element.triggerType}类型暂未支持`);
                            break;
                    }
                }
            });
        }
    }

    /**
     * 处理自定义部件导航数据
     *
     * @memberof ControlBase
     */
    public handleCustomCtrlData() {
        const customCtrlNavContexts = this.controlInstance.getPSControlNavContexts();
        const customCtrlParams = this.controlInstance.getPSControlNavParams();
        if (customCtrlNavContexts && (customCtrlNavContexts.length > 0)) {
            customCtrlNavContexts.forEach((item: any) => {
                let tempContext: any = {};
                let curNavContext: any = item;
                this.handleCustomDataLogic(curNavContext, tempContext, item.key);
                Object.assign(this.context, tempContext);
            })
        }
        if (customCtrlParams && (customCtrlParams.length > 0)) {
            customCtrlParams.forEach((item: any) => {
                let tempParam: any = {};
                let curNavParam: any = item;
                this.handleCustomDataLogic(curNavParam, tempParam, item.key);
                Object.assign(this.viewparams, tempParam);
            })
        }
    }

    /**
     * 处理部件自定义导航参数逻辑
     *
     * @memberof ControlBase
     */
    public handleCustomDataLogic(curNavData: any, tempData: any, item: string) {
        const navDatas: any = Array.isArray(this.navdatas) ? this.navdatas[0] : this.navdatas;
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
                return;
            } else if (this.viewparams[(curNavData.value).toLowerCase()] != null) {
                Object.defineProperty(tempData, item.toLowerCase(), {
                    value: this.viewparams[(curNavData.value).toLowerCase()],
                    writable: true,
                    enumerable: true,
                    configurable: true
                });
                return;
            } else if (navDatas[(curNavData.value).toLowerCase()] != null) {
                Object.defineProperty(tempData, item.toLowerCase(), {
                    value: navDatas[(curNavData.value).toLowerCase()],
                    writable: true,
                    enumerable: true,
                    configurable: true
                });
                return;
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

    /**
     * 重置
     *
     * @memberof ControlBase
     */
    public onReset() {
        LogUtil.warn(`${this.controlInstance.name}重置功能暂未实现`);
    }
}