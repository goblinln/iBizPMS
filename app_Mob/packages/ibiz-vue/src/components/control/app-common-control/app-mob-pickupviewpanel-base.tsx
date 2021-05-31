import { Util } from 'ibiz-core';
import { MobPickUpViewPanelControlBase } from '../../../widgets';
import { Prop, Watch, Emit, Component } from "vue-property-decorator";
import { IPSAppDEView } from "@ibiz/dynamic-model-api";

/**
 * 选择视图面板部件基类
 *
 * @export
 * @class AppMobPickUpViewPanelBase
 * @extends {MobPickUpViewPanelControlBase}
 */
@Component({})
export class AppMobPickUpViewPanelBase extends MobPickUpViewPanelControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppMobPickUpViewPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppMobPickUpViewPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobPickUpViewPanelBase
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
     * @memberof AppMobPickUpViewPanelBase
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
     * @memberof AppMobPickUpViewPanelBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobPickUpViewPanelBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }


    /**
     * 绘制视图
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderView() {
        const embeddedView =  this.controlInstance.getEmbeddedPSAppDEView() as IPSAppDEView;
        if (!embeddedView) {
            return;
        }
        const { name, modelFilePath } = embeddedView;
        Object.assign(this.context, { viewpath: modelFilePath })
        return this.$createElement('app-view-shell', {
            props: {
                staticProps: {
                    panelViewState: this.viewState,
                    viewDefaultUsage: 'includedView',
                    isChildView: true,
                    viewModelData: embeddedView,
                    isSingleSelect: this.isSingleSelect
                },
                dynamicProps: {
                    _context: JSON.stringify(this.context),
                    _viewparams: JSON.stringify(this.viewparams),
                },
            },
            ref: name,
            on: {
                'viewdataschange': this.onViewDatasChange.bind(this)
            }
        });
    }


    /**
     * 绘制嵌入视图
     *
     * @returns {*}
     * @memberof AppMobPickUpViewPanelBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return;
        }
        let className = {
            'pickupviewpanel': true,
            ...this.renderOptions.controlClassNames,
        };
        return (
            <div class={className}>
                {this.renderView()}
            </div>
        )
    }
}