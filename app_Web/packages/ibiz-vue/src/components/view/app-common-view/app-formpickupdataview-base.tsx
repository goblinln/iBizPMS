import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { FormPickupDataViewBase } from '../../../view/formpickupdataview-base';

/**
 * 表单选择数据视图基类
 *
 * @export
 * @class AppFormPickupDataViewBase
 * @extends {FormPickupDataViewBase}
 */
export class AppFormPickupDataViewBase extends FormPickupDataViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppFormPickupDataViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppFormPickupDataViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppFormPickupDataViewBase
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
     * @memberof AppFormPickupDataViewBase
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
     * @memberof AppFormPickupDataViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 表单选择视图渲染
     * 
     * @memberof AppFormPickupDataViewBase
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
            this.renderToolBar(),
            this.renderQuickGroup(),
            this.renderQuickSearch(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }
}