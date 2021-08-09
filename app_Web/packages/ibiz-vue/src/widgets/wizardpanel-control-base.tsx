import { IPSAppDEField, IPSControlAction, IPSDEEditForm, IPSDEWizard, IPSDEWizardEditForm, IPSDEWizardForm, IPSDEWizardPanel, IPSDEWizardStep } from '@ibiz/dynamic-model-api';
import { ViewState, WizardPanelControlInterface } from 'ibiz-core';
import { Subject } from 'rxjs';
import { AppCenterService } from '../app-service';
import { AppWizardPanelService } from '../ctrl-service';
import { MainControlBase } from './main-control-base';

export class WizardPanelControlBase extends MainControlBase implements WizardPanelControlInterface {

    /**
     * 向导面板部件实例
     * 
     * @memberof WizardPanelControlBase
     */
    public controlInstance!: IPSDEWizardPanel;

    /**
     * 部件行为--init
     *
     * @type {string}
     * @memberof WizardPanelControlBase
     */
    public initAction!: string;

    /**
     * 部件行为--finish
     *
     * @type {string}
     * @memberof WizardPanelControlBase
     */
    public finishAction!: string;

    /**
     * 向导表单参数
     *
     * @type {*}
     * @memberof WizardPanelControlBase
     */
    public formParam: any = {};

    /**
     * 执行过的表单
     *
     * @public
     * @type {Array<string>}
     * @memberof WizardPanelControlBase
     */
    public historyForms: Array<string> = [];

    /**
     * 步骤行为集合
     *
     * @type {*}
     * @memberof WizardPanelControlBase
     */
    public stepActions: any = {};

    /**
     * 向导表单集合
     *
     * @type {Array<any>}
     * @memberof WizardPanelControlBase
     */
    public wizardForms: Array<any> = [];

    /**
     * 当前状态
     *
     * @memberof WizardPanelControlBase
     */
    public curState = '';

    /**
     * 当前激活表单
     *
     * @type {string}
     * @memberof WizardPanelControlBase
     */
    public activeForm: string = '';

    /**
     * 步骤标识集合
     *
     * @type {*}
     * @memberof WizardPanelControlBase
     */
    public stepTags: any = {};

    /**
     * 步骤集合
     *
     * @type {*}
     * @memberof WizardPanelControlBase
     */
     public steps: any[] = [];

    /**
     * 视图状态订阅对象
     *
     * @public
     * @type {Subject<{action: string, data: any}>}
     * @memberof WizardPanelControlBase
     */
    public wizardState: Subject<ViewState> = new Subject();

    /**
     * 部件模型数据初始化实例
     *
     * @memberof WizardPanelControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit(args);
        await this.ctrlModelFill();
        this.initAction = (this.controlInstance.getInitPSControlAction() as IPSControlAction)?.getPSAppDEMethod()?.codeName || 'Get';
        this.finishAction = (this.controlInstance.getFinishPSControlAction() as IPSControlAction)?.getPSAppDEMethod()?.codeName || 'Update';
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppWizardPanelService(this.controlInstance, this.context);
            await this.service.loaded();
        }
        this.initActiveForm();
    }

    /**
     * 部件模型填充
     *
     * @return {*} 
     * @memberof WizardPanelControlBase
     */
    public async ctrlModelFill() {
        const wizard: IPSDEWizard | null = this.controlInstance.getPSDEWizard();
        if (!wizard) {
            return;
        }
        await this.controlInstance.fill();
        const wizardForms: Array<IPSDEWizardForm> = wizard.getPSDEWizardForms() || [];
        for (const form of wizardForms) {
            await form.fill();
            await form.getPSDEWizardStep()?.fill();
        }
    }

    /**
     * 部件初始化
     * 
     * @memberof WizardPanelControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        this.regFormActions();
        if (this.activeForm) {
            this.historyForms.push(this.activeForm);
        }
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (Object.is(tag, this.name)) {
                    if (Object.is('load', action)) {
                        this.doInit(data);
                    }
                }
            });
        }

    }

    /**
     * 初始化当前激活表单
     * 
     * @memberof WizardPanelControlBase
     */
    public initActiveForm() {
        const wizard: IPSDEWizard | null = this.controlInstance.getPSDEWizard();
        const wizardForms: Array<IPSDEWizardForm> = wizard?.getPSDEWizardForms() || [];
        if (wizard && wizardForms.length > 0) {
            const firstForm: IPSDEWizardForm = wizardForms.find((form: IPSDEWizardForm) => {
                return form.firstForm;
            }) as IPSDEWizardForm;
            this.activeForm = `${this.controlInstance.name}_form_${firstForm.formTag?.toLowerCase()}`;
        }
    }

