import { IBizWizardPanelModel, ViewState } from 'ibiz-core';
import { Subject } from 'rxjs';
import { AppWizardPanelService } from '..';
import { MDControlBase } from './md-control-base';

export class StateWizardPanelControlBase extends MDControlBase {

    /**
     * 向导面板部件实例
     * 
     * @memberof StateWizardPanelControlBase
     */
    public controlInstance!: IBizWizardPanelModel;

    /**
     * 部件行为--init
     *
     * @type {string}
     * @memberof StateWizardPanelControlBase
     */
    public initAction!: string;

    /**
     * 部件行为--finish
     *
     * @type {string}
     * @memberof StateWizardPanelControlBase
     */
    public finishAction!: string;

    /**
     * 向导表单参数
     *
     * @type {*}
     * @memberof StateWizardPanelControlBase
     */
    public formParam: any = {};

    /**
     * 执行过的表单
     *
     * @public
     * @type {Array<string>}
     * @memberof StateWizardPanelControlBase
     */
    public historyForms: Array<string> = [];

    /**
     * 步骤行为集合
     *
     * @type {*}
     * @memberof StateWizardPanelControlBase
     */
    public stepActions: any = {};


    /**
     * 向导表单集合
     *
     * @type {Array<any>}
     * @memberof StateWizardPanelControlBase
     */
    public wizardForms: Array<any> = [];

    /**
     * 当前状态
     *
     * @memberof StateWizardPanelControlBase
     */
    public curState = '';

    /**
     * 当前激活表单
     *
     * @type {string}
     * @memberof StateWizardPanelControlBase
     */
    public activeForm: string = '';

    /**
     * 首表单
     *
     * @type {string}
     * @memberof StateWizardPanelControlBase
     */
    public firstForm: string = '';

    /**
     * 状态属性
     *
     * @type {string}
     * @memberof StateWizardPanelControlBase
     */
    public stateField: string = '';

    /**
     * 步骤标识集合
     *
     * @type {*}
     * @memberof StateWizardPanelControlBase
     */
    public stepTags: any = {};

    /**
     * 视图状态订阅对象
     *
     * @public
     * @type {Subject<{action: string, data: any}>}
     * @memberof StateWizardPanelControlBase
     */
    public wizardState: Subject<ViewState> = new Subject();

    /**
     * 步骤是否显示集合
     *
     * @type {*}
     * @memberof StateWizardPanelControlBase
     */
    public stepVisiable:any = {};

    /**
     * 当前显示表单
     *
     * @type {string}
     * @memberof StateWizardPanelControlBase
     */
    public curShow:string ="";

    /**
     * 抽屉状态
     *
     * @memberof StateWizardPanelControlBase
     */
    public drawerOpenStatus:any = {
        isOpen:false,
        formName:""
    }

        /**
     * 部件模型数据初始化实例
     *
     * @memberof StateWizardPanelControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit(args);
        let { getStatePSAppDEField, initAction, finishAction } = this.controlInstance;
        this.stateField = getStatePSAppDEField?.codeName?.toLowerCase();
        //TODO行为首字母大写
        this.initAction = initAction?.id ? initAction.id.replace(initAction.id[0], initAction.id[0].toUpperCase()) : null;
        this.finishAction = finishAction?.id ? finishAction.id.replace(finishAction.id[0], finishAction.id[0].toUpperCase()) : null;
        this.service = new AppWizardPanelService(this.controlInstance);
        await this.service.loaded();
        this.initFirstForm();
    }

    public ctrlInit() {
        this.regFormActions();
        this.doInit();
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
     * @memberof StateWizardPanelControlBase
     */
    public initFirstForm() {
        const { getPSDEWizard: wizard, name } = this.controlInstance;
        if (wizard && wizard.getPSDEWizardForms?.length > 0 && wizard.getFirstPSDEWizardForm?.id) {
            const firstForm = wizard.getPSDEWizardForms.find((form: any) => { return form.name === wizard.getFirstPSDEWizardForm.id && form.firstForm; })
            if (firstForm) {
                this.firstForm = `${name}_form_${firstForm.formTag}`;
            }
        }
    }

