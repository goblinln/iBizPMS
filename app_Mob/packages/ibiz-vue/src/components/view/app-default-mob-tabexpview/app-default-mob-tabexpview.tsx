import { Component, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { MobTabExpViewBase } from '../../../view';

@Component({})
export class AppDefaultMobTabExpView extends MobTabExpViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultMobTabExpView
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppDefaultMobTabExpView
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultMobTabExpView
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
     * @memberof AppDefaultMobTabExpView
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
     * @memberof AppDefaultMobTabExpView
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * Vue激活声明周期
     *
     * @memberof AppDefaultMobTabExpView
     */
     public activated() {
        this.viewActivated();
    }

    /**
     * 应用首页视图渲染
     * 
     * @memberof AppDefaultMobTabExpView
     */
    render(h: any){
        if(!this.viewIsLoaded){
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-DEFAULT`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance }
        }, [
            this.readerViewHeadersSegment(),
            this.renderViewHeaderCaptionBar(),
            this.renderContent(),
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderBottomMessage(),            
            this.renderMainContent(),
            this.renderToolBar()
        ]);
    }
}
