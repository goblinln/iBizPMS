import {  Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { ChartViewBase } from '../../../view/chartview-base';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用图表视图基类
 *
 * @export
 * @class AppChartViewBase
 * @extends {ChartViewBase}
 */
export class AppChartViewBase extends ChartViewBase {

    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppChartViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppChartViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppChartViewBase
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
     * @memberof AppChartViewBase
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
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppChartViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderToolBar(),
            this.renderQuickSearch(),
            this.renderQuickGroup(),
            this.renderQuickSearchForm(),
            this.viewInstance?.viewStyle == "STYLE2" ? [this.renderSearchForm()] : null,
            this.renderBodyMessage(),
            this.renderMainContent(),
            this.renderBottomMessage(),
        ]);
    }


}