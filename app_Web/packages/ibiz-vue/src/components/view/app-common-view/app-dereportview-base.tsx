import { Util } from 'ibiz-core';
import { CreateElement } from 'vue';
import { DeReportViewBase } from '../../../view/dereportview-base';
import { AppLayoutService } from '../../../app-service/common-service/app-layout-service';
import { Prop, Watch } from 'vue-property-decorator';

/**
 * 应用实体报表视图基类
 *
 * @export
 * @class AppDeReportViewBase
 * @extends {DeReportViewBase}
 */
export class AppDeReportViewBase extends DeReportViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDeReportViewBase
     */
    @Prop() public dynamicProps!: any;

     /**
      * 视图静态参数
      *
      * @type {string}
      * @memberof AppDeReportViewBase
      */
    @Prop() public staticProps!: any;
 
     /**
      * 监听视图动态参数变化
      *
      * @param {*} newVal
      * @param {*} oldVal
      * @memberof AppDeReportViewBase
      */
    @Watch('dynamicProps',{
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            super.onDynamicPropsChange(newVal,oldVal);
        }
    }
 
    /**
     * 监听视图静态参数变化
     * 
     * @memberof AppDeReportViewBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            super.onStaticPropsChange(newVal,oldVal);
        }
    }
 
    /**
     * 销毁视图回调
     *
     * @memberof AppDeReportViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof DeReportViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.reportPanelInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.reportPanelInstance?.name, on: targetCtrlEvent },);
    }

    /**
     * 实体报表视图渲染
     * 
     * @memberof AppDeReportViewBase
     */
    render(h:CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent:any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderToolBar(),
            this.renderQuickGroup(),
            this.renderQuickSearch(),
            this.renderQuickSearchForm(),
            this.renderSearchBar(),
            this.renderBodyMessage(),
            this.renderMainContent(),
            this.renderBottomMessage(),
        ]);
    }
}