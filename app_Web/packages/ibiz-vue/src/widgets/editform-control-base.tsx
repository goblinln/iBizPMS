import schema from 'async-validator';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { FormButtonModel, FormDruipartModel, FormGroupPanelModel, FormIFrameModel, FormItemModel, FormPageModel, FormPartModel, FormRawItemModel, FormTabPageModel, FormTabPanelModel, FormUserControlModel, IBizFormDetailModel, IBizFormItemModel, Util, Verify, ViewTool } from 'ibiz-core';
import { FormControlBase } from './form-control-base';
import { AppFormService } from '../ctrl-service';
import { AppCenterService, AppViewLogicService } from '../app-service';

/**
 * 编辑表单部件基类
 *
 * @export
 * @class EditFormControlBase
 * @extends {FormControlBase}
 */
export class EditFormControlBase extends FormControlBase {

    /**
     * 是否自动加载
     *
     * @type {*}
     * @memberof EditFormControlBase
     */
    public isAutoLoad?: any;

    /**
     * 是否默认保存
     *
     * @type {*}
     * @memberof EditFormControlBase
     */
    public isAutoSave?: any;

    /**
     * 部件行为--submit
     *
     * @type {*}
     * @memberof EditFormControlBase
     */
    public WFSubmitAction?: any;

    /**
     * 部件行为--start
     *
     * @type {*}
     * @memberof EditFormControlBase
     */
    public WFStartAction?: any;

    /**
     * 部件行为--update
     *
     * @type {*}
     * @memberof EditFormControlBase
     */
    public updateAction: any;

    /**
     * 部件行为--remove
     *
     * @type {*}
     * @memberof EditFormControlBase
     */
    public removeAction: any;

    /**
     * 部件行为--create
     *
     * @type {*}
     * @memberof EditFormControlBase
     */
    public createAction?: any;

    /**
     * 部件行为--create
     *
     * @type {*}
     * @memberof EditFormControlBase
     */
    public searchAction?: any;

    /**
     * 主键表单项名称
     *
     * @protected
     * @type {string}
     * @memberof EditFormControlBase
     */
    public majorKeyItemName: string = "";

    /**
     * 主信息属性映射表单项名称
     *
     * @type {string}
     * @memberof EditFormControlBase
     */
    public majorMessageItemName: string = "";

    /**
      * 当前执行的行为逻辑
      *
      * @type {string}
      * @memberof EditFormControlBase
      */
    public currentAction: string = "";

    /**
     * 工作流审批意见控件绑定值
     *
     * @memberof EditFormControlBase
     */
    public srfwfmemo: string = "";

    /**
     * 关系界面数量
     *
     * @type {number}
     * @memberof EditFormControlBase
     */
    public drCount: number = 0;

    /**
      * 关系界面计数器
      *
      * @type {number}
      * @memberof EditFormControlBase
      */
    public drcounter: number = 0;

    /**
      * 需要等待关系界面保存时，第一次调用save参数的备份
      *
      * @type {number}
      * @memberof EditFormControlBase
      */
    public drsaveopt: any = {};

    /**
      * 表单保存回调存储对象
      *
      * @type {any}
      * @memberof EditFormControlBase
      */
    public saveState: any;

