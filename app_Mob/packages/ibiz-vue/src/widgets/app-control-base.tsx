import Vue from 'vue';
import { Subscription } from 'rxjs';
import { ControlInterface, Util } from 'ibiz-core';
import { CounterServiceRegister } from 'ibiz-service';
import { PluginService } from '../app-service/common-service';
import { AppServiceBase } from 'ibiz-core';
import { IPSControl } from '@ibiz/dynamic-model-api';

/**
 * 部件基类
 *
 * @export
 * @class AppControlBase
 * @extends {Vue}
 */
export class AppControlBase extends Vue implements ControlInterface {
    /**
     * 环境文件
     * 
     * @type {any}
     * @protected
     * @memberof ControlBase
     */
    protected Environment: any = AppServiceBase.getInstance().getAppEnvironment();

    /**
     * 视图标识
     *
     * @type {*}
     * @memberof AppControlBase
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
     * @memberof AppControlBase
     */
    public showBusyIndicator: boolean = true;

    /**
     * 是否启用动态模式
     *
     * @type {boolean}
     * @memberof AppControlBase
     */
    public enableDynamicMode!: boolean;

    /**
     * 部件模型实例对象
     *
     * @type {*}
     * @memberof AppControlBase
     */
    public controlInstance!: any;

    /**
     * 名称
     *
     * @type {string}
     * @memberof AppControlBase
     */
    public name?: string;

    /**
     * 视图通讯对象
     *
     * @type {*}
     * @memberof AppControlBase
     */
    public viewState: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppControlBase
     */
    public context: any = {};

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppControlBase
     */
    public viewparams: any = {};

    /**
     * 模型数据是否加载完成
     * 
     * @memberof AppControlBase
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
     * @memberof AppControlBase
     */
    public service: any;

    /**
     * 实体服务对象
     *
     * @type {*}
     * @memberof AppControlBase
     */
    public appEntityService: any;

    /**
     * 订阅视图状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof AppControlBase
     */
    public viewStateEvent: Subscription | undefined;

    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof AppControlBase
     */
    public counterServiceArray: Array<any> = [];

    /**
     * 挂载状态集合
     *
     * @type {Map<string,boolean>}
     * @memberof AppControlBase
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
     * 部件事件抛出方法
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname, action, data }
     * @memberof AppControlBase
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
        if(!opts){
            return;
        }
        this.renderOptions = {};
        const { controlType, codeName, getPSSysCss } = this.controlInstance;
        // 部件类名
        const controlClassNames: any = {
            [controlType?.toLowerCase()]: true,
            [Util.srfFilePath2(codeName)]: true,
        };
        Object.assign(controlClassNames, opts);
        if (getPSSysCss?.cssName) {
            Object.assign(controlClassNames, { [getPSSysCss?.cssName]: true });
        }
        this.$set(this.renderOptions, 'controlClassNames', controlClassNames);
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {*} controlInstance 要绘制的部件实例
     * @param {*} [otherParam] 其他参数
     * @returns
     * @memberof AppControlBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        let targetCtrlName: string = `app-control-shell`;
        let targetCtrlParam: any = {
            dynamicProps: {
                viewparams: this.viewparams,
                context: this.context,
            },
            staticProps: {
                viewState: this.viewState,
                viewtag: this.viewtag,
                modelData: controlInstance,
            }
        };
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
     * 获取部件类型
     *
     * @returns {string}
     * @memberof AppControlBase
     */
    public getControlType(): string {
        return this.controlInstance.controlType;
    }

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof AppControlBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 静态输入属性
     *
     * @returns {any}
     * @memberof AppControlBase
     */
    public staticProps!: any;

    /**
     * 动态输入属性
     *
     * @returns {any}
     * @memberof AppControlBase
     */
    public dynamicProps!: any;

    /**
     * 获取单项数据
     *
     * @returns {*}
     * @memberof AppControlBase
     */
    public getData(): any {
        return null;
    }

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal?.context && newVal.context !== oldVal?.context) {
            this.context = newVal.context;
        }
        if (newVal?.viewparams && newVal.viewparams !== oldVal?.viewparams) {
            this.viewparams = newVal.viewparams;
        }
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
     * @memberof AppControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.controlInstance = this.staticProps.modelData;
        this.viewState = this.staticProps.viewState;
        this.viewtag = this.staticProps.viewtag;
        this.ctrlModelInit().then((res: any) => {
            this.ctrlInit();
            this.controlIsLoaded = true;
            setTimeout(() => {
                this.ctrlMounted();
                this.setIsMounted();
            }, 0);
        })
    }

    /**
     * 部件模型数据加载
     *
     * @memberof AppControlBase
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
     * 部件模型数据初始化实例
     *
     * @memberof AppControlBase
     */
    public async ctrlModelInit(args?: any) {
        await this.ctrlModelLoad();
        this.initMountedMap();
        this.name = this.controlInstance?.name ? this.controlInstance.name : this.controlInstance?.codeName;
        this.showBusyIndicator = this.controlInstance?.showBusyIndicator;
        this.initRenderOptions();
        await this.initCounterService(this.controlInstance);
    }

    /**
     * 部件初始化
     *
     * @memberof AppControlBase
     */
    public ctrlInit(args?: any) { }

    /**
     * 部件挂载
     *
     * @memberof AppControlBase
     */
    public ctrlMounted(args?: any) {
        this.hasCtrlMounted = true;
        this.ctrlEvent({
            controlname: this.controlInstance?.name,
            action: 'controlIsMounted',
            data: true
        })
    }

    /**
     * 部件销毁
     *
     * @memberof AppControlBase
     */
    public ctrlDestroyed(args?: any) {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }

    /**
     *  计数器刷新
     *
     * @memberof AppControlBase
     */
    public counterRefresh() {
        if (this.counterServiceArray && this.counterServiceArray.length > 0) {
            this.counterServiceArray.forEach((item: any) => {
                if (item.refreshData && item.refreshData instanceof Function) {
                    item.refreshData();
                }
            })
        }
    }

    /**
     * 关闭视图
     *
     * @param {any} args
     * @memberof AppControlBase
     */
    public closeView(args: any): void {
        this.$emit('closeView', [args]);
    }

    /**
     * 初始化计数器服务
     * 
     * @memberof AppControlBase
     */
    public async initCounterService(param: any) {
        if (!param) {
            return;
        }
        const appCounterRef =  param.getPSAppCounterRefs?.();
        if (appCounterRef && appCounterRef.length > 0) {
            for (const counterRef of appCounterRef) {
                const path = counterRef.getPSAppCounter()?.modelPath;
                const targetCounterService: any = await CounterServiceRegister.getInstance().getService({ context: this.context, viewparams: this.viewparams }, path);
                const tempData: any = { id: counterRef.id, path: path, service: targetCounterService };
                this.counterServiceArray.push(tempData);
            }
        }
    }
}
