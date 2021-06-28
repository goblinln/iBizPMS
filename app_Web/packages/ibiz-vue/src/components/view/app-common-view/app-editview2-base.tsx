import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service/common-service/app-layout-service';
import { CreateElement } from 'vue';
import { EditView2Base } from '../../../view/editview2-base';

/**
 * 应用实体编辑视图基类
 *
 * @export
 * @class AppEditView2Base
 * @extends {EditView2Base}
 */
export class AppEditView2Base extends EditView2Base {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppEditView2Base
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppEditView2Base
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppEditView2Base
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
     * @memberof AppEditView2Base
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
     * @memberof AppEditView2Base
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 渲染表单
     *
     * @memberof AppEditView2Base
     */
    public renderForm() {
        if (!this.editFormInstance) {
            return;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, { slot: 'mainform', props: targetCtrlParam, ref: this.editFormInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof EditViewBase
     */
    public renderMainContent() {
        if (!this.drbarInstance) {
            return null;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.drbarInstance);
        return this.$createElement(targetCtrlName, { 
            slot: 'default',
            props: targetCtrlParam, 
            ref: this.drbarInstance?.name, 
            on: targetCtrlEvent, 
        },[
            this.renderForm(),
        ]);
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppEditView2Base
     */
    render(h:CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent:any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
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
