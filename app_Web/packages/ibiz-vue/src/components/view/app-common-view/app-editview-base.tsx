import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { EditViewBase } from '../../../view/EditViewBase';
import { AppLayoutService } from '../../../app-service/common-service/app-layout-service';
import { CreateElement } from 'vue';

/**
 * 应用实体编辑视图基类
 *
 * @export
 * @class AppEditViewBase
 * @extends {EditViewBase}
 */
export class AppEditViewBase extends EditViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppEditViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppEditViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppEditViewBase
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
     * @memberof AppEditViewBase
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
     * @memberof AppEditViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppEditViewBase
     */
    render(h:CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent:any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderDataPanelInfo(),
            this.renderTopMessage(),
            this.renderCaptionInfo(),
            this.renderBodyMessage(),
            this.renderToolBar(),
            this.renderMainContent(),
            this.renderBottomMessage(),
        ]);
    }
}
