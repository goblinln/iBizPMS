import { IPSControlHandler } from '@ibiz/dynamic-model-api';
import { MobSearchFormControlInterface } from 'ibiz-core';
import { MobFormControlBase } from './mob-form-control-base';

/**
 * 搜索表单部件基类
 *
 * @export
 * @class SearchFormControlBase
 * @extends {EditFormControlBase}
 */
export class MobSearchFormControlBase extends MobFormControlBase implements MobSearchFormControlInterface{

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
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isExpandSearchForm = newVal.isExpandSearchForm;
        super.onStaticPropsChange(newVal, oldVal);
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
    public handleDataChange() {
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
    public async loadDraft(opt: any = {}, mode?: string): Promise<any> {
        if (!this.loaddraftAction) {
            this.$Notice.error(`${this.$t('app.view')} ${this.$t('app.ctrl.form')} loaddraftAction ${this.$t('app.notConfig')}`);
            return Promise.reject();
        }
        const arg: any = { ...opt };
        Object.assign(arg, this.viewparams);
        const response: any = await this.service.loadDraft(this.loaddraftAction, { ...this.context }, arg, this.showBusyIndicator);
        if (response && response.status === 200) {
            const data = response.data;
            if (this.appDeCodeName?.toLowerCase() && data[this.appDeCodeName.toLowerCase()]) {
                Object.assign(this.context, { [this.appDeCodeName.toLowerCase()]: data[this.appDeCodeName.codeName.toLowerCase()] });
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
        }
        return response;
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
     * 重置
     *
     * @memberof SearchFormControlBase
     */
    public onReset() {
        this.loadDraft({}, 'RESET');
    }
}
