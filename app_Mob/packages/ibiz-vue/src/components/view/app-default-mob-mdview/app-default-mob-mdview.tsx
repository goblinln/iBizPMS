import { Component, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { MobMDViewBase } from '../../../view/mob-md-view-base';

@Component({})
export class AppDefaultMobMdView extends MobMDViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultMdView
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppDefaultMdView
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultMdView
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
     * @memberof AppDefaultMdView
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
     * @memberof AppDefaultMdView
     */
    public destroyed() {
        this.viewDestroyed();
    }

    /**
     * Vue激活声明周期
     *
     * @memberof AppDefaultMdView
     */
    public activated() {
        this.viewActivated();
    }
    
    /**
     * 应用首页视图渲染
     * 
     * @memberof AppDefaultMdView
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-DEFAULT`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance }
        }, [
            this.renderViewHeaderCaptionBar(),
            this.renderViewHeaderButton(),
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderBottomMessage(),            
            this.renderSearchForm(),
            this.renderQuickGroup(),
            this.renderQuickSearch(),
            this.renderViewContent(),
            this.renderMainContent(),
            this.renderToolBar(),
            this.renderScrollTool(),
        ]);
    }
}
