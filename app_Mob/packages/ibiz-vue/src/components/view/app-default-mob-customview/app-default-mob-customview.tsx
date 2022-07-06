import { Component, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { MobCustomViewBase } from '../../../view/mob-custom-view-base';

/**
 * 默认样式移动端自定义视图
 *
 * @export
 * @class AppDefaultMobCustomView
 * @extends {CustomViewBase}
 */
@Component({})
export class AppDefaultMobCustomView extends MobCustomViewBase {

    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppDefaultMobCustomView
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultMobCustomView
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultMobCustomView
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
     * 监听当前视图环境参数变化
     * 
     * @memberof AppDefaultMobCustomView
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
     * @memberof AppDefaultIndexView
     */
    public destroyed() {
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppDefaultMobCustomView
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context, enableControlUIAuth: this.enableControlUIAuth }
        }, [
            this.renderViewHeaderCaptionBar(),
            this.renderToolBar(),
            this.renderTopMessage(),
            this.renderBodyMessage(),
            ...this.renderViewControls(),
            this.renderBottomMessage()
        ]);
    }


}