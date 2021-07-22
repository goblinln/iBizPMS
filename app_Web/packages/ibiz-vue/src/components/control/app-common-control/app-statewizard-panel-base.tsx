import { Prop, Watch, Emit } from 'vue-property-decorator';
import { throttle, Util } from 'ibiz-core';
import { StateWizardPanelControlBase } from '../../../widgets';
import { IPSDEEditForm, IPSDEWizard, IPSDEWizardEditForm, IPSDEWizardStep } from '@ibiz/dynamic-model-api';

export class AppStateWizardPanelBase extends StateWizardPanelControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppStateWizardPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppStateWizardPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppStateWizardPanelBase
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
     * @memberof AppStateWizardPanelBase
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
     * @memberof AppStateWizardPanelBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppStateWizardPanelBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}
    
    /**
     * 绘制步骤标题
     *
     * @memberof AppWizardPanelBase
     */
    public renderTitle(step: any, stepForm: string) {
        return this.$createElement('div', {
            slot: 'title',
            directives: [{
                name: 'popover',
                arg: `${stepForm}_popover`
            }],
            on: {
                click: () => throttle(this.stepTitleClick,[stepForm],this)
            }
        }, [
            <span>{this.activeForm != stepForm && !this.historyForms.includes(stepForm) ? <i class="el-icon-lock"></i> : null}{ step.title }</span>
        ])
    }

    /**
     * 绘制向导面板步骤
     *
     * @memberof AppWizardPanelBase
     */
    public renderViewSteps() {
        const wizardSteps: Array<IPSDEWizardStep> = (this.controlInstance.getPSDEWizard() as IPSDEWizard).getPSDEWizardSteps() || [];
        if (wizardSteps.length > 0) {
            return (
                <div class="view-steps">
                    <div class="background-box"></div>
                    <div class="steps_icon" on-click={() => throttle(this.handleClick,['PRE'],this)}><i class="el-icon-arrow-left"></i></div>
                    <el-steps class="wizard-steps" active={this.wizardForms.indexOf(this.activeForm) + 1} finish-status="success" align-center>
                        {wizardSteps.map((step: IPSDEWizardStep) => {
                            const stepForm = this.getStepForm(step);
                            return (
                                <el-step class={{ 'app-active-step': this.activeForm === stepForm ? true : false }}>
                                    { this.renderTitle(step, stepForm) }
                                </el-step>
                            );
                        })}
                    </el-steps>
                    <div class="steps_icon" on-click={() => throttle(this.handleClick,['NEXT'],this)}><i class="el-icon-arrow-right"></i></div>
                </div>
            );
        }
    }

    /**
     * 绘制表单
     *
     * @memberof AppWizardPanelBase
     */
    public renderEditForm(form: IPSDEWizardEditForm) {
        if (form?.controlType != "FORM" || form.formFuncMode != "WIZARDFORM") {
            return;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(form);
        Object.assign(targetCtrlParam.staticProps,{viewState: this.wizardState});
        return this.$createElement(targetCtrlName, { key: form.codeName, props: targetCtrlParam, ref: form.name, on: targetCtrlEvent });
    }

    /**
     * 绘制所有表单
     *
     * @memberof AppWizardPanelBase
     */
    public renderEditForms() {
        const editForms: Array<IPSDEEditForm> = this.controlInstance.getPSDEEditForms() || [];
        let content: any;
        if (editForms.length > 0) {
            content = editForms.map((form: IPSDEEditForm, index: number) => {
                return (
                    <el-popover
                        value={this.stepVisiable[form.name]}
                        ref={`${form.name}_popover`}
                        popper-class="app-state-wizard-popover-container"
                        placement={`bottom-${index === editForms.length - 1 ? 'end' : 'start' }`}
                        trigger="click">
                            <div class="app-state-wizard-container">
                                <div class="app-state-wizard-header">
                                    <div class="app-state-wizard-header-extra">
                                        <i class='ivu-icon ivu-icon-md-open' size="18" on-click={() => throttle(this.handleOpen,[form.name],this)}></i>
                                        <i class='ivu-icon ivu-icon-md-close' size="18" on-click={() => throttle(this.handleClose,[form.name],this)}></i>
                                    </div>
                                </div>
                                <div class="popover-title">{ form.logicName }</div>
                                <div class="app-state-wizard-content">
                                    { this.renderEditForm(form as IPSDEWizardEditForm) }
                                </div>
                                { this.renderStepsFooter(form.name) }
                            </div>
                    </el-popover>
                );
            });
        }
        return content;
    }

    /**
     * 绘制向导面板footer
     *
     * @memberof AppWizardPanelBase
     */
    public renderStepsFooter(name: string) {
        return (
            <footer class="app-state-wizard-footer">
                {this.isVisiable(name, 'PREV') ? <i-button on-click={(...params: any[]) => throttle(this.onClickPrev,params,this)} type="primary"><i class="ivu-icon ivu-icon-ios-arrow-back" /></i-button> : null}
                {this.isVisiable(name, 'NEXT') ? <i-button on-click={(...params: any[]) => throttle(this.onClickNext,params,this)} type="primary" long>{this.$t('app.wizardpanel.next')}</i-button> : null}
                {this.isVisiable(name, 'FINISH') ? <i-button on-click={(...params: any[]) => throttle(this.onClickFinish,params,this)} type="primary" long>{this.$t('app.wizardpanel.complete')}</i-button> : null}
            </footer>
        );
    }

    /**
     * 绘制状态向导面板
     *
     * @memberof AppWizardPanelBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        const wizard: IPSDEWizard | null = this.controlInstance.getPSDEWizard();
        const controlClassNames = this.renderOptions.controlClassNames;
        //TODO  表单抽屉
        return (
            <layout class={{ 'app-state-wizard': true, ...controlClassNames }}>
                { wizard && (wizard.getPSDEWizardSteps() || []).length > 0 ?
                    [
                        this.renderViewSteps(),
                        this.renderEditForms()
                    ]
                : null }
            </layout>
        );
    }
}