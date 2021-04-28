
import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobTabViewPanelControlBase } from '../../../widgets';

/**
 * 分页视图面板部件基类
 *
 * @export
 * @class AppMobTabViewPanelBase
 * @extends {MobTabViewPanelControlBase}
 */
export class AppMobTabViewPanelBase extends MobTabViewPanelControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppMobTabViewPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppMobTabViewPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobTabViewPanelBase
     */
    @Watch('dynamicProps', {
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onDynamicPropsChange(newVal, oldVal);
        }
    }

    /**
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobTabViewPanelBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onStaticPropsChange(newVal, oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppMobTabViewPanelBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobTabViewPanelBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppMobTabViewPanelBase
     */
    public render(): any {
        if (!this.controlIsLoaded) {
            return null;
        }
        if (!this.isActivied) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const embedView = this.controlInstance.getEmbeddedPSAppDEView();
        const staticProps = Object.assign(this.staticProps, { viewDefaultUsage: "includedView" ,isChildView: true,viewModelData:embedView})
        const dynamicProps = Object.assign({ _context: JSON.stringify(this.context)}, { _viewparams: JSON.stringify(this.viewparams)})
        return (
            <div class={{ ...controlClassNames, 'app-tab-view-panel': true }}>
                {
                    this.$createElement('app-view-shell', {
                        props: { staticProps: staticProps, dynamicProps: dynamicProps },
                        ref: this.controlInstance.name,
                        on: {
                            close: (args: any)=>{
                                this.closeView(args);
                            }
                        }
                    })
                }
            </div>
        )

    }
}
