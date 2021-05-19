import { IPSApplication, IPSAppUtil, IPSControlHandler } from '@ibiz/dynamic-model-api';
import { EditFormControlBase } from './editform-control-base';
import moment from 'moment';
import { GetModelService, LogUtil } from 'ibiz-core';
/**
 * 搜索表单部件基类
 *
 * @export
 * @class SearchFormControlBase
 * @extends {EditFormControlBase}
 */
export class SearchFormControlBase extends EditFormControlBase {

    /**
     * 是否展开搜索表单
     *
     * @type {*}
     * @memberof SearchFormControlBase
     */
    public isExpandSearchForm: any = false;

    /**
     * 存储项名称
     * 
     * @type {string}
     * @memberof SearchFormControlBase
     */
    public saveItemName: string = '';

    /**
     * 历史记录
     * 
     * @type {any[]}
     * @memberof SearchFormControlBase
     */
    protected historyItems: any[] = [];

    /**
     * 选中记录
     * 
     * @type {any}
     * @memberof SearchFormControlBase
     */
    protected selectItem: any = null;

    /**
     * 模型id
     * 
     * @type {any}
     * @memberof SearchFormControlBase
     */
    public modelId: string = "";

    /**
     * 功能服务名称
     * 
     * @type {any}
     * @memberof SearchFormControlBase
     */
    public utilServiceName: string = "";

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof SearchFormControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        this.isExpandSearchForm = newVal?.isExpandSearchForm;
        super.onDynamicPropsChange(newVal, oldVal);
    }

    /**
     * 初始化搜索表单模型
     *
     * @memberof SearchFormControlBase
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        this.loaddraftAction = (this.controlInstance.getPSControlHandler() as IPSControlHandler)?.findPSControlHandlerAction('loaddraft')?.getPSAppDEMethod?.()?.codeName || 'GetDraft';
        this.loadAction = (this.controlInstance.getPSControlHandler() as IPSControlHandler)?.findPSControlHandlerAction('load')?.getPSAppDEMethod?.()?.codeName || 'Load';
        this.modelId = `searchform_${this.appDeCodeName ? this.appDeCodeName.toLowerCase() : 'app'}_${this.controlInstance.codeName.toLowerCase()}`;
        await this.initUtilService();
    }

    /**
     * 初始化功能服务名称
     *
     * @memberof SearchFormControlBase
     */
    public async initUtilService() {
        const appUtil: IPSAppUtil = ((await (await GetModelService(this.context))?.app as IPSApplication).getAllPSAppUtils() || []).find((util: any) => {
            return util.utilType == 'FILTERSTORAGE';
        }) as IPSAppUtil;
        if (appUtil) {
            this.utilServiceName = appUtil.codeName?.toLowerCase();
        }
        this.utilServiceName = "dynafilter";
    }

    /**
     * 部件创建完毕
     *
     * @memberof SearchFormControlBase
     */
    public ctrlInit(): void {
        super.ctrlInit();
        this.loadModel();
    }

    public loadModel() {
        let param: any = {};
        Object.assign(param, {
            appdeName: this.appDeCodeName,
            modelid: this.modelId,
            utilServiceName: this.utilServiceName,
            ...this.viewparams
        });
        let post = this.service.loadModel(this.utilServiceName, this.context, param);
        this.ctrlBeginLoading();
		post.then((response: any) => {
            this.ctrlEndLoading();
			if(response.status == 200 && response.data) {
                this.historyItems = response.data;
			}
		}).catch((response: any) => {
            this.ctrlEndLoading();
			LogUtil.log(response);
		});
    }

    /**
     * 处理dataChang下发的事件
     *
     * @memberof SearchFormControlBase
     */
    public handleDataChange(){
        if (this.isAutoSave) {
            this.ctrlEvent({
                controlname: this.name,
                action: 'load',
                data: this.data,
            });
        }
    }

    /**
     * 加载草稿
     *
     * @param {*} [opt={}]
     * @memberof SearchFormControlBase
     */
    public loadDraft(opt: any = {},mode?:string): void {
        if(!this.loaddraftAction){
            this.$throw('视图' + (this.$t('app.searchForm.notConfig.loaddraftAction') as string),'loadDraft');
            return;
        }
        const arg: any = { ...opt } ;
        Object.assign(arg,{viewparams:this.viewparams});
        let post: Promise<any> = this.service.loadDraft(this.loaddraftAction,JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                this.$throw(response,'loadDraft');
                return;
            }

            const data = response.data;
            this.resetDraftFormStates();
            this.onFormLoad(data,'loadDraft');
            setTimeout(() => {
                const form: any = this.$refs[this.name];
                if (form) {
                    form.fields.forEach((field: any) => {
                        field.validateMessage = "";
                        field.validateState = "";
                        field.validateStatus = false;
                    });
                }
            });
            if(Object.is(mode,'RESET')){
                if (!this.formValidateStatus()) {
                    return;
                }
            }
            this.ctrlEvent({
                controlname: this.name,
                action: 'load',
                data: data,
            });
            this.$nextTick(() => {
                this.formState.next({ type: 'load', data: data });
            });
        }).catch((response: any) => {
            this.ctrlEndLoading();
            this.$throw(response,'loadDraft');
        });
    }

    /**
     * 表单值变化
     *
     * @param {{ name: string, newVal: any, oldVal: any }} param
     * @memberof SearchFormControlBase
     */
    public formDataChange(param: { name: string; newVal: any; oldVal: any }): void {
        super.formDataChange(param);
        this.ctrlEvent({
            controlname: this.name,
            action: 'valuechange',
            data: this.data,
        });
    }

    /**
     * 表单加载完成
     *
     * @param {*} [data={}]
     * @param {string} action
     * @memberof SearchFormControlBase
     */
    public onFormLoad(data: any = {}, action: string): void {
        this.setFormEnableCond(data);
        this.fillForm(data, action);
        this.formLogic({ name: '', newVal: null, oldVal: null });
    }

    /**
     * 回车事件
     *
     * @param {*} $event
     * @memberof SearchFormControlBase
     */
    public onEnter($event: any): void {
        this.ctrlEvent({
            controlname: this.name,
            action: 'search',
            data: this.data,
        });
    }

    /**
     * 搜索
     *
     * @memberof SearchFormControlBase
     */
    public onSearch() {
        this.ctrlEvent({
            controlname: this.name,
            action: 'search',
            data: this.data,
        });
    }

    /**
     * 确定
     *
     * @return {*}
     * @memberof SearchFormControlBase
     */
    public onOk() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let propip: any = this.$refs.propip;
        propip.handleMouseleave();
        this.onSave(this.saveItemName);
    }

    /**
     * 取消设置
     *
     * @return {*}
     * @memberof SearchFormControlBase
     */
    public onCancel() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let propip: any = this.$refs.propip;
        propip.handleMouseleave();
        // this.onSave();
    }

    /**
     * 保存
     *
     * @return {*}
     * @memberof SearchFormControlBase
     */
    public onSave(name?: string) {
        let time = moment();
        this.historyItems.push({
            name: (name ? name : time.format('YYYY-MM-DD HH:mm:ss')),
            value: time.unix().toString(),
            data: JSON.parse(JSON.stringify(this.data))
        })
        this.selectItem = time.unix().toString();
        let param: any = {};
		Object.assign(param, {
            model: JSON.parse(JSON.stringify(this.historyItems)),
            appdeName: this.appDeCodeName,
            modelid: this.modelId,
            utilServiceName: this.utilServiceName,
			...this.viewparams
		});
		let post = this.service.saveModel(this.utilServiceName, this.context, param);
		post.then((response: any) => {
            this.ctrlEvent({ controlname: this.controlInstance.name, action: "save", data: response.data });
		}).catch((response: any) => {
			LogUtil.log(response);
		});
    }

    /**
     * 改变过滤条件
     *
     * @return {*}
     * @memberof SearchFormControlBase
     */
    public onFilterChange(evt: any) {
        let item: any = this.historyItems.find((item: any) => Object.is(evt, item.value));
        if(item) {
            this.selectItem = item.value;
            this.data = JSON.parse(JSON.stringify(item.data));
        }
    }

    /**
     * 重置
     *
     * @memberof SearchFormControlBase
     */
    public onReset() {
        this.loadDraft({},'RESET');
    }
}
