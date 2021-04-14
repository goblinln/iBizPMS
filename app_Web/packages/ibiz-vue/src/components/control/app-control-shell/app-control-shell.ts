import { Vue, Component, Prop, Inject, Watch, Emit } from 'vue-property-decorator';
import { AppComponentService } from '../../../app-service/common-service/app-component-service';

/**
 * 部件壳
 *
 * @export
 * @class AppControlShell
 * @extends {Vue}
 */
@Component({})
export class AppControlShell extends Vue {
    /**
     * 部件静态参数
     *
     * @memberof AppDefaultForm
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppDefaultForm
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听当前视图环境参数变化
     *
     * @memberof AppDefaultEditView
     */
    @Watch('staticProps', {
        immediate: true,
        deep: true,
    })
    public onstaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && newVal != oldVal) {
            this.initControlData(newVal);
        }
    }

    /**
     * 部件组件名称
     *
     * @type {any}
     * @memberof ViewBase
     */
    public controlComponentName: string = '';

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppControlShell
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 初始化组件数据
     *
     * @param {ControlContext} opts
     * @memberof AppControlShell
     */
    public initControlData(opts: any) {
        if(opts.modelData && opts.modelData.getPSSysPFPlugin){
            this.controlComponentName = AppComponentService.getControlComponents(
                opts.modelData?.controlType,
                opts.modelData?.controlStyle || 'DEFAULT',
                opts.modelData?.getPSSysPFPlugin?.pluginCode
            );
        }else{
            this.controlComponentName = AppComponentService.getControlComponents(
                opts.modelData?.controlType,
                opts.modelData?.controlStyle || 'DEFAULT',
            );
        }
    }

    /**
     * 获取部件引用
     *
     * @readonly
     * @memberof AppControlShell
     */
    public get ctrl(){
        return this.$refs.ctrl;
    }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppControlShell
     */
    public render(): any {
        if (!this.controlComponentName) {
            return;
        }
        const controlId = `${this.staticProps?.modelData?.appDataEntity?.codeName + this.staticProps?.modelData?.codeName}control`;
        return this.$createElement(this.controlComponentName, {
            props: { staticProps: this.staticProps, dynamicProps: this.dynamicProps },
            ref:'ctrl',
            on: {
                'ctrl-event': this.ctrlEvent,
                'closeView': ($event: any) => {
                    this.$emit('closeView', $event);
                }
            },
            domProps:{
                id:controlId
            }
        });
    }
}
