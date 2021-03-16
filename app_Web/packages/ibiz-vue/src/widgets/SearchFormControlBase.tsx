import { EditFormControlBase } from './EditFormControlBase';

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
        this.isExpandSearchForm = newVal.isExpandSearchForm;
        super.onDynamicPropsChange(newVal, oldVal);
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
     * @memberof EditFormControlBase
     */
    public handleDataChange(){
        if (this.isAutoSave) {
            this.autoSave();
        }
        this.ctrlEvent({
            controlname: this.controlInstance.name,
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
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '视图' + (this.$t('app.searchForm.notConfig.loaddraftAction') as string) });
            return;
        }
        const arg: any = { ...opt } ;
        Object.assign(arg,{viewparams:this.viewparams});
        let post: Promise<any> = this.service.loadDraft(this.loaddraftAction,JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        post.then((response: any) => {
            if (!response.status || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
                }
                return;
            }

            const data = response.data;
            this.resetDraftFormStates();
            this.onFormLoad(data,'loadDraft');
            setTimeout(() => {
                const form: any = this.$refs[this.controlInstance.name];
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
                controlname: this.controlInstance.name,
                action: 'load',
                data: data,
            });
            this.$nextTick(() => {
                this.formState.next({ type: 'load', data: data });
            });
        }).catch((response: any) => {
            if (response && response.status === 401) {
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                return;
            }

            const { data: _data } = response;
            this.$Notice.error({ title: _data.title, desc: _data.message });
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
            controlname: this.controlInstance.name,
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
            controlname: this.controlInstance.name,
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
            controlname: this.controlInstance.name,
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
