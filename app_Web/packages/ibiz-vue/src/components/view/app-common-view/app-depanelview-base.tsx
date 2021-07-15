import { Util } from 'ibiz-core';
import { Prop, Watch } from 'vue-property-decorator';
import { AppLayoutService } from '../../../app-service';
import { DePanelViewBase } from '../../../view';

/**
 * 应用实体面板视图基类
 *
 * @export
 * @class AppDataViewExpViewBase
 * @extends {DataViewExpBase}
 */
export class AppDePanelViewBase extends DePanelViewBase {
    
    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppDePanelViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDePanelViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDePanelViewBase
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
     * @memberof AppDePanelViewBase
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
     * @memberof AppDefaultIndexView
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof ExpViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.panelInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.panelInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 数据视图渲染
     * 
     * @memberof AppDataViewExpViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
          return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderToolBar(),
            this.renderBodyMessage(),
            this.renderMainContent(),
            this.renderBottomMessage(),
        ]);
    }
}