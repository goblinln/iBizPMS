import { Component, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobMapViewBase } from '../../../view/mob-map-view-base';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用地图视图基类
 *
 * @export
 * @class AppDefaultMobMapView
 * @extends {MobMapViewBase}
 */
@Component({})
export class AppDefaultMobMapView extends MobMapViewBase {


    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppDefaultMobMapView
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultMobMapView
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultMobMapView
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
     * @memberof AppDefaultMobMapView
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
     * @memberof AppDefaultMobMapView
     */
    public destroyed(){
        this.viewDestroyed();
    }


    /**
     * Vue激活声明周期
     *
     * @memberof AppDefaultMobEditView
     */
    public activated() {
        this.viewActivated();
    }

    /**
     * 分页导航视图渲染
     * 
     * @memberof AppDefaultMobMapView
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
                this.renderViewContent(),
                this.renderMainContent()
        ]);
    }
}
