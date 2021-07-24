import { Component, Prop, Emit, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { VueLifeCycleProcessing } from '../../../decorators';
import { MobWFDynaActionViewBase } from '../../../view/mob-wf-dyna-action-view-base';

/**
 * 应用实体工作流动态操作视图
 *
 * @export
 * @class AppWFDynaActionViewBase
 * @extends {AppWFDynaActionViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobWFDynaActionView extends MobWFDynaActionViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppWFDynaActionViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppWFDynaActionViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppWFDynaActionViewBase
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
     * @memberof AppWFDynaActionViewBase
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
     * @memberof AppWFDynaActionViewBase
     */
    public destroyed() {
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppWFDynaActionViewBase
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
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderBottomMessage(),            
            this.renderMainContent(),
            this.renderFooter(),
        ]);
    }

}