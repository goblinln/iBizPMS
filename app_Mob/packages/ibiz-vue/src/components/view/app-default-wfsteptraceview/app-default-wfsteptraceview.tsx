import { Component, Prop, Watch } from 'vue-property-decorator';
import { WfStepTraceViewBase } from '../../../view';
import { VueLifeCycleProcessing } from '../../../decorators';
import { CreateElement } from 'vue';
import { AppLayoutService } from '../../..';
import { Util } from 'ibiz-core';
/**
 * 应用流程跟踪视图
 *
 * @export
 * @class AppDefaultWfStepTraceView
 * @extends {AppWfStepTraceViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultWfStepTraceView extends WfStepTraceViewBase {
    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppDefaultMobWfDynaExpMDView
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultMobWfDynaExpMDView
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultMobWfDynaExpMDView
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
     * @memberof AppDefaultMobWfDynaExpMDView
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
     * @memberof AppDefaultMobWfDynaExpMDView
     */
    public destroyed() {
        this.viewDestroyed();
    }

    /**
     * 渲染视图主体内容区
     *
     * @memberof AppWfStepTraceViewBase
     */
    public renderMainContent() {
        return this.$createElement('app-wf-step-trace', {
            props: {
                context: this.context,
                viewparams: this.viewparams,
                appEntityCodeName: this.staticProps?.appDeCodeName,
            },
        });
    }

    /**
     * 编辑视图渲染
     *
     * @memberof AppDefaultMobWFDynaStartView
     */
    render(h: CreateElement) {
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(
            `${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`
        );
        return h(
            targetViewLayoutComponent,
            {
                props: {
                    viewInstance: this.viewInstance,
                    viewparams: this.viewparams,
                    context: this.context,
                },
            },
            [this.renderMainContent()]
        );
    }
}
