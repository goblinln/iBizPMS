import { CreateElement } from 'vue';
import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { ChartExpViewBase } from '../../../view/chartexpview-base';
import { AppLayoutService } from '../../../app-service';

export class AppChartExpViewBase extends ChartExpViewBase {
     
    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppChartExpViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppChartExpViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppChartExpViewBase
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
     * 监听视图静态参数变化
     * 
     * @memberof AppChartExpViewBase
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
     * @memberof AppChartExpViewBase
     */
    public destroyed() {
        this.viewDestroyed();
    }

    /**
     * 渲染视图
     *
     * @memberof AppChartExpViewBase
     */
    public render(h: CreateElement) {
        if (!this.viewIsLoaded) {
            return;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance?.viewType}-${this.viewInstance?.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderToolBar(),
            this.renderBodyMessage(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }
}