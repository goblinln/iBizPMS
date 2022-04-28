import { CreateElement } from 'vue';
import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service/common-service/app-layout-service';
import { DeIndexViewBase } from '../../../view';

/**
 * 应用实体首页视图
 *
 * @export
 * @class AppDEIndexViewBase
 * @extends {DeIndexViewBase}
 */
export class AppDEIndexViewBase extends DeIndexViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDEIndexViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppDEIndexViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDEIndexViewBase
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
     * @memberof AppDEIndexViewBase
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
     * @memberof AppDEIndexViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * @description 渲染表单部件
     * @return {*} 
     * @memberof AppDEIndexViewBase
     */
    public renderForm() {
        if (!this.formInstance) {
            return;
        }
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.formInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.formInstance?.name, on: targetCtrlEvent });
    }

    /**
     * @description 渲染数据关系栏部件
     * @return {*} 
     * @memberof AppDEIndexViewBase
     */
    public renderDrBar() {
        if (!this.drBarInstance) {
            return;
        }
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.drBarInstance);
        Object.assign(targetCtrlParam.staticProps, {
            showMode: 'INDEXMODE'
        });
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.drBarInstance?.name, on: targetCtrlEvent });
    }

    /**
     * @description 渲染主体内容
     * @return {*} 
     * @memberof AppDEIndexViewBase
     */
    public renderMainContent() {
        return (
            <div slot="default" class="ctrl-container">
                <div class="form-container">
                    {this.renderForm()}
                </div>
                <div class="drbar-container">
                    {this.renderDrBar()}
                </div>
            </div>
        )
    }

    /**
     * @description 渲染实体首页视图
     * @param {CreateElement} h
     * @return {*} 
     * @memberof AppDEIndexViewBase
     */
    render(h:CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent:any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            // this.renderDataPanelInfo(),
            this.renderTopMessage(),
            this.renderCaptionInfo(),
            this.renderBodyMessage(),
            this.renderToolBar(),
            this.renderMainContent(),
            this.renderBottomMessage(),
            ...this.renderViewControls()
        ]);
    }
}
