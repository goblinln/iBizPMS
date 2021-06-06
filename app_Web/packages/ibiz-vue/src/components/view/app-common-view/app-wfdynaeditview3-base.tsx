import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { WFDynaEditView3Base } from '../../../view';
import { AppLayoutService } from '../../../app-service/common-service/app-layout-service';
import { CreateElement } from 'vue';

/**
 * 应用实体工作流动态编辑视图3基类
 *
 * @export
 * @class AppWFDynaEditView3Base
 * @extends {WFDynaEditView3Base}
 */
export class AppWFDynaEditView3Base extends WFDynaEditView3Base {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppWFDynaEditView3Base
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppWFDynaEditView3Base
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppWFDynaEditView3Base
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
     * @memberof AppWFDynaEditView3Base
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
     * @memberof AppWFDynaEditView3Base
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppWFDynaEditView3Base
     */
    render(h:CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent:any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderCaptionInfo(),
            this.renderToolBar(),
            this.renderMainContent()
        ]);
    }
}
