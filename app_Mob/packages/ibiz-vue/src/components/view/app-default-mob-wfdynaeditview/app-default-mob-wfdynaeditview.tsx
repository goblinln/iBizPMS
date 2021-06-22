import { Component, Prop, Emit, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { VueLifeCycleProcessing } from '../../../decorators';
import { MobWFDynaEditViewBase } from '../../../view/mob-wf-dyna-edit-view-base';

/**
 * 应用实体工作流动态编辑视图
 *
 * @export
 * @class AppDefaultWFDynaEditView
 * @extends {AppWFDynaEditViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobWFDynaEditView extends MobWFDynaEditViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppWFDynaEditViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppWFDynaEditViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppWFDynaEditViewBase
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
     * @memberof AppWFDynaEditViewBase
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
     * @memberof AppWFDynaEditViewBase
     */
    public destroyed() {
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppWFDynaEditViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderViewHeaderCaptionBar(),
            this.renderContent(),
            this.renderMainContent(),
            this.renderFooter(),
        ]);
    }

}