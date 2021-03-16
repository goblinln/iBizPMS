import Vue from 'vue';
import { Subscription } from 'rxjs';
import { ControlInterface, Util } from 'ibiz-core';
import { CounterServiceRegister } from 'ibiz-service';
import { PluginService } from 'ibiz-vue';

/**
 * 部件基类
 *
 * @export
 * @class ControlBase
 * @extends {Vue}
 */
export class ControlBase extends Vue implements ControlInterface {
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
    public PluginFactory:PluginService = PluginService.getInstance();

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
    public enableDynamicMode!:boolean;

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
    public name?: string;

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
     * 部件事件抛出方法
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname, action, data }
     * @memberof ControlBase
     */
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof ViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        this.ctrlEvent({ controlname, action, data });
    }

    /**
     * 初始化部件的绘制参数
     *
     * @type {Array<*>}
     * @memberof ViewBase
     */
    public initRenderOptions(opts?: any) {
        this.renderOptions = {};
        const { controlType, codeName, getPSSysCss } = this.controlInstance;
        // 部件类名
        const controlClassNames: any = {
            [controlType?.toLowerCase()]: true,
            [Util.srfFilePath2(codeName)]: true,
        };
        Object.assign(controlClassNames,opts);
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
     * @memberof ControlBase
     */
    public computeTargetCtrlData(controlInstance:any) {
        let targetCtrlName: string = `app-control-shell`;
        let targetCtrlParam: any = {
            dynamicProps: {
                viewparams: Util.deepCopy(this.viewparams),
                context: Util.deepCopy(this.context),
            },
            staticProps:{
                viewState: this.viewState,
                viewtag: this.viewtag,
                modelData: controlInstance,
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
    public staticProps!:any;

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
    public setIsMounted(){ }

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
        this.ctrlModelInit().then((res:any) =>{
            this.ctrlInit();
            this.controlIsLoaded = true;
            setTimeout(() => {
                this.ctrlMounted();
                this.setIsMounted();
            }, 0);
        })
    }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof AppDefaultForm
     */
    public async ctrlModelInit(args?:any) {
        this.name = this.controlInstance.name ? this.controlInstance.name : this.controlInstance.codeName;
        this.showBusyIndicator = this.controlInstance.showBusyIndicator;
        this.initRenderOptions();
        await this.initCounterService(this.controlInstance);
    }

    /**
     * 部件初始化
     *
     * @memberof ControlBase
     */
    public ctrlInit(args?:any){}

    /**
     * 部件挂载
     *
     * @memberof ControlBase
     */
    public ctrlMounted(args?:any){}

    /**
     * 部件销毁
     *
     * @memberof ControlBase
     */
    public ctrlDestroyed(args?:any){
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
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
        if(!param){
            return; 
        }
        const { getPSAppCounterRefs: appCounterRef } = param;
        if (appCounterRef && appCounterRef.length > 0) {
            for (const counterRef of appCounterRef) {
                const targetCounterService: any = await CounterServiceRegister.getInstance().getService({ context: this.context, viewparams: this.viewparams }, counterRef.getPSAppCounter.dynaModelFilePath);
                const tempData: any = { id: counterRef.id, path: counterRef.getPSAppCounter.dynaModelFilePath, service: targetCounterService };
                this.counterServiceArray.push(tempData);
            }
        }
    }
}