    /**
     * 表单项校验错误提示信息
     * 
     *  @memberof  EditFormControlBase
     */
    public errorMessages: Array<any> = [];

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof EditFormControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isAutoLoad = this.staticProps.isAutoLoad;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof EditFormControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppFormService(this.controlInstance);
            await this.service.loaded();
        }
        this.isAutoSave = this.controlInstance.enableAutoSave;
        this.loaddraftAction = this.controlInstance.loaddraftAction;
        this.updateAction = this.controlInstance.updateAction;
        this.removeAction = this.controlInstance.removeAction;
        this.loadAction = this.controlInstance.loadAction;
        this.createAction = this.controlInstance.createAction;
        this.WFSubmitAction = this.controlInstance.WFSubmitAction;
        this.WFStartAction = this.controlInstance.WFStartAction;
        // 初始化data
        this.controlInstance?.formItems?.forEach((formItem: IBizFormItemModel) => {
            this.$set(this.data, formItem.name, null);
        });
        // 初始化表单成员运行时模型
        this.initDetailsModel();
        // 初始化静态值规则
        this.initRules();
    }

    /**
     * 编辑表单初始化
     *
     * @memberof EditFormControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        if (this.isAutoLoad) {
            this.autoLoad({ srfkey: this.context[this.controlInstance.appDeCodeName?.toLowerCase()] });
        }
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: { tag: string, action: string, data: any }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('save', action)) {
                    this.save(data, data.showResultInfo);
                }
                if (Object.is('remove', action)) {
                    this.remove(data);
                }
                if (Object.is('saveandexit', action)) {
                    this.saveAndExit(data);
                }
                if (Object.is('saveandnew', action)) {
                    this.saveAndNew(data);
                }
                if (Object.is('removeandexit', action)) {
                    this.removeAndExit(data);
                }
                if (Object.is('refresh', action)) {
                    this.refresh(data);
                }
                if (Object.is('panelaction', action)) {
                    this.panelAction(data.action, data.emitAction, data.data);
                }
            });
        }
        if (this.dataChang) {
            this.dataChang.pipe(debounceTime(300), distinctUntilChanged()).subscribe((data: any) => {
                this.handleDataChange();
            });
        }
    }

    /**
     * 处理dataChang下发的事件
     *
     * @memberof EditFormControlBase
     */
    public handleDataChange() {
        if (this.isAutoSave) {
            this.autoSave();
        }
        const state = !Object.is(JSON.stringify(this.oldData), JSON.stringify(this.data)) ? true : false;
        // this.$store.commit('viewaction/setViewDataChange', { viewtag: this.viewtag, viewdatachange: state });
    }

    /**
     * 表单值变化
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @returns {void}
     * @memberof EditFormControlBase
     */
    public formDataChange({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void {
        if (this.ignorefieldvaluechange) {
            return;
        }
        this.resetFormData({ name: name, newVal: newVal, oldVal: oldVal });
        this.formLogic({ name: name, newVal: newVal, oldVal: oldVal });
        this.dataChang.next(JSON.stringify(this.data));
    }

    /**
     * 加载草稿
     *
     * @param {*} [opt={}]
     * @memberof EditFormControlBase
     */
    public loadDraft(opt: any = {}): void {
        let callBack: any;
        if (!this.loaddraftAction) {
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: `${this.controlInstance.codeName}` + (this.$t('app.formpage.notconfig.loaddraftaction') as string) });
            return;
        }
        this.createDefault();
        if (opt.callBack) {
            callBack = opt.callBack;
            delete opt.callBack;
        }
        const arg: any = { ...opt };
        let viewparamResult: any = Object.assign(arg, this.viewparams);
        let post: Promise<any> = this.service.loadDraft(this.loaddraftAction, JSON.parse(JSON.stringify(this.context)), { viewparams: viewparamResult }, this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                if (response.data) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                }
                return;
            }

            const data = response.data;
            this.resetDraftFormStates();
            this.onFormLoad(data, 'loadDraft');
            if (callBack && (callBack instanceof Function)) callBack();

            // 删除主键表单项的值
            this.controlInstance.formItems.find((item: any) => {
                if (item.appDeField && !item.hidden && item.keyField) {
                    data[item.name] = null;
                }
            })
            this.ctrlEvent({
                controlname: this.controlInstance.name,
                action: 'load',
                data: data,
            });
            this.$nextTick(() => {
                this.formState.next({ type: 'load', data: data });
            });
            setTimeout(() => {
                const form: any = this.$refs.form;
                if (form) {
                    form.fields.forEach((field: any) => {
                        field.validateMessage = "";
                        field.validateState = "";
                        field.validateStatus = false;
                    });
                }
            });
        }).catch((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status && response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                return;
            }
        });
    }

    /**
     * 自动保存
     *
     * @param {*} [opt={}]
     * @memberof EditFormControlBase
     */
    public autoSave(opt: any = {}): void {
        if (!this.formValidateStatus()) {
            return;
        }
        const arg: any = { ...opt };
        const data = this.getData();
        Object.assign(arg, data);
        Object.assign(arg, { srfmajortext: data[this.majorMessageItemName] });
        const action: any = Object.is(data.srfuf, '1') ? this.updateAction : this.createAction;
        if (!action) {
            let actionName: any = Object.is(data.srfuf, '1') ? "updateAction" : "createAction";
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: `${this.controlInstance.codeName}` + (this.$t('app.formpage.notconfig.actionname') as string) });
            return;
        }
        Object.assign(arg, { viewparams: this.viewparams });
        const post: Promise<any> = this.service.add(action, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                if (response.data) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                }
                return;
            }
            const data = response.data;
            this.onFormLoad(data, 'autoSave');
            this.ctrlEvent({
                controlname: this.controlInstance.name,
                action: 'save',
                data: data,
            });
            AppCenterService.notifyMessage({ name: this.controlInstance?.appDataEntity?.codeName, action: 'appRefresh', data: data });
            this.$nextTick(() => {
                this.formState.next({ type: 'save', data: data });
            });
        }).catch((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status && response.data) {
                if (response.data.errorKey) {
                    if (Object.is(response.data.errorKey, "versionCheck")) {
                        this.$Modal.confirm({
                            title: (this.$t('app.formpage.saveerror') as string),
                            content: (this.$t('app.formpage.savecontent') as string),
                            onOk: () => {
                                this.refresh([]);
                            },
                            onCancel: () => { }
                        });
                    } else if (Object.is(response.data.errorKey, 'DupCheck')) {
                        let errorProp: string = response.data.message.match(/\[[a-zA-Z]*\]/)[0];
                        let name: string = this.service.getNameByProp(errorProp.substr(1, errorProp.length - 2));
                        if (name) {
                            this.$Notice.error({
                                title: (this.$t('app.commonWords.createFailed') as string),
                                desc: this.detailsModel[name].caption + " : " + arg[name] + (this.$t('app.commonWords.isExist') as string) + '!',
                            });
                        } else {
                            this.$Notice.error({
                                title: (this.$t('app.commonWords.createFailed') as string),
                                desc: response.data.message ? response.data.message : (this.$t('app.commonWords.sysException') as string),
                            })
                        }
                    } else if (Object.is(response.data.errorKey, 'DuplicateKeyException')) {
                        this.$Notice.error({
                            title: (this.$t('app.commonWords.createFailed') as string),
                            desc: this.detailsModel[this.majorKeyItemName].caption + " : " + arg[this.majorKeyItemName] + (this.$t('app.commonWords.isExist') as string) + '!',
                        });
                    } else {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message ? response.data.message : (this.$t('app.commonWords.sysException') as string) });
                    }
                } else {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message ? response.data.message : (this.$t('app.commonWords.sysException') as string) });
                }
                return;
            } else {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
            }
        });
    }

    /**
     * 保存
     *
     * @param {*} [opt={}]
     * @param {boolean} [showResultInfo] 
     * @param {boolean} [isStateNext] formState是否下发通知
     * @returns {Promise<any>}
     * @memberof EditFormControlBase
     */
    public async save(opt: any = {}, showResultInfo: boolean = true, isStateNext: boolean = true): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            if (!this.formValidateStatus()) {
                if (this.errorMessages && this.errorMessages.length > 0) {
                    let descMessage: string = '';
                    this.errorMessages.forEach((message: any) => {
                        descMessage = descMessage + '<p>' + message.error + '<p>';
                    })
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: descMessage });
                } else {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.formpage.valuecheckex') as string) });
                }
                return;
            }
            const arg: any = { ...opt };
            const data = this.getData();
            Object.assign(arg, this.context);
            Object.assign(arg, data);
            Object.assign(arg, { srfmajortext: data[this.majorMessageItemName] });
            if (isStateNext && this.drCount > 0) {
                this.drcounter = this.drCount;
                this.drsaveopt = opt;
                this.formState.next({ type: 'beforesave', data: arg });//先通知关系界面保存
                this.saveState = resolve;
                return;
            }
            if (this.viewparams && this.viewparams.copymode) {
                data.srfuf = '0';
            }
            const action: any = Object.is(data.srfuf, '1') ? this.updateAction : this.createAction;
            if (!action) {
                let actionName: any = Object.is(data.srfuf, '1') ? "updateAction" : "createAction";
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: `${this.controlInstance.codeName}` + (this.$t('app.formpage.notconfig.actionname') as string) });
                return;
            }
            Object.assign(arg, { viewparams: this.viewparams });
            const post: Promise<any> = Object.is(data.srfuf, '1') ? this.service.update(action, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator) : this.service.add(action, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
            this.ctrlBeginLoading();
            post.then((response: any) => {
                this.ctrlEndLoading();
                if (!response.status || response.status !== 200) {
                    if (response.data) {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                    }
                    return;
                }
                this.viewparams.copymode = false;
                const data = response.data;
                this.onFormLoad(data, 'save');
                this.ctrlEvent({
                    controlname: this.controlInstance.name,
                    action: 'save',
                    data: data,
                });
                AppCenterService.notifyMessage({ name: this.controlInstance?.appDataEntity?.codeName, action: 'appRefresh', data: data });
                this.$nextTick(() => {
                    this.formState.next({ type: 'save', data: data });
                });
                if (this.controlInstance.formFuncMode?.toLowerCase() != 'wizardform' && showResultInfo) {
                    this.$Notice.success({ title: '', desc: (data.srfmajortext ? data.srfmajortext : '') + '&nbsp;' + (this.$t('app.formpage.savesuccess') as string) });
                }
                resolve(response);
            }).catch((response: any) => {
                this.ctrlEndLoading();
                if (response && response.status && response.data) {
                    let appendErrors: string = ""; //附加提示信息
                    if (response.data.parameters && response.data.parameters.fieldErrors) {
                        (response.data.parameters.fieldErrors as Array<any>).forEach((item) => {
                            appendErrors += item.messages ? "<br/>" + item.messages : "";
                        })
                    }
                    if (response.data.errorKey) {
                        if (Object.is(response.data.errorKey, "versionCheck")) {
                            this.$Modal.confirm({
                                title: (this.$t('app.formpage.saveerror') as string),
                                content: (this.$t('app.formpage.savecontent') as string),
                                onOk: () => {
                                    this.refresh();
                                },
                                onCancel: () => { }
                            });
                        } else if (Object.is(response.data.errorKey, 'DupCheck')) {
                            let errorProp: string = response.data.message.match(/\[[a-zA-Z]*\]/)[0];
                            let name: string = this.service.getNameByProp(errorProp.substr(1, errorProp.length - 2));
                            if (name) {
                                this.$Notice.error({
                                    title: (this.$t('app.commonWords.createFailed') as string),
                                    desc: this.detailsModel[name].caption + " : " + arg[name] + (this.$t('app.commonWords.isExist') as string) + '!',
                                });
                            } else {
                                this.$Notice.error({
                                    title: (this.$t('app.commonWords.createFailed') as string),
                                    desc: response.data.message + appendErrors,
                                })
                            }
                        } else {
                            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message + appendErrors });
                        }
                    } else {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message + appendErrors });
                        reject(response);
                    }
                    return;
                } else {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                    reject(response);
                }
                reject(response);
            });
        })
    }

    /**
    * 删除
    *
    * @public
    * @param {*} [opt={}]
    * @memberof EditFormControlBase
    */
    public async remove(opt: Array<any> = [], showResultInfo?: boolean): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            if (!this.removeAction) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: `${this.controlInstance.codeName}` + (this.$t('app.formpage.notconfig.removeaction') as string) });
                return;
            }
            const arg: any = opt[0];
            const _this: any = this;
            Object.assign(arg, { viewparams: this.viewparams });
            this.ctrlBeginLoading();
            this.service.delete(_this.removeAction, JSON.parse(JSON.stringify(this.context)), arg, showResultInfo).then((response: any) => {
                this.ctrlEndLoading();
                if (response) {
                    const data = response.data;
                    this.ctrlEvent({
                        controlname: this.controlInstance.name,
                        action: 'remove',
                        data: data,
                    });
                    this.formState.next({ type: 'remove', data: data });
                    this.data.ismodify = false;
                    this.$Notice.success({ title: '', desc: (data.srfmajortext ? data.srfmajortext : '') + '&nbsp;' + (this.$t('app.formpage.deletesuccess') as string) });
                    AppCenterService.notifyMessage({ name: this.controlInstance?.appDataEntity?.codeName, action: 'appRefresh', data: data });
                    resolve(response);
                }
            }).catch((error: any) => {
                this.ctrlEndLoading();
                const { data: _data } = error;
                this.$Notice.error({ title: _data.title, desc: _data.message });
                reject(error);
            });
        });
    }

    /**
     * 工作流启动
     *
     * @param {*} [data={}]
     * @param {*} [localdata={}]
     * @returns {Promise<any>}
     * @memberof EditFormControlBase
     */
    public async wfstart(data: any, localdata?: any): Promise<any> {
        if (!this.formValidateStatus()) {
            return;
        }
        return new Promise((resolve: any, reject: any) => {
            const _this: any = this;
            const formData: any = this.getData();
            const copyData: any = Util.deepCopy(data[0]);
            Object.assign(formData, { viewparams: copyData });
            const post: Promise<any> = Object.is(formData.srfuf, '1') ? this.service.update(this.updateAction, JSON.parse(JSON.stringify(this.context)), formData, this.showBusyIndicator, true) : this.service.add(this.createAction, JSON.parse(JSON.stringify(this.context)), formData, this.showBusyIndicator, true);
            this.ctrlBeginLoading();
            post.then((response: any) => {
                this.ctrlEndLoading();
                const arg: any = response.data;
                const responseData: any = Util.deepCopy(arg);
                // 保存完成UI处理
                this.onFormLoad(arg, 'save');
                this.ctrlEvent({
                    controlname: this.controlInstance.name,
                    action: 'save',
                    data: arg,
                });
                this.$nextTick(() => {
                    this.formState.next({ type: 'save', data: arg });
                });
                // 准备工作流数据,填充未存库数据
                let tempWFData: any = {};
                if (copyData && Object.keys(copyData).length > 0) {
                    Object.keys(copyData).forEach((key: string) => {
                        if ((!arg.hasOwnProperty(key)) || (!arg[key] && copyData[key])) {
                            tempWFData[key] = copyData[key];
                        }
                    })
                }
                // 准备提交参数
                if (this.viewparams) {
                    let copyViewParams: any = Util.deepCopy(this.viewparams);
                    if (copyViewParams.w) {
                        delete copyViewParams.w;
                    }
                    Object.assign(responseData, copyViewParams);
                }
                if (tempWFData && Object.keys(tempWFData).length > 0) {
                    Object.assign(responseData, tempWFData);
                }
                Object.assign(arg, { viewparams: responseData });
                // 强制补充srfwfmemo
                if (copyData.srfwfmemo) {
                    Object.assign(arg, { srfwfmemo: copyData.srfwfmemo });
                }
                const result: Promise<any> = this.service.wfstart(_this.WFStartAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator, localdata);
                this.ctrlBeginLoading();
                result.then((response: any) => {
                    this.ctrlEndLoading();
                    if (!response || response.status !== 200) {
                        if (response.data) {
                            this.$Notice.error({ title: '', desc: (this.$t('app.formpage.workflow.starterror') as string) + ', ' + response.data.message });
                        }
                        return;
                    }
                    this.$Notice.info({ title: '', desc: (this.$t('app.formpage.workflow.startsuccess') as string) });
                    resolve(response);
                }).catch((response: any) => {
                    this.ctrlEndLoading();
                    if (response && response.status && response.data) {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                        reject(response);
                        return;
                    }
                    if (!response || !response.status || !response.data) {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                        reject(response);
                        return;
                    }
                    reject(response);
                });
            }).catch((response: any) => {
                this.ctrlEndLoading();
                if (response && response.status && response.data) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                    reject(response);
                    return;
                }
                if (!response || !response.status || !response.data) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                    reject(response);
                    return;
                }
                reject(response);
            })
        });
    }

    /**
     * 工作流提交
     *
     * @param {*} [data={}]
     * @param {*} [localdata={}]
     * @returns {Promise<any>}
     * @memberof EditFormControlBase
     */
    public async wfsubmit(data: any, localdata?: any): Promise<any> {
        if (!this.formValidateStatus()) {
            return;
        }
        return new Promise((resolve: any, reject: any) => {
            const _this: any = this;
            const arg: any = data[0];
            const copyData: any = Util.deepCopy(arg);
            Object.assign(this.viewparams, copyData);
            Object.assign(arg, { viewparams: this.viewparams });
            if (!arg[this.controlInstance?.appDataEntity?.keyField?.codeName?.toLowerCase()] || Object.is(arg[this.controlInstance?.appDataEntity?.keyField?.codeName?.toLowerCase()], '')) {
                return;
            }
            const post: Promise<any> = Object.is(arg.srfuf, '1') ? this.service.update(this.updateAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator, true) : this.service.add(this.createAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator, true);
            this.ctrlBeginLoading();
            post.then((response: any) => {
                this.ctrlEndLoading();
                const responseData: any = response.data;
                let tempResponseData: any = Util.deepCopy(response);
                this.service.handleResponse('save', tempResponseData);
                const arg: any = tempResponseData.data;
                // 保存完成UI处理
                this.onFormLoad(arg, 'save');
                this.ctrlEvent({
                    controlname: this.controlInstance.name,
                    action: 'save',
                    data: arg,
                });
                this.$nextTick(() => {
                    this.formState.next({ type: 'save', data: arg });
                });
                // 准备工作流数据,填充未存库数据
                let tempWFData: any = {};
                if (copyData && Object.keys(copyData).length > 0) {
                    Object.keys(copyData).forEach((key: string) => {
                        if ((!arg.hasOwnProperty(key)) || (!arg[key] && copyData[key])) {
                            tempWFData[key] = copyData[key];
                        }
                    })
                }
                // 准备提交参数
                if (this.viewparams) {
                    Object.assign(responseData, this.viewparams);
                }
                if (tempWFData && Object.keys(tempWFData).length > 0) {
                    Object.assign(responseData, tempWFData);
                }
                // 补充逻辑完成参数
                if (localdata && localdata.type && Object.is(localdata.type, "finish")) {
                    Object.assign(responseData, { srfwfpredefaction: "finish" });
                }
                Object.assign(arg, { viewparams: responseData });
                // 强制补充srfwfmemo
                if (copyData.srfwfmemo) {
                    Object.assign(arg, { srfwfmemo: copyData.srfwfmemo });
                }
                const result: Promise<any> = this.service.wfsubmit(_this.WFSubmitAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator, localdata);
                this.ctrlBeginLoading();
                result.then((response: any) => {
                    this.ctrlEndLoading();
                    if (!response || response.status !== 200) {
                        if (response.data) {
                            this.$Notice.error({ title: '', desc: (this.$t('app.formpage.workflow.submiterror') as string) + ', ' + response.data.message });
                        }
                        return;
                    }
                    this.onFormLoad(arg, 'submit');
                    AppCenterService.notifyMessage({ name: this.controlInstance?.appDataEntity?.codeName, action: 'appRefresh', data: data });
                    this.$Notice.info({ title: '', desc: (this.$t('app.formpage.workflow.submitsuccess') as string) });
                    resolve(response);
                }).catch((response: any) => {
                    this.ctrlEndLoading();
                    if (response && response.status && response.data) {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                        reject(response);
                        return;
                    }
                    if (!response || !response.status || !response.data) {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                        reject(response);
                        return;
                    }
                    reject(response);
                });
            }).catch((response: any) => {
                this.ctrlEndLoading();
                if (response && response.status && response.data) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                    reject(response);
                    return;
                }
                if (!response || !response.status || !response.data) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                    reject(response);
                    return;
                }
                reject(response);
            })
        })
    }

    /**
     * 表单刷新数据
     *
     * @param {any} args
     * @memberof EditFormControlBase
     */
    public refresh(args: any = {}): void {
        if (this.data.srfkey && !Object.is(this.data.srfkey, '')) {
            Object.assign(args, { srfkey: this.data.srfkey });
            this.load(args);
            return;
        }
        if (this.data.srfkeys && !Object.is(this.data.srfkeys, '')) {
            Object.assign(args, { srfkey: this.data.srfkeys });
            this.load(args);
            return;
        }
    }

    /**
     * 面板行为
     *
     * @param {string} [action] 调用的实体行为
     * @param {string} [emitAction] 抛出行为
     * @param {*} [data={}] 传入数据
     * @param {boolean} [showloading] 是否显示加载状态
     * 
     * @memberof EditFormControlBase
     */
    public panelAction(action: string, emitAction: string, data: any = {}, showloading?: boolean): void {
        if (!action || (action && Object.is(action, ''))) {
            return;
        }
        const arg: any = { ...data };
        const formdata = this.getData();
        Object.assign(arg, formdata);
        Object.assign(arg, this.viewparams);
        const post: Promise<any> = this.service.frontLogic(action, JSON.parse(JSON.stringify(this.context)), arg, showloading);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                if (response.data) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                }
                return;
            }
            const data = response.data;
            this.onFormLoad(data, emitAction);
            this.ctrlEvent({
                controlname: this.controlInstance.name,
                action: emitAction,
                data: data,
            });
            this.$nextTick(() => {
                this.formState.next({ type: emitAction, data: data });
            });
        }).catch((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status && response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                return;
            }
        });
    }

    /**
     * 表单项更新
     *
     * @param {string} mode 界面行为名称
     * @param {*} [data={}] 请求数据
     * @param {string[]} updateDetails 更新项
     * @param {boolean} [showloading] 是否显示加载状态
     * @returns {void}
     * @memberof EditFormControlBase
     */
    public updateFormItems(mode: string, data: any = {}, updateDetails: string[], showloading: boolean = false): void {
        if (!mode || (mode && Object.is(mode, ''))) {
            return;
        }
        const arg: any = Object.assign(this.viewparams, data);
        const post: Promise<any> = this.service.frontLogic(mode, JSON.parse(JSON.stringify(this.context)), arg, showloading);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response || response.status !== 200) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.formpage.updateerror') as string) });
                return;
            }
            const data = response.data;
            const _data: any = {};
            updateDetails.forEach((name: string) => {
                if (!data.hasOwnProperty(name)) {
                    return;
                }
                Object.assign(_data, { [name]: data[name] });
            });
            this.setFormEnableCond(_data);
            this.fillForm(_data, 'updateFormItem');
            this.formLogic({ name: '', newVal: null, oldVal: null });
            this.dataChang.next(JSON.stringify(this.data));
            this.$nextTick(() => {
                this.formState.next({ type: 'updateformitem', ufimode: arg.srfufimode, data: _data });
            });
        }).catch((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status && response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                return;
            }
        });
    }

    /**
     * 保存并退出
     *
     * @param {any[]} args
     * @memberof EditFormControlBase
     */
    public saveAndExit(data: any[]): Promise<any> {
        let _this = this;
        return new Promise((resolve: any, reject: any) => {
            let arg: any = {};
            if (data && data.length > 0) {
                Object.assign(arg, data[0]);
            }
            _this.currentAction = "saveAndExit";
            _this.save([arg]).then((res) => {
                if (res) {
                    _this.closeView(res.data);
                }
                resolve(res);
            }).catch((error) => {
                reject(error);
            })
        })
    }

    /**
     * 保存并新建
     *
     * @param {any[]} args
     * @memberof EditFormControlBase
     */
    public saveAndNew(data: any[]): Promise<any> {
        let _this = this;
        return new Promise((resolve: any, reject: any) => {
            let arg: any = {};
            if (data && data.length > 0) {
                Object.assign(arg, data[0]);
            }
            _this.currentAction = "saveAndNew";
            _this.save([arg]).then((res) => {
                _this.ResetData(res);
                _this.loadDraft({});
            }).catch((error) => {
                reject(error);
            })
        })
    }

    /**
     * 删除并退出
     *
     * @param {any[]} args
     * @memberof EditFormControlBase
     */
    public removeAndExit(data: any[]): Promise<any> {
        let _this = this;
        return new Promise((resolve: any, reject: any) => {
            let arg: any = {};
            if (data && data.length > 0) {
                Object.assign(arg, data[0]);
            }
            _this.remove([arg]).then((res) => {
                if (res) {
                    _this.closeView(res.data);
                }
                resolve(res);
            }).catch((error) => {
                reject(error);
            })
        })
    }

    /**
    * 关系界面数据保存完成
    *
    * @param {any} $event
    * @memberof EditFormControlBase
    */
    public drdatasaved($event: any) {
        let _this = this;
        this.drcounter--;
        if (this.drcounter === 0) {
            this.save(this.drsaveopt, false, false).then((res) => {
                this.saveState(res);
                this.drsaveopt = {};
                if (Object.is(_this.currentAction, "saveAndNew")) {
                    _this.ResetData(res);
                    _this.loadDraft({});
                } else if (Object.is(_this.currentAction, "saveAndExit")) {
                    if (res) {
                        _this.closeView(res.data);
                    }
                }
            });
        }
    }

    /**
     * 表单加载完成
     *
     * @public
     * @param {*} [data={}]
     * @param {string} [action]
     * @memberof EditFormControlBase
     */
    public onFormLoad(data: any = {}, action: string): void {
        if (this.controlInstance?.appDeCodeName) {
            if (Object.is(action, "save") || Object.is(action, "autoSave") || Object.is(action, "submit"))
                // 更新context的实体主键
                if (data[this.controlInstance?.appDeCodeName]) {
                    Object.assign(this.context, { [this.controlInstance?.appDeCodeName]: data[this.controlInstance?.appDeCodeName] })
                }
        }
        this.setFormEnableCond(data);
        this.computeButtonState(data);
        this.fillForm(data, action);
        this.oldData = {};
        Object.assign(this.oldData, Util.deepCopy(this.data));
        // this.$store.commit('viewaction/setViewDataChange', { viewtag: this.viewtag, viewdatachange: false });
        this.formLogic({ name: '', newVal: null, oldVal: null });
    }

    /**
     * 值填充
     *
     * @param {*} [_datas={}]
     * @param {string} [action]
     * @memberof EditFormControlBase
     */
    public fillForm(_datas: any = {}, action: string): void {
        this.ignorefieldvaluechange = true;
        Object.keys(_datas).forEach((name: string) => {
            if (this.data.hasOwnProperty(name)) {
                this.data[name] = _datas[name];
            }
        });
        if (Object.is(action, 'loadDraft')) {
            this.createDefault();
        }
        if (Object.is(action, 'load')) {
            this.updateDefault();
        }
        this.$nextTick(function () {
            this.ignorefieldvaluechange = false;
        })
    }

    /**
      * 置空对象
      *
      * @param {any[]} args
     * @memberof EditFormControlBase
      */
    public ResetData(_datas: any) {
        if (Object.keys(_datas).length > 0) {
            Object.keys(_datas).forEach((name: string) => {
                if (this.data.hasOwnProperty(name)) {
                    this.data[name] = null;
                }
            });
        }
    }

    /**
     * 表单项检查逻辑
     *
     * @public
     * @param name 属性名
     * @memberof EditFormControlBase
     */
    public checkItem(name: string): Promise<any> {
        return new Promise((resolve, reject) => {
            var validator = new schema({ [name]: this.rules[name] });
            validator.validate({ [name]: this.data[name] }).then(() => {
                resolve(true);
            })
                .catch(() => {
                    resolve(false);
                });;
        })
    }

    /**
     * 设置表单项是否启用
     *
     * @public
     * @param {*} data
     * @memberof EditFormControlBase
     */
    public setFormEnableCond(data: any): void {
        Object.values(this.detailsModel).forEach((detail: any) => {
            if (!Object.is(detail.detailType, 'FORMITEM')) {
                return;
            }
            const formItem: any = detail;
            formItem.setEnableCond(data.srfuf);
        });
    }

    /**
     * 编辑器行为触发
     *
     * @param {*} arg
     * @returns {void}
     * @memberof EditFormControlBase
     */
    public onFormItemActionClick({ tag, event }: any) {
        AppViewLogicService.getInstance().executeViewLogic(`${this.controlInstance.name}_${tag}_click`, event, this, tag, this.controlInstance.getPSAppViewLogics);
    }

    /**
     * 计算表单按钮权限状态
     *
     * @param {*} [data] 传入数据
     * @memberof EditFormControlBase
     */
    public computeButtonState(data: any) {
        let targetData: any = this.transformData(data);
        ViewTool.calcActionItemAuthState(targetData, this.actionModel, this.appUIService);
        if (this.detailsModel && Object.keys(this.detailsModel).length > 0) {
            Object.keys(this.detailsModel).forEach((name: any) => {
                const model = this.detailsModel[name];
                if (model?.detailType == "BUTTON" && model.uiaction?.tag) {
                    // 更新detailsModel里的按钮的权限状态值
                    this.detailsModel[name].visible = this.actionModel[model.uiaction.tag].visabled;
                    this.detailsModel[name].disabled = this.actionModel[model.uiaction.tag].disabled;
                    this.detailsModel[name].isPower = this.actionModel[model.uiaction.tag].dataActionResult === 1 ? true : false;
                } else if (model?.detailType == 'GROUPPANEL' && model.uiActionGroup?.details?.length > 0) {
                    // 更新分组面板界面行为组的权限状态值
                    model.uiActionGroup.details.forEach((actionDetail: any) => {
                        actionDetail.visible = this.actionModel[actionDetail.tag].visabled;
                        actionDetail.disabled = this.actionModel[actionDetail.tag].disabled;
                    })
                }
            })
        }
    }

    /**
     * 初始化界面行为模型
     *
     * @type {*}
     * @memberof EditFormControlBase
     */
    public initCtrlActionModel() {
        if (this.controlInstance.allFormDetails?.length > 0) {
            this.controlInstance.allFormDetails.forEach((detail: any) => {
                if (detail?.detailType == 'BUTTON' && detail.getPSUIAction) {
                    // 添加表单按钮的actionModel
                    const appUIAction: any = Util.deepCopy(detail.getPSUIAction);
                    this.actionModel[appUIAction.uIActionTag] = Object.assign(appUIAction, { disabled: false, visabled: true, getNoPrivDisplayMode: appUIAction.getNoPrivDisplayMode ? appUIAction.getNoPrivDisplayMode : 6 });
                } else if (detail?.detailType == 'GROUPPANEL' && detail.uiActionGroup?.getPSUIActionGroupDetails?.length > 0) {
                    // 添加表单分组界面行为组的actionModel
                    detail.uiActionGroup.getPSUIActionGroupDetails.forEach((actionDetail: any) => {
                        if (actionDetail?.getPSUIAction) {
                            const appUIAction: any = Util.deepCopy(actionDetail.getPSUIAction);
                            this.actionModel[appUIAction.uIActionTag] = Object.assign(appUIAction, { disabled: false, visabled: true, getNoPrivDisplayMode: appUIAction.getNoPrivDisplayMode ? appUIAction.getNoPrivDisplayMode : 6 });
                        }
                    })
                }
            })
        }
    }

    /**
     * 设置表单项错误提示信息
     * 
     * @param {*} prop 表单项字段名
     * @param {*} status 校验状态
     * @param {*} error 错误信息
     * @memberof EditFormControlBase
     */
    public formItemValidate(prop: string, status: boolean, error: string) {
        error = error ? error : '';
        if (this.errorMessages && this.errorMessages.length > 0) {
            const index = this.errorMessages.findIndex((errorMessage: any) => Object.is(errorMessage.prop, prop));
            if (index != -1) {
                this.errorMessages[index].error = error;
            } else {
                this.errorMessages.push({ prop: prop, error: error });
            }
        } else {
            this.errorMessages.push({ prop: prop, error: error });
        }
    }

    /**
     * 显示更多模式切换操作
     *
     * @type {string}
     * @memberof EditFormControlBase
     */
    public manageContainerClick(name: string) {
        let model = this.detailsModel[name];
        if (model.isManageContainer) {
            model.setManageContainerStatus(!model.manageContainerStatus);
            model.controlledItems.forEach((item: any) => {
                if (this.detailsModel[item].isControlledContent) {
                    this.detailsModel[item].setVisible(model.manageContainerStatus ? this.detailsModel[item].oldVisible : false);
                }
            });
            this.$forceUpdate();
        }
    }

    /**
     *打印
     *@memberof @memberof EditFormControlBase
     */
    public print() {
        let _this: any = this;
        _this.$print({ id: `${this.controlInstance.appDeCodeName?.toLowerCase()}_${this.controlInstance.codeName}`, popTitle: `${this.controlInstance.appDeLogicName}` });
    }

    /**
     * 新建默认值
     *
     * @memberof EditFormControlBase
     */
    public createDefault() {
        const { allFormDetails } = this.controlInstance;
        if (allFormDetails.length > 0) {
            for (const detail of allFormDetails) {
                const property = detail?.codeName?.toLowerCase();
                if ((detail?.createDV || detail?.createDVT) && this.data.hasOwnProperty(property)) {
                    switch (detail.createDVT) {
                        case 'CONTEXT':
                            this.data[property] = this.viewparams[detail?.createDV];
                            break;
                        case 'SESSION':
                            this.data[property] = this.context[detail?.createDV];
                            break;
                        case 'APPDATA':
                            this.data[property] = this.context[detail?.createDV];
                            break;
                        case 'OPERATORNAME':
                            this.data[property] = this.context['srfusername'];
                            break;
                        case 'OPERATOR':
                            this.data[property] = this.context['srfuserid'];
                            break;
                        case 'CURTIME':
                            this.data[property] = Util.dateFormat(new Date());
                            break;
                        case 'PARAM':
                            this.data[property] = this.service.getRemoteCopyData()?.[property] || null;
                            break;
                        default:
                            this.data[property] = detail?.appDeField?.isNumber ? Number(detail?.createDV) : detail?.createDV;
                            break;
                    }
                }
            }
        }
    }

    /**
     * 更新默认值
     *
     * @memberof EditFormControlBase
     */
    public updateDefault() {
        const { allFormDetails } = this.controlInstance;
        if (allFormDetails.length > 0) {
            for (const detail of allFormDetails) {
                const property = detail?.codeName?.toLowerCase();
                if ((detail?.updateDV || detail?.updateDVT) && this.data.hasOwnProperty(property)) {
                    switch (detail?.updateDVT) {
                        case 'CONTEXT':
                            this.data[property] = this.viewparams[detail?.updateDV];
                            break;
                        case 'SESSION':
                            this.data[property] = this.context[detail?.updateDV];
                            break;
                        case 'APPDATA':
                            this.data[property] = this.context[detail?.updateDV];
                            break;
                        case 'OPERATORNAME':
                            this.data[property] = this.context['srfusername'];
                            break;
                        case 'OPERATOR':
                            this.data[property] = this.context['srfuserid'];
                            break;
                        case 'CURTIME':
                            this.data[property] = Util.dateFormat(new Date());
                            break;
                        case 'PARAM':
                            this.data[property] = this.service.getRemoteCopyData()?.[property] || null;
                            break;
                        case 'RESET':
                            this.data[property] = null;
                        default:
                            this.data[property] = detail?.appDeField?.isNumber ? Number(detail?.updateDV) : detail?.updateDV;
                            break;
                    }
                }
            }
        }
    }

    /**
     * 初始化值规则
     *
     * @memberof EditFormControlBase
     */
    public initRules() {
        // 先初始化系统值规则和属性值规则
        let staticRules: any = {};
        const { allFormItemVRs } = this.controlInstance;
        allFormItemVRs?.forEach((item: any) => {
            const { getPSDEFormItemName: formItemName, checkMode, valueRuleType, getPSSysValueRule: sysRule, getPSDEFValueRule: deRule } = item;
            if (!staticRules[formItemName]) {
                staticRules[formItemName] = [];
            }
            // 排除后台检查的值规则
            if (checkMode == 2) {
                return
            }
            // 系统值规则
            if (valueRuleType == 'SYSVALUERULE' && sysRule) {
                // 正则值规则
                if (sysRule.ruleType == 'REG') {
                    staticRules[formItemName].push({
                        pattern: new RegExp(sysRule.regExCode),
                        message: sysRule.ruleInfo,
                        trigger: ['change', 'blur']
                    })
                    // 脚本值规则
                } else if (sysRule.ruleType == 'SCRIPT') {
                    staticRules[formItemName].push({
                        validator: (rule: any, value: any, callback: any) => {
                            // 空值时不校验
                            if (Util.isEmpty(this.data[formItemName])) {
                                return true
                            }
                            let source = this.data;
                            try {
                                eval(sysRule.scriptCode);
                            } catch (error) {
                                console.error(error);
                            }
                            return true;
                        },
                        trigger: ['change', 'blur']
                    })
                }
                // 属性值规则
            } else if (valueRuleType == 'DEFVALUERULE' && deRule) {
                // 有值项的情况，校验值项的值
                let formItem = this.controlInstance.getFormDetailByName(formItemName);
                let valueName = formItem?.valueItemName || formItemName;
                staticRules[formItemName].push({
                    validator: (rule: any, value: any, callback: any, source: any) => {
                        // 空值时不校验
                        if (Util.isEmpty(this.data[valueName])) {
                            return true
                        }
                        const { isPast, infoMessage } = Verify.verifyDeRules(valueName, this.data, deRule.getPSDEFVRGroupCondition);
                        if (!isPast) {
                            callback(new Error(infoMessage || deRule.ruleInfo));
                        }
                        return true;
                    },
                    trigger: ['change', 'blur']
                })
            }
        })

        // 初始化非空值规则和数据类型值规则
        this.rules = {};
        const { allFormDetails } = this.controlInstance;
        if (allFormDetails?.length > 0) {
            for (const detail of allFormDetails) {
                if (detail.detailType == 'FORMITEM' && detail.editorType != 'HIDDEN' && !detail.compositeItem) {
                    // let type = detail.appDeField?.isNumber ? 'number' : 'string';
                    let otherRules = staticRules[detail.name] || [];
                    let editorRules = Verify.buildVerConditions(detail.editor)
                    this.rules[detail.name] = [
                        // 非空值规则
                        { validator: (rule: any, value: any, callback: any) => { return !(this.detailsModel[detail.name].required && (value === null || value === undefined || value === "")) }, message: `${detail.caption} 必须填写` },
                        // 表单值规则
                        ...otherRules,
                        // 编辑器基础值规则
                        ...editorRules
                    ]
                }
            }
        }
    }

    /**
     * 初始化表单成员模型
     *
     * @memberof EditFormControlBase
     */
    public initDetailsModel() {
        this.detailsModel = {};
        const { allFormDetails, noTabHeader, name, formPages } = this.controlInstance;
        if (allFormDetails?.length > 0) {
            for (const detail of allFormDetails) {
                let detailOpts: any = {
                    name: detail.name,
                    caption: detail.caption,
                    isShowCaption: detail.showCaption,
                    detailType: detail.detailType,
                    visible: !detail.getPSDEFDGroupLogic('PANELVISIBLE'),
                    form: this,
                    isControlledContent: detail.showMoreMode == 1,
                };
                let detailModel: any = null;
                switch (detail.detailType) {
                    case 'BUTTON':
                        Object.assign(detailOpts, {
                            disabled: false,
                        });
                        if (detail.getPSUIAction) {
                            detailOpts.uiaction = {
                                type: detail.getPSUIAction.uIActionType,
                                tag: detail.getPSUIAction.uIActionTag,
                                visabled: true,
                                disabled: false,
                            }
                            if (detail.getPSUIAction.actionTarget) {
                                detailOpts.uiaction.actiontarget = detail.getPSUIAction.actionTarget;
                            }
                            if (detail.getPSUIAction.getNoPrivDisplayMode) {
                                detailOpts.uiaction.noprivdisplaymode = detail.getPSUIAction.getNoPrivDisplayMode;
                            }
                            if (detail.getPSUIAction.dataAccessAction) {
                                detailOpts.uiaction.dataaccaction = detail.getPSUIAction.dataAccessAction;
                            }
                        }
                        detailModel = new FormButtonModel(detailOpts);
                        break;
                    case 'FORMITEM':
                        Object.assign(detailOpts, {
                            disabled: false,
                            required: !detail.allowEmpty,
                            enableCond: detail.enableCond,
                        });
                        detailModel = new FormItemModel(detailOpts);
                        break;
                    case 'GROUPPANEL':
                        detailOpts.isManageContainer = detail.showMoreMode == 2;

                        // 界面行为组
                        let uiActionGroup: any = {
                            caption: detail.uiActionGroup?.name,
                            langbase: '',
                            extractMode: detail.actionGroupExtractMode || 'ITEM',
                            details: [],
                        }
                        if (detail.uiActionGroup?.getPSUIActionGroupDetails?.length > 0) {
                            detail.uiActionGroup.getPSUIActionGroupDetails.forEach((actionDetail: any) => {
                                let temp: any = {
                                    name: `${detail.name}_${actionDetail.name}`,
                                    tag: actionDetail?.getPSUIAction?.uIActionTag,
                                    caption: actionDetail?.getPSUIAction?.caption || '',
                                    disabled: false,
                                    visabled: true,
                                    noprivdisplaymode: actionDetail?.getPSUIAction?.getNoPrivDisplayMode,
                                    actiontarget: actionDetail?.getPSUIAction?.actionTarget || '',
                                    dataaccaction: actionDetail?.getPSUIAction?.dataAccessAction || '',
                                    isShowCaption: actionDetail.showCaption,
                                    isShowIcon: actionDetail.showIcon,
                                }
                                if (actionDetail?.getPSUIAction?.$appDataEntity) {
                                    temp.uiactiontag = `${actionDetail?.getPSUIAction?.$appDataEntity?.codeName?.toLowerCase()}_${actionDetail?.getPSUIAction?.uIActionTag?.toLowerCase()}`
                                }
                                // 图标
                                if (actionDetail?.getPSUIAction?.getPSSysImage) {
                                    let image = actionDetail.getPSUIAction.getPSSysImage;
                                    if (image.cssClass) {
                                        temp.icon = image.cssClass;
                                    } else {
                                        temp.img = image.imagePath;
                                    }
                                }
                                uiActionGroup.details.push(temp);
                            })
                        }
                        detailOpts.uiActionGroup = uiActionGroup;

                        // 受控容器的成员
                        let showMoreModeItems: any[] = [];
                        //  支持锚点的成员
                        let anchorPoints: any[] = [];
                        detail.getChildFormDetails?.forEach((item: any, index: number) => {
                            if (item.showMoreMode == 1) {
                                showMoreModeItems.push(item.name);
                            }
                            if (item.enableAnchor) {
                                anchorPoints.push({
                                    name: item.name,
                                    editor: item.editor ? item.editor : {}
                                });
                            }
                            const showMore = item.getShowMoreMgrPSDEFormDetail;
                            if (showMore && showMore.id && Object.is(showMore.id, detail.name)) {
                                detailOpts.isManageContainer = true;
                            }
                        })
                        detailOpts.controlledItems = showMoreModeItems;
                        detailOpts.anchorPoints = anchorPoints;
                        detailModel = new FormGroupPanelModel(detailOpts);
                        break;
                    case 'TABPANEL':
                        // 添加tab分页
                        let tabPages: any[] = [];
                        detail.getChildFormDetails?.forEach((item: any, index: number) => {
                            tabPages.push({
                                name: item.name,
                                index: index,
                                visible: !item.getPSDEFDGroupLogic('PANELVISIBLE')
                            })
                        })
                        Object.assign(detailOpts, {
                            tabPages: tabPages,
                        });
                        detailModel = new FormTabPanelModel(detailOpts);
                        break;
                    case 'TABPAGE':
                        detailModel = new FormTabPageModel(detailOpts);
                        break;
                    case 'FORMPAGE':
                        detailModel = new FormPageModel(detailOpts);
                        break;
                    case 'FORMPART':
                        detailModel = new FormPartModel(detailOpts);
                        break;
                    case 'DRUIPART':
                        this.drCount++;
                        detailModel = new FormDruipartModel(detailOpts);
                        break;
                    case 'IFRAME':
                        detailModel = new FormIFrameModel(detailOpts);
                        break;
                    case 'RAWITEM':
                        detailModel = new FormRawItemModel(detailOpts);
                        break;
                    case 'USERCONTROL':
                        detailModel = new FormUserControlModel(detailOpts);
                        break;
                }
                this.$set(this.detailsModel, detail.name, detailModel)
            }
        }
        // 有分页头表格时
        if (!noTabHeader) {
            let formPages: any[] = [];
            formPages?.forEach((item: any, index: number) => {
                formPages.push({
                    name: item.name,
                    index: index,
                    visible: !item.getPSDEFDGroupLogic('PANELVISIBLE')
                })
            })
            this.$set(this.detailsModel, name, new FormTabPanelModel({
                caption: name,
                detailType: 'TABPANEL',
                name: name,
                visible: true,
                isShowCaption: true,
                form: this,
                tabPages: formPages,
            }))
        }
    }

    /**
     * 重置表单项值
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof AppDefaultForm
     */
    public resetFormData({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void {
        const { formItems } = this.controlInstance;
        if (formItems?.length > 0) {
            for (const item of formItems) {
                if (item.resetItemName && item.resetItemName == name) {
                    this.onFormItemValueChange({ name: item.name, value: null });
                    if (item.valueItemName) {
                        this.onFormItemValueChange({ name: item.valueItemName, value: null });
                    }
                }
            }
        }
    }

    /**
     * 表单逻辑
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof FormControlBase
     */
    public async formLogic({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }) {
        const { allFormDetails } = this.controlInstance;
        // 表单动态逻辑
        allFormDetails?.forEach((detail: any) => {
            detail.allPSDEFDGroupLogics?.forEach((logic: any) => {
                if (Object.is(name, '') || logic.relatedDetailNames.has(name)) {
                    let ret = this.verifyGroupLogic(this.data, logic);
                    switch (logic.logicCat) {
                        // 动态空输入，不满足则必填
                        case 'ITEMBLANK':
                            this.detailsModel[detail.name].required = !ret;
                            break;
                        // 动态启用，满足则启用
                        case 'ITEMENABLE':
                            this.detailsModel[detail.name].setDisabled(!ret);
                            break;
                        // 动态显示，满足则显示
                        case 'PANELVISIBLE':
                            this.detailsModel[detail.name].setVisible(ret);
                            break;
                    }
                }
            })
        })

        // 表单项更新
        let formDetail: IBizFormDetailModel = this.controlInstance.getFormDetailByName(name);
        if (formDetail?.formItemUpdate) {
            const { getPSAppDEMethod, getPSDEFIUpdateDetails, showBusyIndicator } = formDetail.formItemUpdate;
            let details: string[] = [];
            getPSDEFIUpdateDetails?.forEach((item: any) => {
                details.push(item.name)
            })
            if (await this.checkItem(formDetail.name)) {
                this.updateFormItems(getPSAppDEMethod?.id, this.data, details, showBusyIndicator);
            }
        }
    }

    /**
     * 校验动态逻辑结果
     *
     * @param {*} data 数据对象
     * @param {*} logic 逻辑对象
     * @returns
     * @memberof EditFormControlBase
     */
    public verifyGroupLogic(data: any, logic: any) {
        if (logic.logicType == 'GROUP' && logic?.getPSDEFDLogics?.length > 0) {
            let result: boolean = true;
            if (logic.groupOP == 'AND') {
                let falseItem = logic.getPSDEFDLogics.find((childLogic: any) => {
                    return !this.verifyGroupLogic(data, childLogic);
                })
                result = falseItem == undefined;
            } else if (logic.groupOP == 'OR') {
                let trueItem = logic.getPSDEFDLogics.find((childLogic: any) => {
                    return this.verifyGroupLogic(data, childLogic);
                })
                result = trueItem != undefined;
            }
            // 是否取反
            return logic.notMode ? !result : result;
        } else if (logic.logicType == 'SINGLE') {
            return Verify.testCond(data[logic.dEFDName.toLowerCase()], logic.condOP, logic.value)
        }
        return false;
    }


    /**
     * 处理操作列点击
     * 
     * @param {*} event 事件对象
     * @param {*} formDetail 表单成员模型对象
     * @param {*} actionDetal 界面行为模型对象
     * @memberof EditFormControlBase
     */
    public handleActionClick(event: any, formDetail: any, actionDetal: any) {
        AppViewLogicService.getInstance().executeViewLogic(this.getViewLogicTag('form', formDetail.codeName, actionDetal.name), event, this, undefined, this.controlInstance.getPSAppViewLogics);
    }
}
