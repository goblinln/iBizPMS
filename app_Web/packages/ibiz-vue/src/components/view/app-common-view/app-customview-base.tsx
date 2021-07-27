import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { CustomViewBase } from '../../../view/customview-base';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用自定义视图基类
 *
 * @export
 * @class AppCustomViewBase
 * @extends {CustomViewBase}
 */
export class AppCustomViewBase extends CustomViewBase {

    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppCustomViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppCustomViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppCustomViewBase
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
     * @memberof AppCustomViewBase
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
     * @memberof AppCustomViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderToolBar(),
            this.renderTopMessage(),
            this.renderBodyMessage(),
            ...this.renderViewControls(),
            this.renderBottomMessage()
        ]);
    }


}