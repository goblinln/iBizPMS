import { Component, Prop, Emit, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { MobPickUpViewBase } from '../../../view';

@Component({})
export class AppDefaultPickUpView extends MobPickUpViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultPickUpView
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppDefaultPickUpView
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultPickUpView
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
     * @memberof AppDefaultPickUpView
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
     * @memberof AppDefaultPickUpView
     */
    public destroyed() {
        this.viewDestroyed();
    }

    /**
     * Vue激活声明周期
     *
     * @memberof AppDefaultPickUpView
     */
     public activated() {
        this.viewActivated();
    }

    /**
     * 选择视图渲染
     * 
     * @memberof AppDefaultPickUpView
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance }
        }, [
            this.renderViewHeaderCaptionBar(),
            this.renderContent(),
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderBottomMessage(),            
            this.renderMainContent(),
            this.renderFooter()
        ]);
    }

}
