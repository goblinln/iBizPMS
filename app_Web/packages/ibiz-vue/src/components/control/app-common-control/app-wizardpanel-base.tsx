import { Prop, Watch, Emit } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { WizardPanelControlBase } from '../../../widgets';
import { IPSDEEditForm, IPSDEWizard, IPSDEWizardEditForm, IPSDEWizardStep } from '@ibiz/dynamic-model-api';

export class AppWizardPanelBase extends WizardPanelControlBase {
    
    /**
     * 部件动态参数
     *
     * @memberof AppWizardPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppWizardPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppWizardPanelBase
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
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppWizardPanelBase
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
     * @memberof AppWizardPanelBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppWizardPanelBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制步骤标题栏
     *
     * @memberof AppWizardPanelBase
     */
    public renderStepsTitle() {
        const wizardSteps: Array<IPSDEWizardStep> = (this.controlInstance.getPSDEWizard() as IPSDEWizard).getPSDEWizardSteps() || [];
        if (this.controlInstance.showStepBar && wizardSteps.length > 0) {
            return (
                <el-steps class="wizard-steps" active={this.wizardForms.indexOf(this.activeForm)} finish-status="success">
                    {wizardSteps.map((step: IPSDEWizardStep) => {
                        return <el-step title={step.title}></el-step>
                    })}
                </el-steps>
            );
        }
    }

    /**
     * 绘制步骤表单
     *
     * @memberof AppWizardPanelBase
     */
    public renderStepForm(form: IPSDEWizardEditForm) {
        if (form?.controlType != "FORM" || this.activeForm != form.name || form.formFuncMode != "WIZARDFORM") {
            return;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(form);
        Object.assign(targetCtrlParam.staticProps,{viewState: this.wizardState});
        return this.$createElement(targetCtrlName, { key: Util.createUUID(), props: targetCtrlParam, ref: form.name, on: targetCtrlEvent });
    }

    /**
     * 绘制向导面板footer
     *
     * @memberof AppWizardPanelBase
     */
    public renderStepsFooter() {
        return (
            <footer class="app-wizard-footer">
                {!this.isHidden('PREV') ? <i-button on-click={this.onClickPrev.bind(this)} type="primary">上一步</i-button> : null}
                {!this.isHidden('NEXT') ? <i-button on-click={this.onClickNext.bind(this)} type="primary">下一步</i-button> : null}
                {!this.isHidden('FINISH') ? <i-button on-click={this.onClickFinish.bind(this)} type="primary">完成</i-button> : null}
            </footer>
        );
    }

    /**
     * 绘制向导面板
     *
     * @memberof AppWizardPanelBase
     */
    public render() {
        if(!this.controlIsLoaded || !this.activeForm){
            return null;
        }
        const editForms: Array<IPSDEEditForm> = this.controlInstance.getPSDEEditForms() || [];
        const controlClassNames = this.renderOptions.controlClassNames;
        return (
            <layout class={{ 'app-wizard': true, ...controlClassNames }}>
                {this.renderStepsTitle()}
                <i-content class="app-wizard-content">
                    {editForms.length > 0 ? 
                    editForms.map((editForm: IPSDEEditForm) => {
                        return this.renderStepForm(editForm as IPSDEWizardEditForm);
                    }) : null}
                </i-content>
                {this.renderStepsFooter()}
            </layout>
        );
    }
}