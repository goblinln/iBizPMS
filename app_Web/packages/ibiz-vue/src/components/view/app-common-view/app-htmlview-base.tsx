import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { HtmlViewBase } from '../../../view/HtmlViewBase';
import { AppLayoutService } from '../../../app-service/common-service/app-layout-service';

/**
 * 实体html视图基类
 *
 * @export
 * @class AppHtmlViewBase
 * @extends {HtmlViewBase}
 */
export class AppHtmlViewBase extends HtmlViewBase {

    /**
     * 视图动态参数
     *
     * @type {any}
     * @memberof AppHtmlViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {any}
     * @memberof AppHtmlViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppHtmlViewBase
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
     * @memberof AppHtmlViewBase
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
     * @memberof AppHtmlViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }
    
    /**
     * 实体html视图渲染
     * 
     * @memberof AppHtmlViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }
}

