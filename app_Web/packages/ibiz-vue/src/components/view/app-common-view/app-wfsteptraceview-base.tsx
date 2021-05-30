import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { WfStepTraceViewBase } from '../../../view';

/**
 * 应用流程跟踪视图基类
 *
 * @export
 * @class AppWfStepTraceViewBase
 * @extends {WfStepTraceViewBase}
 */
export class AppWfStepTraceViewBase extends WfStepTraceViewBase {

    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppWfStepTraceViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppWfStepTraceViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppWfStepTraceViewBase
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
     * 监听当前视图环境参数变化
     * 
     * @memberof AppWfStepTraceViewBase
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
     * @memberof AppDefaultIndexView
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
        return this.$createElement('extend-action-timeline',{props:{
            context:this.context,
            viewparams:this.viewparams,
            appEntityCodeName:this.staticProps?.appDeCodeName
        }})
     }

    /**
     * 应用流程跟踪视图渲染
     * 
     * @memberof AppWfStepTraceViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderMainContent(),
        ]);
    }


}