    /**
     * 获取步骤标识
     *
     * @memberof StateWizardPanelControlBase
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
     * @memberof StateWizardPanelControlBase
     */
    public regFormActions() {
        const { name, getPSDEWizard: wizard } = this.controlInstance;
        if (wizard && wizard.getPSDEWizardForms?.length > 0) {
            wizard.getPSDEWizardForms.forEach((form: any) => {
                this.regFormAction(`${name}_form_${form.formTag.toLowerCase()}`, form.stepActions, this.getStepTag(wizard.getPSDEWizardSteps, form?.getPSDEWizardStep?.id))
            })
        }
    }

    /**
     * 注册表单
     *
     * @memberof StateWizardPanelControlBase
     */
    public regFormAction(name: string, actions: Array<string>,stepTag:any) {
        this.stepActions[name] = actions;
        this.stepTags[name] = stepTag;
        this.stepVisiable[name] = false;
        this.wizardForms.push(name);
    }

    /**
     * 计算激活表单
     *
     * @memberof StateWizardPanelControlBase
     */
    public computedActiveForm(data:any){
        if(data[this.stateField]){
            if(Object.keys(this.stepTags).length >0){
                Object.keys(this.stepTags).forEach((name:string) =>{
                    if(this.stepTags[name] === data[this.stateField]){
                        this.activeForm = name;
                    }
                })
            }
            if(!this.activeForm){
                this.activeForm = this.firstForm;
            }
        }else{
            this.activeForm = this.firstForm;
        }
        if(this.activeForm) {
            let index = this.wizardForms.indexOf(this.activeForm);
            this.wizardForms.forEach((item:any,inx:number) =>{
                if(inx <= index){
                    this.historyForms.push(item);
                }
            })
        }
    }

    /**
     * 初始化
     *
     * @memberof StateWizardPanelControlBase
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
                    Object.assign(this.context, { [appde.codeName.toLowerCase()]: response.data[appde?.codeName?.toLowerCase()] });
                }
                this.computedActiveForm(this.formParam);
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
     * @memberof StateWizardPanelControlBase
     */
    public formLoad(name: string) {
        if(name) {
            this.wizardState.next({ tag: name, action: 'load', data: this.formParam });
        }
    }

    /**
     * 表单加载完成
     *
     * @memberof StateWizardPanelControlBase
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
     * 状态表单加载完成
     *
     * @memberof StateWizardPanelControlBase
     */
    public wizardpanelFormload(args: any, name: string) {
        if(args) {
            Object.assign(this.formParam, args);
        }
    }

     /**
     * 向导表单保存完成
     *
     * @param {*} args
     * @param {string} name
     * @memberof StateWizardPanelControlBase
     */
    public wizardpanelFormsave(args: any, name: string) {
        Object.assign(this.formParam, args);
        if(Object.is(this.curState, 'NEXT')) {
            if(this.historyForms.indexOf(name) === -1){
                this.historyForms.push(name);
            }
            this.setPopVisiable(name,false);
            if (this.getNextForm(name)) {
                this.activeForm = this.getNextForm(name);
                this.setPopVisiable(this.activeForm,true);
                setTimeout(() => {
                    this.formLoad(this.activeForm);
                }, 1);
            } else {
                this.doFinish();
            }
        }else if(Object.is(this.curState, 'FINISH')) {
            this.doFinish();
        }
    }

    /**
     * 步骤标题点击
     *
     * @memberof StateWizardPanelControlBase
     */
    public stepTitleClick(name: string) {
        const that = this;
        let activeIndex:number = that.wizardForms.indexOf(that.activeForm);
        let curIndex:number = that.wizardForms.indexOf(name);
        if(curIndex > activeIndex){
            setTimeout(() =>{
                (that.$refs[name+'_popover'] as any).showPopper = false;
                that.curShow = "";
            },0)
            return;
        }
        that.stepVisiable[name] = !that.stepVisiable[name];
        if(that.stepVisiable[name]){
            that.curShow = name;
            that.formLoad(name);
        }else{
            that.curShow = "";
        }
    }

    /**
     * 左右按钮点击
     *
     * @memberof StateWizardPanelControlBase
     */
    public handleClick(mode: any) {
        if(Object.is(this.curShow,"")){
            return;
        }
        let curIndex:number = this.wizardForms.indexOf(this.curShow);
        if(Object.is(mode,"PRE") && (curIndex !== 0)){
            this.setPopVisiable(this.wizardForms[curIndex],false);
            setTimeout(() => {
                this.setPopVisiable(this.wizardForms[curIndex-1],true);
                this.formLoad(this.wizardForms[curIndex-1]);
            }, 0);
        }
        if(Object.is(mode,"NEXT") && (curIndex < (this.wizardForms.length - 1) && this.historyForms.includes(this.wizardForms[curIndex+1]))){
            this.setPopVisiable(this.wizardForms[curIndex],false);
            setTimeout(() => {
                this.setPopVisiable(this.wizardForms[curIndex+1],true);
                this.formLoad(this.wizardForms[curIndex+1]);
            }, 0);
        }
    }

