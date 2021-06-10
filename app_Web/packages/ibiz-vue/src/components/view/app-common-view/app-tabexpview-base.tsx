import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { TabExpViewBase } from '../../../view/tabexpview-base';
import { CreateElement } from 'vue';
import { AppLayoutService } from '../../../app-service/common-service/app-layout-service';

/**
 * 应用分页导航视图基类
 *
 * @export
 * @class AppTabExpViewBase
 * @extends {TabExpViewBase}
 */
export class AppTabExpViewBase extends TabExpViewBase {


    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppTabExpViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppTabExpViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTabExpViewBase
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
     * 监听静态参数变化
     * 
     * @memberof AppTabExpViewBase
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
     * 渲染数据面板
     * 
     * @memberof AppTabExpViewBase
     */
    public renderDataPanel() {
        if (this.dataPanelInstance) {
            let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.dataPanelInstance);
            return this.$createElement(targetCtrlName, { slot: 'datapanel', ref: this.dataPanelInstance?.name, props: targetCtrlParam, on: targetCtrlEvent });
        }
    }

    /**
     *  视图销毁
     *
     * @memberof ViewBase
     */
    public destroyed() {
        this.viewDestroyed();
    }

    /**
     * 分页导航视图渲染
     * 
     * @memberof AppTabExpViewBase
     */
    render(h: CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance?.viewType}-${this.viewInstance?.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderCaptionInfo(),
            this.renderDataPanel(),
            this.renderBodyMessage(),
            this.renderToolBar(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }
}
