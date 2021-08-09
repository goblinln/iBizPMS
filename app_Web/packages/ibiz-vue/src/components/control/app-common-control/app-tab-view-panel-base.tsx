import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { TabViewPanelBase } from '../../../widgets';
import { IPSAppDEView } from '@ibiz/dynamic-model-api/dist/types/exports';

/**
 * 分页视图面板部件基类
 *
 * @export
 * @class AppTabViewPanelBase
 * @extends {TabViewPanelBase}
 */
export class AppTabViewPanelBase extends TabViewPanelBase {

    /**
     * 部件静态参数
     *
     * @memberof AppTabViewPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppTabViewPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTabViewPanelBase
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
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTabViewPanelBase
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
     * @memberof AppTabViewPanelBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppTreeExpBarBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制分页视图面板
     *
     * @returns {*}
     * @memberof AppTabViewPanelBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const embedView: IPSAppDEView = this.controlInstance.getEmbeddedPSAppDEView() as IPSAppDEView;
        let tempViewParam: any = this.viewparam;
        let targetCtrlParam: any = {
            staticProps: {
                viewDefaultUsage: false,
                viewModelData: embedView,
                viewUseByExpBar: true
            },
            dynamicProps: {
                viewparam: tempViewParam,
                viewdata: JSON.stringify(this.context)
            }
        };
        return (
            <div class={{ ...controlClassNames, 'tabviewpanel': true }} >
                {
                    this.isActivied && embedView ?
                        this.$createElement('app-view-shell', {
                            props: targetCtrlParam,
                            class: 'viewcontainer2',
                            on: {
                                viewload: this.viewDatasChange.bind(this)
                            }
                        }) : null
                }
            </div>
        );
    }

}