import { PickupGridViewBase } from '../../../view';
import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用实体选择表格视图基类
 *
 * @export
 * @class AppPickupGridViewBase
 * @extends {PickupGridViewBase}
 */
export class AppPickupGridViewBase extends PickupGridViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppPickupGridViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppPickupGridViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPickupGridViewBase
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
     * @memberof AppPickupGridViewBase
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
     * @memberof AppPickupGridViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 表格选择视图渲染
     * 
     * @memberof AppPickupGridViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance?.viewType}-${this.viewInstance?.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderToolBar(),
            this.renderQuickGroup(),
            this.renderQuickSearch(),
            !(this.viewInstance?.viewStyle == "DEFAULT" && this.viewInstance?.enableQuickSearch) ? this.renderSearchForm() : null,
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }
}