    /**
     * 注册表单步骤行为
     *
     * @memberof WizardPanelControlBase
     */
    public regFormActions() {
        const wizard: IPSDEWizard | null = this.controlInstance.getPSDEWizard();
        const wizardForms: Array<IPSDEWizardForm> = wizard?.getPSDEWizardForms() || [];
        const wizardSteps: Array<IPSDEWizardStep> = wizard?.getPSDEWizardSteps() || [];
        if (wizard && wizardForms.length > 0) {
            wizardForms.forEach((stepForm: IPSDEWizardForm) => {
                const formName = `${this.controlInstance.name}_form_${stepForm.formTag?.toLowerCase()}`;
                const editForm: IPSDEWizardEditForm = (this.controlInstance.getPSDEEditForms() || []).find((form: IPSDEEditForm) => {
                    return form.name === formName;
                }) as IPSDEWizardEditForm;
                const action = {
                    loadAction: editForm?.getGetPSControlAction()?.actionName || 'Get',
                    preAction: editForm?.getGoBackPSControlAction()?.actionName,
                    saveAction: editForm?.getUpdatePSControlAction()?.actionName || "Update",
                    actions: stepForm.getStepActions() || [],
                }
                this.regFormAction(formName, action, this.getStepTag(wizardSteps, stepForm?.getPSDEWizardStep()?.stepTag as string));
            })
        }
        if (wizardSteps.length > 0) {
            wizardSteps.forEach((steps: IPSDEWizardStep) => {
                this.steps.push(steps.stepTag);
            })
        }
    }

    /**
     * 上一步
     *
     * @memberof WizardPanelControlBase
     */
    public onClickPrev() {
        const length = this.historyForms.length;
        if (length > 1) {
            this.curState = 'PREV';
            if (this.stepActions[this.activeForm].preAction) {
                this.wizardState.next({ tag: this.activeForm, action: 'panelaction', data: { action: this.stepActions[this.activeForm].preAction, emitAction: 'save', data: this.formParam } });
            } else {
                this.activeForm = this.historyForms[length - 1];
                setTimeout(() => {
                    this.formLoad(this.formParam);
                }, 1);
                this.historyForms.splice(length - 1, 1);
            }
        }
    }

