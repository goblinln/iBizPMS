import { IPSControlHandler } from '@ibiz/dynamic-model-api';
import { EditFormControlBase } from './editform-control-base';

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
    }

    /**
     * 部件创建完毕
     *
     * @memberof SearchFormControlBase
     */
    public ctrlInit(): void {
        super.ctrlInit();
    }

    /**
     * 处理dataChang下发的事件
     *
     * @memberof SearchFormControlBase
     */
    public handleDataChange(){
        if (this.isAutoSave) {
            this.autoSave();
        }
        this.ctrlEvent({
            controlname: this.name,
            action: 'load',
            data: this.data,
        });
    }

    /**
     * 加载草稿
     *
     * @param {*} [opt={}]
     * @memberof SearchFormControlBase
     */
    public loadDraft(opt: any = {},mode?:string): void {
        if(!this.loaddraftAction){
            this.$throw('视图' + (this.$t('app.searchForm.notConfig.loaddraftAction') as string));
            return;
        }
        const arg: any = { ...opt } ;
        Object.assign(arg,{viewparams:this.viewparams});
        let post: Promise<any> = this.service.loadDraft(this.loaddraftAction,JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                this.$throw(response);
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
            this.$throw(response);
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
     * 重置
     *
     * @memberof SearchFormControlBase
     */
    public onReset() {
        this.loadDraft({},'RESET');
    }
}
