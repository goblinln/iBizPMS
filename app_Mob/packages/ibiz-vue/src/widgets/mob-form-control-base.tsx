import { FormButtonModel, FormDruipartModel, FormGroupPanelModel, FormIFrameModel, FormItemModel, FormPageModel, FormPartModel, FormTabPageModel, FormTabPanelModel, FormUserControlModel, ModelTool, Util, Verify } from 'ibiz-core';
import schema from 'async-validator';
import { Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { AppMobFormService } from '../ctrl-service';
import { MainControlBase } from './main-control-base';
import { AppCenterService } from '../app-service/common-service';
import { IPSAppDEUIAction, IPSDEEditForm, IPSDEEditFormItem, IPSDEFDCatGroupLogic, IPSDEFormButton, IPSDEFormDetail, IPSDEFormGroupPanel, IPSDEFormItem, IPSDEFormItemVR, IPSDEFormPage, IPSDEFormTabPage, IPSDEFormTabPanel } from '@ibiz/dynamic-model-api';


/**
 * 表单部件基类
 *
 * @export
 * @class FromControlBase
 * @extends {MainControlBase}
 */
export class MobFormControlBase extends MainControlBase {

    /**
     * 部件模型实例对象
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public controlInstance!: IPSDEEditForm;

    /**
    * 是否自动加载
    *
    * @type {*}
    * @memberof MobFormControlBase
    */
    public isAutoLoad?: any;

    /**
     * 关系界面数量
     *
     * @type {number}
     * @memberof EditFormControlBase
     */
    public drCount: number = 0;

    /**
     * 是否默认保存
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public isAutoSave?: any;

    /**
     * 部件行为--submit
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public WFSubmitAction?: any;

    /**
     * 部件行为--start
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public WFStartAction?: any;

    /**
     * 部件行为--update
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public updateAction: any;

    /**
     * 值规则
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public rules: any = {}

    /**
      * 关系界面计数器
      *
      * @type {number}
      * @memberof EditMobFormControlBase
      */
    public drcounter: number = 0;

    /**
      * 需要等待关系界面保存时，第一次调用save参数的备份
      *
      * @type {number}
      * @memberof EditMobFormControlBase
      */
    public drsaveopt: any = {};

    /**
      * 表单保存回调存储对象
      *
      * @type {any}
      * @memberof EditMobFormControlBase
      */
    public saveState: any;

    /**
     * 部件行为--remove
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public removeAction: any;

    /**
     * 部件行为--create
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public createAction?: any;

    /**
     * 部件行为--create
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public searchAction?: any;

    /**
     * 主键表单项名称
     *
     * @protected
     * @type {string}
     * @memberof MobFormControlBase
     */
    public majorKeyItemName: string = "";

    /**
     * 主信息属性映射表单项名称
     *
     * @type {string}
     * @memberof MobFormControlBase
     */
    public majorMessageItemName: string = "";


    /**
     * 表单状态
     *
     * @type {Subject<any>}
     * @memberof MobFormControlBase
     */
    public formState!: Subject<any>;

    /**
     * 数据变化
     *
     * @public
     * @type {Subject<any>}
     * @memberof MobFormControlBase
     */
    public dataChange!: Subject<any>;
    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public data: any = {};

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public detailsModel: any;

    /**
     * 原始数据
     *
     * @private
     * @type {*}
     * @memberof MobFormControlBase
     */
    private oldData: any = {};

    /**
     * 部件行为--load
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public loadAction: any;

    /**
     * 部件行为--loaddraft
     *
     * @type {*}
     * @memberof MobFormControlBase
     */
    public loaddraftAction: any;

    /**
     * 忽略表单项值变化
     *
     * @type {boolean}
     * @memberof MobFormControlBase
     */
    public ignorefieldvaluechange?: boolean;

    /**
      * 异常信息缓存
      *
      * @type {any}
      * @memberof MobFormControlBase
      */
    public errorCache: any = {};

    /**
      * 当前执行的行为逻辑
      *
      * @type {string}
      * @memberof MobFormControlBase
      */
    protected currentAction: string = "";

    /**
     * 编辑表单初始化
     *
     * @memberof MobFormControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        // 初始化默认值
        this.formState = new Subject();
        this.dataChange = new Subject();
        this.ignorefieldvaluechange = false;
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: { tag: string, action: string, data: any }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('autoload', action)) {
                    this.autoLoad(data);
                }
                if (Object.is('load', action)) {
                    this.load(data);
                }
                if (Object.is('loaddraft', action)) {
                    this.loadDraft(data);
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
            });
        }
        this.dataChange
            .pipe(
                debounceTime(300),
                distinctUntilChanged()
            ).subscribe((data: any) => {
                // if (this.autosave) {
                //     this.autoSave();
                // }
                const state = !Object.is(JSON.stringify(this.oldData), JSON.stringify(this.data)) ? true : false;
                this.$store.commit('viewaction/setViewDataChange', { viewtag: this.viewtag, viewdatachange: state });
            });
    }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MobFormControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isAutoLoad = this.staticProps.isAutoLoad;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof MobFormControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        if (!(this.Environment?.isPreviewMode)) {
            this.service = new AppMobFormService(this.controlInstance);
            await this.service.loaded();
        }
        this.isAutoSave = this.controlInstance.enableAutoSave;
        this.loaddraftAction = this.controlInstance.getGetDraftPSControlAction?.()?.actionName;
        this.updateAction = this.controlInstance.getUpdatePSControlAction?.()?.actionName;
        this.removeAction = this.controlInstance.getRemovePSControlAction?.()?.actionName;
        this.loadAction = this.controlInstance.getGetPSControlAction?.()?.actionName;
        this.createAction = this.controlInstance.getCreatePSControlAction?.()?.actionName;
        this.WFSubmitAction = (this.controlInstance as any).getWFSubmitPSControlAction?.()?.actionName;
        this.WFStartAction = (this.controlInstance as any).getWFStartPSControlAction?.()?.actionName;
        // 初始化data
        this.controlInstance.getPSDEFormItems()?.forEach((formItem: IPSDEFormItem) => {
            this.$set(this.data, formItem.id, null);
        });
        // // 初始化表单成员运行时模型
        this.initDetailsModel();
        // 初始化静态值规则
        this.initRules();
    }

    /**
     * 初始化表单成员模型
     *
     * @memberof MobFormControlBase
     */
    public initDetailsModel() {
        this.detailsModel = {};
        const { noTabHeader, name } = this.controlInstance;
        const allFormDetails: IPSDEFormDetail[] = ModelTool.getAllFormDetails(this.controlInstance);
        if (allFormDetails.length > 0) {
            for (const detail of allFormDetails) {
                let detailOpts: any = {
                    name: detail.name,
                    caption: detail.caption,
                    isShowCaption: detail.showCaption,
                    detailType: detail.detailType,
                    visible: !ModelTool.findGroupLogicByLogicCat('PANELVISIBLE', detail.getPSDEFDGroupLogics()),
                    form: this,
                    isControlledContent: detail.showMoreMode == 1,
                };
                let detailModel: any = null;
                switch (detail.detailType) {
                    case 'BUTTON':
                        Object.assign(detailOpts, {
                            disabled: false,
                        });
                        const uiAction = (detail as IPSDEFormButton).getPSUIAction?.();
                        if (uiAction) {
                            detailOpts.uiaction = {
                                type: uiAction.uIActionType,
                                tag: uiAction.uIActionTag,
                                visabled: true,
                                disabled: false,
                            }
                            if (uiAction.actionTarget) {
                                detailOpts.uiaction.actiontarget = uiAction.actionTarget;
                            }
                            if ((uiAction as IPSAppDEUIAction).noPrivDisplayMode) {
                                detailOpts.uiaction.noprivdisplaymode = (uiAction as IPSAppDEUIAction).noPrivDisplayMode;
                            }
                            if (uiAction.dataAccessAction) {
                                detailOpts.uiaction.dataaccaction = uiAction.dataAccessAction;
                            }
                        }
                        detailModel = new FormButtonModel(detailOpts);
                        break;
                    case 'FORMITEM':
                        Object.assign(detailOpts, {
                            disabled: false,
                            required: !(detail as IPSDEFormItem).allowEmpty,
                            enableCond: (detail as IPSDEFormItem).enableCond,
                        });
                        detailModel = new FormItemModel(detailOpts);
                        break;
                    case 'GROUPPANEL':
                        detailOpts.isManageContainer = detail.showMoreMode == 2;
                        const PSUIActionGroup = (detail as IPSDEFormGroupPanel).getPSUIActionGroup();
                        // 界面行为组
                        let uiActionGroup: any = {
                            caption: PSUIActionGroup?.name,
                            langbase: '',
                            extractMode: (detail as IPSDEFormGroupPanel).actionGroupExtractMode || 'ITEM',
                            details: [],
                        }
                        const PSUIActionGroupDetails = PSUIActionGroup?.getPSUIActionGroupDetails?.() || [];
                        if (PSUIActionGroupDetails.length > 0) {
                            PSUIActionGroupDetails.forEach((actionDetail: any) => {
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
                        // todo
                        (detail as IPSDEFormGroupPanel).getPSDEFormDetails()?.forEach((item: IPSDEFormDetail, index: number) => {
                            if (!item) return;
                            if (item.showMoreMode == 1) {
                                showMoreModeItems.push(item.name);
                            }
                            if ((item as any).enableAnchor) {
                                anchorPoints.push({
                                    name: item.name,
                                    editor: (item as IPSDEFormItem).getPSEditor() || {}
                                });
                            }
                            const showMore = item.getShowMoreMgrPSDEFormDetail?.();
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
                        (detail as IPSDEFormTabPanel).getPSDEFormTabPages()?.forEach((item: IPSDEFormTabPage, index: number) => {
                            tabPages.push({
                                name: item.name,
                                index: index,
                                visible: !ModelTool.findGroupLogicByLogicCat('PANELVISIBLE', detail.getPSDEFDGroupLogics())
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
                        // this.drCount++;
                        detailModel = new FormDruipartModel(detailOpts);
                        break;
                    case 'IFRAME':
                        detailModel = new FormIFrameModel(detailOpts);
                        break;
                    case 'RAWITEM':
                        // detailModel = new FormRawItemModel(detailOpts);
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
            this.controlInstance.getPSDEFormPages()?.forEach((item: IPSDEFormPage, index: number) => {
                formPages.push({
                    name: item.name,
                    index: index,
                    visible: !ModelTool.findGroupLogicByLogicCat('PANELVISIBLE', item.getPSDEFDGroupLogics())
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
     * 自动加载
     *
     * @param {*} [arg={}]
     * @returns {void}
     * @memberof MobFormControlBase
     */
    protected autoLoad(arg: any = {}): void {
        if (arg.srfkey && !Object.is(arg.srfkey, '')) {
            Object.assign(arg, { srfkey: arg.srfkey });
            this.load(arg);
            return;
        }
        if (arg.srfkeys && !Object.is(arg.srfkeys, '')) {
            Object.assign(arg, { srfkey: arg.srfkeys });
            this.load(arg);
            return;
        }
        this.loadDraft(arg);
    }

    /**
     * 加载
     *
     * @private
     * @param {*} [opt={}]
     * @returns {Promise<any>}
     * @memberof MobFormControlBase
     */
    public async load(opt: any = {}): Promise<any> {
        const { codeName } = this.controlInstance;
        if (!this.loadAction) {
            this.$Notice.error(codeName + this.$t('app.view') + this.$t('app.ctrl.form') + 'loadAction' + this.$t('app.notConfig'));
            return Promise.reject();
        }
        this.ctrlBeginLoading();
        const arg: any = { ...opt };
        Object.assign(arg, this.viewparams);
        const response: any = await this.service.get(this.loadAction, { ...this.context }, arg, this.showBusyIndicator);
        if (response && response.status === 200) {
            this.endLoading();
            const data = response.data;
            this.onFormLoad(data, 'load');
            this.ctrlEvent({
                controlname: this.controlInstance.name,
                action: 'load',
                data: data,
            });
            this.$nextTick(() => {
                this.formState.next({ type: 'load', data: data });
            });
        } else if (response && response.status !== 401) {
            this.endLoading();
            const { error: _data } = response;
            this.$Notice.error(_data.message);
        }
        return response;
    }

    /**
     * 加载草稿
     *
     * @param {*} [opt={}]
     * @memberof MobFormControlBase
     */
    protected async loadDraft(opt: any = {}): Promise<any> {
        const { codeName } = this.controlInstance;
        if (!this.loaddraftAction) {
            this.$Notice.error(codeName + this.$t('app.view') + this.$t('app.ctrl.form') + 'loaddraftAction' + this.$t('app.notConfig'));
            return Promise.reject();
        }
        const arg: any = { ...opt };
        Object.assign(arg, this.viewparams);
        const response: any = await this.service.loadDraft(this.loaddraftAction, { ...this.context }, arg, this.showBusyIndicator);
        if (response && response.status === 200) {
            const data = response.data;
            if (this.appDeCodeName?.toLowerCase() && data[this.appDeCodeName.toLowerCase()]) {
                Object.assign(this.context, { [this.appDeCodeName.toLowerCase()]: data[this.appDeCodeName.toLowerCase()] });
            }
            this.resetDraftFormStates();
            this.onFormLoad(data, 'loadDraft');
            this.ctrlEvent({
                controlname: this.controlInstance.name,
                action: 'load',
                data: data,
            });
            this.$nextTick(() => {
                this.formState.next({ type: 'load', data: data });
            });
        } else if (response && response.status !== 401) {
            const { error: _data } = response;
            this.$Notice.error(_data.message);
        }

        return response;
    }


    /**
     * 表单加载完成
     *
     * @private
     * @param {*} [data={}]
     * @param {string} [action]
     * @memberof MobFormControlBase
     */
    public onFormLoad(data: any = {}, action: string): void {
        this.setFormEnableCond(data);
        this.fillForm(data, action);
        this.oldData = {};
        Object.assign(this.oldData, JSON.parse(JSON.stringify(this.data)));
        this.$store.commit('viewaction/setViewDataChange', { viewtag: this.viewtag, viewdatachange: false });
        this.formLogic({ name: '', newVal: null, oldVal: null });
    }

    /**
     * 设置表单项是否启用
     *
     * @public
     * @param {*} data
     * @memberof MobFormControlBase
     */
    public setFormEnableCond(data: any): void {
        Object.values(this.detailsModel).forEach((detail: any) => {
            if (!Object.is(detail?.detailType, 'FORMITEM')) {
                return;
            }
            const formItem: any = detail;
            formItem.setEnableCond(data.srfuf);
        });
    }

    /**
     * 值填充
     *
     * @param {*} [_datas={}]
     * @param {string} [action]
     * @memberof MobFormControlBase
     */
    protected fillForm(_datas: any = {}, action: string): void {
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
     * 新建默认值
     *
     * @memberof MobFormControlBase
     */
    public createDefault() {
        const allFormDetails: IPSDEEditFormItem[] = ModelTool.getAllFormItems(this.controlInstance);
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
                            this.data[property] = ModelTool.isNumberField(detail?.getPSAppDEField()) ? Number(detail?.createDV) : detail?.createDV;
                            break;
                    }
                }
            }
        }
    }

    /**
     * 更新默认值
     *
     * @memberof MobFormControlBase
     */
    public updateDefault() {
        const allFormDetails: IPSDEEditFormItem[] = ModelTool.getAllFormItems(this.controlInstance);
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
                            this.data[property] = ModelTool.isNumberField(detail?.getPSAppDEField()) ? Number(detail?.updateDV) : detail?.updateDV;
                            break;
                    }
                }
            }
        }
    }

    /**
     * 重置草稿表单状态
     *
     * @public
     * @memberof MobFormControlBase
     */
    public resetDraftFormStates(): void {
        const form: any = this.$refs[this.controlInstance.name];
        if (form) {
            form.resetFields();
        }
    }

    /**
     * 重置表单项值
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof AppDefaultMobForm
     */
    public resetFormData({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void {
        const formItems = this.controlInstance.getPSDEFormItems();
        if (formItems && formItems.length > 0) {
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
     * 表单项值变更
     *
     * @param {{ name: string, value: any }} $event
     * @returns {void}
     * @memberof MobFormControlBase
     */
    public onFormItemValueChange($event: { name: string, value: any }) {
        if (!$event) {
            return;
        }
        if (!$event.name || Object.is($event.name, '') || !this.data.hasOwnProperty($event.name)) {
            return;
        }
        this.data[$event.name] = $event.value;
        this.formDataChange({ name: $event.name, newVal: $event.value, oldVal: null })
    }

    /**
     * 表单值变化
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @returns {void}
     * @memberof MobFormControlBase
     */
    public formDataChange({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void {
        if (this.ignorefieldvaluechange) {
            return;
        }
        this.resetFormData({ name: name, newVal: newVal, oldVal: oldVal });
        this.formLogic({ name: name, newVal: newVal, oldVal: oldVal });
        this.dataChange.next(JSON.stringify(this.data));

        this.validItem(name, this.data);
    }

    /**
     * 校验动态逻辑结果
     *
     * @param {*} data 数据对象
     * @param {*} logic 逻辑对象
     * @returns
     * @memberof EditMobFormControlBase
     */
    public verifyGroupLogic(data: any, logic: any) {
        if (logic.logicType == 'GROUP' && logic?.getPSDEFDLogics()?.length > 0) {
            let result: boolean = true;
            if (logic.groupOP == 'AND') {
                let falseItem = logic.getPSDEFDLogics().find((childLogic: any) => {
                    return !this.verifyGroupLogic(data, childLogic);
                })
                result = falseItem == undefined;
            } else if (logic.groupOP == 'OR') {
                let trueItem = logic.getPSDEFDLogics().find((childLogic: any) => {
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
     * 表单项更新
     *
     * @param {string} mode 界面行为名称
     * @param {*} [data={}] 请求数据
     * @param {string[]} updateDetails 更新项
     * @param {boolean} [showloading] 是否显示加载状态
     * @returns {void}
     * @memberof MobFormControlBase
     */
    public updateFormItems(mode: string, data: any = {}, updateDetails: string[], showloading: boolean = false): void {
        if (!mode || (mode && Object.is(mode, ''))) {
            return;
        }
        const arg: any = { ...data };
        Object.assign(arg, this.viewparams);
        const post: Promise<any> = this.service.frontLogic(mode, JSON.parse(JSON.stringify(this.context)), arg, showloading);
        post.then((response: any) => {
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
            this.dataChange.next(JSON.stringify(this.data));
            this.$nextTick(() => {
                this.formState.next({ type: 'updateformitem', ufimode: arg.srfufimode, data: _data });
            });
        }).catch((response: any) => {
            const { error: _data } = response;
            if (Object.is(_data.status, 'BAD_REQUEST') && _data.parameters && _data.parameters.fieldErrors) {
                this.resetValidates();
                this.fillValidates(_data.parameters.fieldErrors)
            }
            this.$Notice.error(_data.message);
        });
    }

    /**
     * 重置校验结果
     *
     * @memberof MobFormControlBase
     */
    protected resetValidates(): void {
        Object.values(this.detailsModel).forEach((detail: any) => {
            if (!Object.is(detail.detailType, 'FORMITEM')) {
                return;
            }
            const formItem: FormItemModel = detail;
            formItem.setError('');
        });
    }

    /**
     * 填充校验结果 （后台）
     *
     * @param {any[]} fieldErrors
     * @memberof MobFormControlBase
     */
    protected fillValidates(fieldErrors: any[]): void {
        fieldErrors.forEach((error: any) => {
            const formItem: FormItemModel = this.detailsModel[error.field];
            if (!formItem) {
                return;
            }
            this.$nextTick(() => {
                formItem.setError(error.message);
            });
        });
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof MobFormControlBase
     */
    public getData(): any {
        return this.data;
    }

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof MobFormControlBase
     */
    public getDatas(): any[] {
        return [this.data];
    }

    /**
     * 表单逻辑
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof MobFormControlBase
     */
    public async formLogic({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }) {
        const allFormDetails: IPSDEFormDetail[] = ModelTool.getAllFormDetails(this.controlInstance);
        // 表单动态逻辑
        allFormDetails?.forEach((detail: IPSDEFormDetail) => {
            detail.getPSDEFDGroupLogics()?.forEach((logic: IPSDEFDCatGroupLogic) => {
                // todo lxm 缺少getRelatedDetailNames
                let relatedNames = logic.getRelatedDetailNames() || [];
                if (Object.is(name, '') || relatedNames.indexOf(name) != -1) {
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
        let formDetail: any = ModelTool.getFormDetailByName(this.controlInstance, name);
        if (formDetail?.formItemUpdate) {
            const { getPSAppDEMethod, getPSDEFIUpdateDetails, showBusyIndicator } = formDetail.formItemUpdate;
            let details: string[] = [];
            getPSDEFIUpdateDetails?.forEach((item: any) => {
                details.push(item.name)
            })
            if (await this.validItem(formDetail.name, this.data)) {
                this.updateFormItems(getPSAppDEMethod?.id, this.data, details, showBusyIndicator);
            }
        }
    }

    /**
     * 初始化值规则
     *
     * @memberof MobFormControlBase
     */
    public initRules() {
        // 先初始化系统值规则和属性值规则
        let staticRules: any = {};
        const allFormItemVRs = this.controlInstance.getPSDEFormItemVRs();
        allFormItemVRs?.forEach((item: IPSDEFormItemVR) => {
            const { checkMode, valueRuleType } = item;
            const formItemName = item.getPSDEFormItemName() || '';
            const sysRule = item.getPSSysValueRule();
            const deRule = item.getPSDEFValueRule();
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
                let formItem = this.controlInstance.findPSDEFormItem(formItemName);
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
        const allFormItems = ModelTool.getAllFormItems(this.controlInstance) as IPSDEEditFormItem[];
        if (allFormItems && allFormItems.length > 0) {
            for (const detail of allFormItems) {
                if (detail.detailType == 'FORMITEM' && detail.getPSEditor()?.editorType != 'HIDDEN' && !detail.compositeItem) {
                    let type = ModelTool.isNumberField(detail?.getPSAppDEField()) ? 'number' : 'string';
                    let otherRules = staticRules[detail.name] || [];
                    let editorRules = Verify.buildVerConditions(detail.getPSEditor())
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
     * 表单项校验逻辑
     *
     * @public
     * @param name 属性名
     * @memberof MobFormControlBase
     */
    public validItem(property: string, data: any): Promise<boolean> {
        return new Promise((resolve: any, reject: any) => {
            if (!this.rules[property]) {
                resolve(true)
            }
            const scheam = new schema({ [property]: this.rules[property] });
            scheam.validate({ [property]: data[property] }).then((error: any) => {
                this.detailsModel[property].error = '';
                this.$forceUpdate();
                resolve(true)
            }).catch(({ errors, fields }: any) => {
                console.log(errors, fields);
                const { field, message } = errors[0];
                this.detailsModel[field].error = message;
                this.$forceUpdate();
                resolve(false)
            })
        })
    }

    /**
     * 校验全部
     *
     * @public
     * @param {{ filter: string}} { filter}
     * @returns {void}
     * @memberof MobFormControlBase
     */
    public async validAll(filter: string = "defult"): Promise<boolean> {
        let filterProperty = ""
        if (filter === 'new') {
            filterProperty = this.appDeKeyFieldName?.toLowerCase();
        }
        return new Promise((resolve: any, reject: any) => {
            const scheam = new schema(this.rules);
            scheam.validate(this.data).then((response: any) => {
                for (const key in this.detailsModel) {
                    if (Object.prototype.hasOwnProperty.call(this.detailsModel, key)) {
                        const model = this.detailsModel[key];
                        model.error = "";
                    }
                }
                this.$forceUpdate();
                resolve(true);
            }).catch(({ errors, fields }: any) => {
                if (!errors) {
                    resolve(true);
                }
                let errorMessage = ""
                for (let index = 0; index < errors.length; index++) {

                    const error = errors[index];
                    const { field, message } = error;
                    this.detailsModel[field].error = message;
                    errorMessage = !index ? message : errorMessage;
                }
                this.$Notice.error(errorMessage);
                this.$forceUpdate();
                resolve(false)
            })
        })
    }


    /**
     * 保存
     *
     * @param {*} [opt={}]
     * @param {boolean} [showResultInfo] 
     * @param {boolean} [isStateNext] formState是否下发通知
     * @returns {Promise<any>}
     * @memberof MobFormControlBase
     */
    public async save(opt: any = {}, showResultInfo?: boolean, isStateNext: boolean = true): Promise<any> {
        showResultInfo = showResultInfo === undefined ? true : false;
        if (this.data.srfuf == '1' ? !await this.validAll() : !await this.validAll('new')) {
            return Promise.reject();
        }
        return new Promise((resolve: any, reject: any) => {
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
                this.$Notice.error(`${this.controlInstance.codeName}` + (this.$t('app.formpage.notconfig.actionname') as string));
                return;
            }
            Object.assign(arg, { viewparams: this.viewparams });
            const post: Promise<any> = Object.is(data.srfuf, '1') ? this.service.update(action, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator) : this.service.add(action, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
            post.then((response: any) => {
                if (!response.status || response.status !== 200) {
                    if (response.data) {
                        this.$Notice.error(response.data.message);
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
                AppCenterService.notifyMessage({ name: this.appDeCodeName, action: 'appRefresh', data: data });
                this.$nextTick(() => {
                    this.formState.next({ type: 'save', data: data });
                });
                if (this.controlInstance.formFuncMode?.toLowerCase() != 'wizardform' && showResultInfo) {
                    this.$Notice.success((data.srfmajortext ? data.srfmajortext : '') + '&nbsp;' + (this.$t('app.formpage.savesuccess') as string));
                }
                resolve(response);
            }).catch((response: any) => {
                if (response && response.status && response.data) {
                    if (response.data.errorKey) {
                        if (Object.is(response.data.errorKey, "versionCheck")) {
                            this.$Notice.confirm((this.$t('app.formpage.saveerror') as string), (this.$t('app.formpage.savecontent') as string)).then(() => {
                                this.refresh();
                            });
                        } else if (Object.is(response.data.errorKey, 'DupCheck')) {
                            let errorProp: string = response.data.message.match(/\[[a-zA-Z]*\]/)[0];
                            let name: string = this.service.getNameByProp(errorProp.substr(1, errorProp.length - 2));
                            if (name) {
                                this.$Notice.error(
                                    this.detailsModel[name].caption + " : " + arg[name] + (this.$t('app.commonWords.isExist') as string) + '!'
                                );
                            } else {
                                this.$Notice.error(
                                    response.data.message,
                                )
                            }
                        } else {
                            this.$Notice.error(response.data.message);
                        }
                    } else {
                        this.$Notice.error(response.data.message);
                        reject(response);
                    }
                    return;
                } else {
                    this.$Notice.error('系统异常');
                    reject(response);
                }
                reject(response);
            });
        })
    }

    /**
     * 删除
     *
     * @private
     * @param {Array<any>} [opt=[]]
     * @param {boolean} [showResultInfo]
     * @returns {Promise<any>}
     * @memberof MobFormControlBase
     */
    private async remove(opt: Array<any> = [], showResultInfo?: boolean): Promise<any> {
        if (!this.removeAction) {
            const view = this.controlInstance.getParentPSModelObject();
            this.$Notice.error(view?.name + this.$t('app.view') + this.$t('app.ctrl.form') + 'removeAction' + this.$t('app.notConfig'));
            return Promise.reject();
        }
        const arg: any = opt[0];
        const _this: any = this;
        Object.assign(arg, this.viewparams);
        const response: any = await this.service.delete(_this.removeAction, { ...this.context }, arg, showResultInfo);
        if (response && response.status === 200) {
            const data = response.data;
            this.ctrlEvent({
                controlname: this.controlInstance.name,
                action: 'remove',
                data: data,
            });
            this.formState.next({ type: 'remove', data: data });
            this.data.ismodify = false;
            AppCenterService.notifyMessage({ name: this.appDeCodeName, action: 'appRefresh', data: data });
            this.$Notice.success((data.srfmajortext ? data.srfmajortext : '') + '&nbsp;' + this.$t('app.message.deleteSccess'));
        } else if (response && response.status !== 401) {
            const { error: _data } = response;
            this.$Notice.error(_data.message);
        }
        return response;
    }

    /**
     * 保存并退出
     *
     * @protected
     * @param {any[]} data
     * @returns {Promise<any>}
     * @memberof MobFormControlBase
     */
    protected async saveAndExit(data: any[]): Promise<any> {
        let arg: any = {};
        if (data && data.length > 0) {
            Object.assign(arg, data[0]);
        }
        this.currentAction = 'saveAndExit';
        const response: any = await this.save([arg]);
        if (response && response.status === 200) {
            this.closeView([{ ...response.data }]);
        }
        return response;
    }

    /**
      * 置空对象
      *
      * @param {any[]} args
      * @memberof MobFormControlBase
      */
    protected ResetData(_datas: any) {
        if (Object.keys(_datas).length > 0) {
            Object.keys(_datas).forEach((name: string) => {
                if (this.data.hasOwnProperty(name)) {
                    this.data[name] = null;
                }
            });
        }
    }

    /**
     * 保存并新建
     *
     * @protected
     * @param {any[]} data
     * @returns {Promise<any>}
     * @memberof MobFormControlBase
     */
    protected async saveAndNew(data: any[]): Promise<any> {
        let arg: any = { ...data[0] };
        this.currentAction = 'saveAndNew';
        const response: any = await this.save([arg]);
        if (response && response.status === 200) {
            this.ResetData(response.data);
            this.loadDraft({});
        }
        return response;
    }

    /**
     * 删除并退出
     *
     * @protected
     * @param {any[]} data
     * @returns {Promise<any>}
     * @memberof MobFormControlBase
     */
    protected async removeAndExit(data: any[]): Promise<any> {
        let arg: any = { ...data[0] };
        const response: any = await this.remove([arg]);
        if (response && response.status === 200) {
            this.closeView([{ ...response.data }]);
        }
        return response;
    }

    /**
     * 部件刷新
     *
     * @param {any[]} args
     * @memberof MobFormControlBase
     */
    public refresh(args?: any[]): void {
        let arg: any = {};
        if (args) {
            Object.assign(arg, args[0]);
        }
        if (this.data.srfkey && !Object.is(this.data.srfkey, '')) {
            Object.assign(arg, { srfkey: this.data.srfkey });
            this.load(arg);
            return;
        }
        if (this.data.srfkeys && !Object.is(this.data.srfkeys, '')) {
            Object.assign(arg, { srfkey: this.data.srfkeys });
            this.load(arg);
            return;
        }
    }

    /**
    * 关系界面数据保存完成
    *
    * @param {any} $event
    * @memberof MobFormControlBase
    */
    protected drdatasaved($event: any) {
        let _this = this;
        this.drcounter--;
        if (this.drcounter > 0) {
            return;
        }
        this.save({}, undefined, false).then((res) => {
            this.saveState(res);
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
