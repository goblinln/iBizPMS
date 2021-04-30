import { Component, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobListExpViewBase } from '../../../view/mob-list-exp-view-base';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用列表导航视图基类
 *
 * @export
 * @class AppDefaultMobListExpView
 * @extends {ListExpViewBase}
 */
@Component({})
export class AppDefaultMobListExpView extends MobListExpViewBase {


    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppDefaultMobListExpView
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultMobListExpView
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultMobListExpView
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
     * @memberof AppDefaultMobListExpView
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
     * @memberof AppDefaultMobListExpView
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
     * @memberof AppDefaultMobListExpView
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
                this.renderMainContent()
        ]);
    }
}
