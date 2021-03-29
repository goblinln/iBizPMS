import { IBizWizardPanelModel, ViewState } from 'ibiz-core';
import { Subject } from 'rxjs';
import { AppCenterService } from '../app-service';
import { AppWizardPanelService } from '../ctrl-service';
import { MDControlBase } from './md-control-base';

export class WizardPanelControlBase extends MDControlBase {

    /**
     * 向导面板部件实例
     * 
     * @memberof WizardPanelControlBase
     */
    public controlInstance!: IBizWizardPanelModel;

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
     * 状态属性
     *
     * @type {string}
     * @memberof WizardPanelControlBase
     */
    public stateField: string = '';

    /**
     * 步骤标识集合
     *
     * @type {*}
     * @memberof WizardPanelControlBase
     */
    public stepTags: any = {};

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
        this.stateField = this.controlInstance.getStatePSAppDEField?.codeName?.toLowerCase();
        this.initAction = this.controlInstance.initAction?.id;
        this.finishAction = this.controlInstance.finishAction?.id;
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppWizardPanelService(this.controlInstance);
            await this.service.loaded();
        }
        this.initActiveForm();
    }

    /**
     * 部件初始化
     * 
     * @memberof WizardPanelControlBase
     */
    public ctrlInit() {
        this.regFormActions();
        if (this.activeForm && !this.stateField) {
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
        const { getPSDEWizard: wizard, name } = this.controlInstance;
        if (wizard && wizard.getPSDEWizardForms?.length > 0 && wizard.getFirstPSDEWizardForm?.id) {
            const firstForm = wizard.getPSDEWizardForms.find((form: any) => { return form.name === wizard.getFirstPSDEWizardForm.id && form.firstForm; })
            if (firstForm) {
                this.activeForm = `${name}_form_${firstForm.formTag?.toLowerCase()}`;
            }
        }
    }

    /**
     * 注册表单步骤行为
     *
     * @memberof WizardPanelControlBase
     */
    public regFormActions() {
        const { name, getPSDEWizard: wizard } = this.controlInstance;
        if (wizard && wizard.getPSDEWizardForms?.length > 0) {
            wizard.getPSDEWizardForms.forEach((stepForm: any) => {
                const formName = `${name}_form_${stepForm.formTag?.toLowerCase()}`;
                const editForm = this.controlInstance.getControl(formName);
                const action = {
                    loadAction: editForm?.loadAction ? editForm.loadAction : 'Get',
                    preAction: editForm?.preAction ? editForm.preAction : 'Get',
                    saveAction: editForm?.saveAction ? editForm.saveAction : "Update",
                    actions: stepForm.stepActions,
                }
                this.regFormAction(formName, action, this.getStepTag(wizard.getPSDEWizardSteps, stepForm?.getPSDEWizardStep?.id));
            })
        }
    }

    /**
     * 获取步骤标识
     *
     * @memberof WizardPanelControlBase
     */
    public getStepTag(wizardSteps: Array<any>, tag: string) {
        if (wizardSteps && (wizardSteps.length > 0) && tag) {
            let curStep: any = wizardSteps.find((step: any) => {
                return step.title === tag;
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
        const { getPSDEWizard: wizard, appDataEntity: appde } = this.controlInstance;
        const arg: any = { ...opt };
        Object.assign(arg, { viewparams: this.viewparams });
        const post: Promise<any> = this.service.init(this.initAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status === 200) {
                this.formParam = response.data;
                if (response.data[appde?.codeName?.toLowerCase()]) {
                    Object.assign(this.context, { [appde?.codeName?.toLowerCase()]: response.data[appde?.codeName?.toLowerCase()] });
                }
                this.formLoad(this.formParam);
            }
        }).catch((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.info });
        })
    }

    /**
     * 表单加载
     *
     * @memberof WizardPanelControlBase
     */
    public formLoad(data: any) {
        if (this.stateField) this.computedActiveForm(data);
        if (this.activeForm) {
            this.wizardState.next({ tag: this.activeForm, action: 'panelaction', data: { action: this.stepActions[this.activeForm].loadAction, emitAction: 'load', data: this.formParam } });
        }
    }

    /**
     * 根据状态获取当前激活表单
     *
     * @memberof WizardPanelControlBase
     */
    public computedActiveForm(data: any) {
        if (data && data[this.stateField]) {
            if (Object.keys(this.stepTags).length > 0) {
                Object.keys(this.stepTags).forEach((name: string) => {
                    if (this.stepTags[name] === data[this.stateField]) {
                        this.activeForm = name;
                        return;
                    }
                })
            }
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
        const post: Promise<any> = this.service.finish(this.finishAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status === 200) {
                const data = response.data;
                this.$Notice.success({ title: this.$t('app.commonWords.success') as string, desc: this.$t('app.commonWords.startsuccess') as string });
                this.ctrlEvent({
                    controlname: this.controlInstance.name,
                    action: 'finish',
                    data: data,
                });
                AppCenterService.notifyMessage({ name: this.controlInstance.appDataEntity?.codeName, action: 'appRefresh', data: data });
            }
        }).catch((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.info });
        });
    }

    /**
     * 获取下一步向导表单
     *
     * @memberof WizardPanelControlBase
     */
    public getNextForm() {
        let index = this.wizardForms.indexOf(this.activeForm);
        if (index >= 0) {
            if (this.wizardForms[index + 1]) {
                return this.wizardForms[index + 1];
            }
        }
        return undefined;
    }

    /**
     * 上一步
     *
     * @memberof WizardPanelControlBase
     */
    public onClickPrev() {
        if (!this.stateField) {
            const length = this.historyForms.length;
            if (length > 1) {
                this.curState = 'PREV';
                this.activeForm = this.historyForms[length - 1];
                setTimeout(() => {
                    this.formLoad(this.formParam);
                }, 1);
                this.historyForms.splice(length - 1, 1);
            }
        } else {
            if (this.activeForm) {
                if (this.$refs && this.$refs[this.activeForm] && (this.$refs[this.activeForm] as any).ctrl) {
                    let form: any = (this.$refs[this.activeForm] as any).ctrl;
                    this.curState = 'PREV';
                    if (!this.stepActions[this.activeForm].preAction) {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: this.$t('app.wizardPanel.preactionmessage') as string });
                        return;
                    }
                    this.viewState.next({ tag: this.activeForm, action: 'panelaction', data: { action: this.stepActions[this.activeForm].preAction, emitAction: 'save', data: this.formParam } });
                }
            }
        }
    }

    /**
     * 下一步
     *
     * @memberof WizardPanelControlBase
     */
    public onClickNext() {
        if (this.activeForm) {
            if (this.$refs && (this.$refs[this.activeForm] as any)?.ctrl) {
                let form: any = (this.$refs[this.activeForm] as any).ctrl;
                if (form.formValidateStatus()) {
                    this.curState = 'NEXT';
                    this.wizardState.next({ tag: this.activeForm, action: 'panelaction', data: { action: this.stepActions[this.activeForm].saveAction, emitAction: 'save', data: this.formParam } });
                } else {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.rulesException') as string) });
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
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.rulesException') as string) });
                }
            }
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
            if (!this.stateField) {
                if (this.getNextForm()) {
                    this.activeForm = this.getNextForm();
                    setTimeout(() => {
                        this.formLoad(this.formParam);
                    }, 1);
                } else {
                    this.doFinish();
                }
            } else {
                setTimeout(() => {
                    this.formLoad(this.formParam);
                }, 1);
            }
        } else if (Object.is(this.curState, 'PREV')) {
            if (this.stateField) {
                setTimeout(() => {
                    this.formLoad(this.formParam);
                }, 1);
            }
        } else if (Object.is(this.curState, 'FINISH')) {
            this.doFinish();
        }
    }

}