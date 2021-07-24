import { Component, Prop, Emit, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { MobDashboardViewBase } from '../../../view';

@Component({})
export class AppDefaultMobDePortalView extends MobDashboardViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultMobDePortalView
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppDefaultMobDePortalView
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultMobDePortalView
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
     * @memberof AppDefaultMobDePortalView
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
     * @memberof AppDefaultMobDePortalView
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * Vue激活声明周期
     *
     * @memberof AppDefaultMobDePortalView
     */
     public activated() {
        this.viewState.next({tag:this.dashboardInstance.name,action:'load'})
        this.viewActivated();
    }

    /**
     * 实体看板视图渲染
     * 
     * @memberof AppDefaultMobDePortalView
     */
    render(h: any){
        if(!this.viewIsLoaded){
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-DEFAULT`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance }
        }, [
            this.renderViewHeaderCaptionBar(),
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderBottomMessage(),            
            this.renderContent(),
            this.renderMainContent(),
            this.renderToolBar()
        ]);
    }
}
