import { Emit, Prop, Watch } from "vue-property-decorator";
import { Util } from "ibiz-core";
import { PickUpViewPanelControlBase } from "../../../widgets";

/**
 * 选择视图面板部件基类
 *
 * @export
 * @class AppPickUpViewPanelBase
 * @extends {PickUpViewPanelControlBase}
 */
export class AppPickUpViewPanelBase extends PickUpViewPanelControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppPickUpViewPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppPickUpViewPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPickUpViewPanelBase
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
     * @memberof AppPickUpViewPanelBase
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
     * @memberof AppPickUpViewPanelBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppPickUpViewPanelBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }


    /**
     * 绘制选择视图面板
     *
     * @returns {*}
     * @memberof AppPickUpViewPanelBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        const targetViewParam = {
            isShowButton: this.isShowButton,
            staticProps: {
                isSingleSelect: this.isSingleSelect,
                viewDefaultUsage: false,
                viewState: this.viewState,
                viewtag: this.viewtag,

            },
            dynamicProps: {
                selectedData: this.selectedData,
                viewparam: this.viewparam,
                viewdata: this.viewdata,
                viewparams: Util.deepCopy(this.viewparams),
                context: Util.deepCopy(this.context),
            }
        }
        const targetViewEvent = {
            'viewdataschange': (data: any) => this.onViewDatasChange(data),
            'viewdatasactivated': (data: any) => {
                this.$emit("ctrl-event", { controlname: "pickupviewpanel", action: "activated", data: data });
            },
            'viewload': (data: any) => {
                this.$emit("ctrl-event", { controlname: "pickupviewpanel", action: "load", data: data });
            }
        }
        return (
            <div class={{ ...this.renderOptions.controlClassNames, 'pickupviewpanel': true}}>
                {
                    this.$createElement(this.view.viewName, {
                        props: targetViewParam,
                        on: targetViewEvent,
                        class: 'viewcontainer3'
                    })
                }
            </div>
        )
    }
}