    /**
     * 打开链接
     *
     * @memberof StateWizardPanelControlBase
     */
    public handleOpen(name:string){
        this.handleClose(name);
        this.drawerOpenStatus.isOpen = true;
        this.drawerOpenStatus.formName = name;
    }

    /**
     * 关闭
     *
     * @memberof StateWizardPanelControlBase
     */
    public handleClose(name:string){
        this.setPopVisiable(name,false);
    }

    /**
     * 获取下一步向导表单
     *
     * @memberof StateWizardPanelControlBase
     */
    public getNextForm(name:string) {
        let index = this.wizardForms.indexOf(name);
        if(index >= 0) {
            if(this.wizardForms[index + 1]) {
                return this.wizardForms[index + 1];
            }
        }
        return undefined;
    }


    /**
     * 上一步
     *
     * @memberof StateWizardPanelControlBase
     */
    public onClickPrev(name:string) {
        console.log("上一步")
        const length = this.historyForms.length;
        if(length > 0) {
            this.curState = 'PREV';
            let curIndex:number = this.wizardForms.indexOf(name);
            this.setPopVisiable(name,false);
            setTimeout(() => {
                this.setPopVisiable(this.historyForms[curIndex - 1],true);
                this.formLoad(this.historyForms[curIndex - 1]);
            }, 1);
        }
        console.log(this.stepVisiable);
    }

    /**
     * 下一步
     *
     * @memberof StateWizardPanelControlBase
     */
    public onClickNext(name:string) {
        if(name) {
            if(this.$refs && this.$refs[name]){
                let form: any = (this.$refs[name] as any).ctrl;
                if(form.formValidateStatus()) {
                    this.curState = 'NEXT';
                    this.wizardState.next({ tag: name, action: 'save', data: this.formParam });
                } else {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.rulesException') as string) });
                }
            }
        }

    }

    /**
     * 完成
     *
     * @memberof StateWizardPanelControlBase
     */
    public onClickFinish(name:string) {
        if(name) {
            if(this.$refs && this.$refs[name]){
                let form: any = (this.$refs[name] as any).ctrl;
                if(form.formValidateStatus()) {
                    this.curState = 'FINISH';
                    this.wizardState.next({ tag: name, action: 'save', data: this.formParam });
                } else {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.rulesException') as string) });
                }
            }
        }
    }

    public getStepForm(step: any) {
        const forms = this.controlInstance.getPSDEEditForms;
        let stepForm: any;
        if (forms?.length > 0) {
            stepForm = forms.find((form: any) => {
                const wizardForm = form.getPSDEWizardForm;
                if (wizardForm && wizardForm.getPSDEWizardStep?.id && wizardForm.getPSDEWizardStep.id == step.name) {
                    return true;
                }
                return false;
            });
        }
        return stepForm ? stepForm.name : "";
    }

    /**
     * 设置popover是否显示
     *
     * @memberof StateWizardPanelControlBase
     */
    public setPopVisiable(name:string,isVisiable:boolean){
        this.stepVisiable[name] = isVisiable;
        const refFrom = (this.$refs[name+'_popover'] as any);
        if (refFrom) {
            refFrom.showPopper = isVisiable;
            this.curShow = isVisiable?name:"";
        } 
    }

    /**
     * 是否显示
     *
     * @memberof StateWizardPanelControlBase
     */
    public isVisiable(name:string,type: string) {
        const actions: Array<string> = this.stepActions[name];
        if(actions && actions.indexOf(type) !== -1 && Object.is(name,this.activeForm)) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 抽屉状态改变
     *
     * @memberof StateWizardPanelControlBase
     */
    public onVisibleChange(value:any){
        if(!value){
            this.drawerOpenStatus.isOpen = false;
        }
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof StateWizardPanelControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (Object.is(action, "save")) {
            this.wizardpanelFormsave(data, controlname);
        } else if (Object.is(action, "load")) {
            this.wizardpanelFormload(data, controlname);
        }
    }
    
}