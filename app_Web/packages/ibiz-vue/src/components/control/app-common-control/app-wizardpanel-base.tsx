import { Prop, Watch, Emit } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { WizardPanelControlBase } from '../../../widgets';

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
        const { showStepBar, getPSDEWizard } = this.controlInstance;
        if (showStepBar && getPSDEWizard?.getPSDEWizardSteps?.length > 0) {
            return (
                <el-steps class="wizard-steps" active={this.wizardForms.indexOf(this.activeForm)} finish-status="success">
                    {getPSDEWizard.getPSDEWizardSteps.map((step: any) => {
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
    public renderStepForm(form: any) {
        if (form?.controlType != "FORM" || this.activeForm != form.name || form.formFuncMode != "WIZARDFORM") {
            return;
        }
        const editForm = form;
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(editForm);
        Object.assign(targetCtrlParam.staticProps,{viewState: this.wizardState});
        return this.$createElement(targetCtrlName, {key:Util.createUUID(),props: targetCtrlParam, ref: editForm.name, on: targetCtrlEvent });
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
        const { getPSDEEditForms } = this.controlInstance;
        const controlClassNames = this.renderOptions.controlClassNames;
        return (
            <layout class={{ 'app-wizard': true, ...controlClassNames }}>
                {this.renderStepsTitle()}
                <i-content class="app-wizard-content">
                    {getPSDEEditForms?.length > 0 ? 
                    getPSDEEditForms.map((editForm: any) => {
                        return this.renderStepForm(editForm);
                    }) : null}
                </i-content>
                {this.renderStepsFooter()}
            </layout>
        );
    }
}