    /**
     * 下一步
     *
     * @memberof WizardPanelControlBase
     */
    public onClickNext() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        if (this.activeForm) {
            if (this.$refs && (this.$refs[this.activeForm] as any)?.ctrl) {
                let form: any = (this.$refs[this.activeForm] as any).ctrl;
                if (form.formValidateStatus()) {
                    this.curState = 'NEXT';
                    this.wizardState.next({ tag: this.activeForm, action: 'panelaction', data: { action: this.stepActions[this.activeForm].saveAction, emitAction: 'save', data: this.formParam } });
                } else {
                    this.$throw((this.$t('app.commonwords.rulesexception') as string), 'onClickNext');
                }
            }
        }
    }

    /**
     * 完成
     *
     * @memberof WizardPanelControlBase
     */
    public onClickFinish() {
        if (this.activeForm) {
            if (this.$refs && this.$refs[this.activeForm] && (this.$refs[this.activeForm] as any).ctrl) {
                let form: any = (this.$refs[this.activeForm] as any).ctrl;
                if (form.formValidateStatus()) {
                    this.curState = 'FINISH';
                    this.wizardState.next({ tag: this.activeForm, action: 'panelaction', data: { action: this.stepActions[this.activeForm].saveAction, emitAction: 'save', data: this.formParam } });
                } else {
                    this.$throw((this.$t('app.commonwords.rulesexception') as string), 'onClickFinish');
                }
            }
        }
    }

    /**
    * 向导表单加载完成
    *
    * @param {*} args
    * @param {string} name
    * @memberof WizardPanelControlBase
    */
    public wizardpanelFormload(args: any, name: string) {
        if (args) {
            Object.assign(this.formParam, args);
        }
    }

    /**
     * 向导表单保存完成
     *
     * @param {*} args 
     * @param {string} name
     * @memberof WizardPanelControlBase
     */
    public wizardpanelFormsave(args: any, name: string) {
        Object.assign(this.formParam, args);
        if (Object.is(this.curState, 'NEXT')) {
            this.historyForms.push(name);
            if (this.getNextForm()) {
                this.activeForm = this.getNextForm();
                setTimeout(() => {
                    this.formLoad(this.formParam);
                }, 1);
            } else {
                this.doFinish();
            }
        } else if (Object.is(this.curState, 'PREV')) {
            const length = this.historyForms.length;
            if (length > 1) {
                this.activeForm = this.historyForms[length - 1];
                setTimeout(() => {
                    this.formLoad(this.formParam);
                }, 1);
                this.historyForms.splice(length - 1, 1);
            }
        } else if (Object.is(this.curState, 'FINISH')) {
            this.doFinish();
        }
    }

    /**
     * 获取步骤标识
     *
     * @memberof WizardPanelControlBase
     */
    public getStepTag(wizardSteps: Array<any>, tag: string) {
        if (wizardSteps && (wizardSteps.length > 0) && tag) {
            const curStep: any = wizardSteps.find((step: any) => {
                return step.stepTag === tag;
            })
            return curStep.stepTag || tag;
        } else {
            return tag;
        }
    }

    /**
     * 注册表单步骤行为
     *
     * @memberof WizardPanelControlBase
     */
    public regFormAction(name: string, actionParams: any, stepTag: any) {
        this.stepActions[name] = actionParams;
        this.stepTags[name] = stepTag;
        this.wizardForms.push(name);
    }

    /**
     * 初始化行为
     *
     * @memberof WizardPanelControlBase
     */
    public doInit(opt: any = {}) {
        const arg: any = { ...opt };
        Object.assign(arg, { viewparams: this.viewparams });
        let tempContext: any = JSON.parse(JSON.stringify(this.context));
        this.onControlRequset('doInit', tempContext, arg);
        const post: Promise<any> = this.service.init(this.initAction, tempContext, arg, this.showBusyIndicator);
        post.then((response: any) => {
            this.onControlResponse('doInit', response);
            if (response && response.status === 200) {
                this.formParam = response.data;
                if (response.data[this.appDeCodeName.toLowerCase()]) {
                    Object.assign(this.context, { [this.appDeCodeName.toLowerCase()]: response.data[this.appDeCodeName.toLowerCase()] });
                }
                this.formLoad(this.formParam);
            }
        }).catch((response: any) => {
            this.onControlResponse('doInit', response);
            this.$throw(response, 'doInit');
        })
    }

    /**
     * 表单加载
     *
     * @memberof WizardPanelControlBase
     */
    public formLoad(data: any) {
        if (this.activeForm) {
            this.wizardState.next({ tag: this.activeForm, action: 'panelaction', data: { action: this.stepActions[this.activeForm].loadAction, emitAction: 'load', data: this.formParam } });
        }
    }

    /**
     * 完成行为
     *
     * @memberof WizardPanelControlBase
     */
    public doFinish() {
        let arg: any = {};
        Object.assign(arg, this.formParam);
        Object.assign(arg, { viewparams: this.viewparams });
        let tempContext: any = JSON.parse(JSON.stringify(this.context));
        this.onControlRequset('doFinish', tempContext, arg);
        const post: Promise<any> = this.service.finish(this.finishAction, tempContext, arg, this.showBusyIndicator);
        post.then((response: any) => {
            this.onControlResponse('doFinish', response);
            if (response && response.status === 200) {
                const data = response.data;
                this.ctrlEvent({
                    controlname: this.controlInstance.name,
                    action: 'finish',
                    data: data,
                });
                AppCenterService.notifyMessage({ name: this.appDeCodeName, action: 'appRefresh', data: data });
            }
        }).catch((response: any) => {
            this.onControlResponse('doFinish', response);
            this.$throw(response, 'doFinish');
        });
    }

    /**
     * 获取下一步向导表单
     *
     * @memberof WizardPanelControlBase
     */
    public getNextForm() {
        if (this.formParam && this.formParam['srfnextform']) {
            return `${this.controlInstance.name}_form_${this.formParam['srfnextform'].toLowerCase()}`;
        } else {
            let index = this.wizardForms.indexOf(this.activeForm);
            if (index >= 0) {
                if (this.wizardForms[index + 1]) {
                    return this.wizardForms[index + 1];
                }
            }
            return undefined;
        }
    }

    /**
     * 是否隐藏
     *
     * @param {string} type
     * @memberof WizardPanelControlBase
     */
    public isHidden(type: string) {
        const actions: Array<string> = this.stepActions[this.activeForm].actions;
        if (actions && actions.indexOf(type) < 0) {
            return true;
        }
        return false;
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof WizardPanelControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (Object.is(action, "save")) {
            this.wizardpanelFormsave(data, controlname);
        } else if (Object.is(action, "load")) {
            this.wizardpanelFormload(data, controlname);
        }
    }
}