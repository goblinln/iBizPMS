import { Subject, Subscription } from 'rxjs';
import { ModelTool, Verify } from 'ibiz-core';
import { MainControlBase } from './main-control-base';
import { IPSDEEditFormItem, IPSDEForm } from '@ibiz/dynamic-model-api';

/**
 * 表单部件基类
 *
 * @export
 * @class FormControlBase
 * @extends {MainControlBase}
 */
export class FormControlBase extends MainControlBase {

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof FormControlBase
     */
    public data: any = {};

    /**
     * 表单的模型对象
     *
     * @type {*}
     * @memberof FormControlBase
     */
    public controlInstance!: IPSDEForm;

    /**
     * 表单服务对象
     *
     * @type {*}
     * @memberof ControlBase
     */
    public service !: any;

    /**
     * 部件行为--loaddraft
     *
     * @type {*}
     * @memberof FormControlBase
     */
    public loaddraftAction: any;

    /**
     * 部件行为--load
     *
     * @type {*}
     * @memberof FormControlBase
     */
    public loadAction: any;

    /**
     * 表单状态
     *
     * @type {Subject<any>}
     * @memberof FormControlBase
     */
    public formState!: Subject<any>;

    /**
     * 忽略表单项值变化
     *
     * @type {boolean}
     * @memberof FormControlBase
     */
    public ignorefieldvaluechange?: boolean;

    /**
     * 数据变化
     *
     * @public
     * @type {Subject<any>}
     * @memberof FormControlBase
     */
    public dataChang!: Subject<any>;

    /**
     * 视图状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof FormControlBase
     */
    public dataChangEvent: Subscription | undefined;

    /**
     * 原始数据
     *
     * @public
     * @type {*}
     * @memberof FormControlBase
     */
    public oldData: any;

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof FormControlBase
     */
    public detailsModel: any;

    /**
     * 值规则
     *
     * @type {*}
     * @memberof FormControlBase
     */
    public rules: any = {}

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof FormControlBase
     */
    public getDatas(): any[] {
        return [this.data];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof FormControlBase
     */
    public getData(): any {
        return this.data;
    }

    /**
     * 重置表单项值
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof FormControlBase
     */
    public resetFormData({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void { }

    /**
     * 表单逻辑
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof FormControlBase
     */
    public async formLogic({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }) { }

    /**
     * 表单值变化
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @returns {void}
     * @memberof FormControlBase
     */
    public formDataChange({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void { }


    /**
     * 表单加载完成
     *
     * @public
     * @param {*} [data={}]
     * @param {string} [action]
     * @memberof FormControlBase
     */
    public onFormLoad(data: any = {}, action: string): void { }

    /**
     * 值填充
     *
     * @param {*} [_datas={}]
     * @param {string} [action]
     * @memberof FormControlBase
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
        this.$nextTick(function () {
            this.ignorefieldvaluechange = false;
        })
    }

    /**
     * 设置表单项是否启用
     *
     * @public
     * @param {*} data
     * @memberof FormControlBase
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
     * 新建默认值
     * @memberof FormControlBase
     */
    public createDefault() { }

    /**
     * 重置草稿表单状态
     *
     * @public
     * @memberof FormControlBase
     */
    public resetDraftFormStates(): void {
        const form: any = this.$refs[this.controlInstance.name];
        if (form) {
            form.resetFields();
        }
    }

    /**
     * 重置校验结果
     *
     * @memberof FormControlBase
     */
    public resetValidates(): void {
        Object.values(this.detailsModel).forEach((detail: any) => {
            if (!Object.is(detail.detailType, 'FORMITEM')) {
                return;
            }
            const formItem: any = detail;
            formItem.setError('');
        });
    }

    /**
     * 填充校验结果 （后台）
     *
     * @param {any[]} fieldErrors
     * @memberof FormControlBase
     */
    public fillValidates(fieldErrors: any[]): void {
        fieldErrors.forEach((error: any) => {
            const formItem: any = this.detailsModel[error.field];
            if (!formItem) {
                return;
            }
            this.$nextTick(() => {
                formItem.setError(error.message);
            });
        });
    }

    /**
     * 表单校验状态
     *
     * @returns {boolean} 
     * @memberof FormControlBase
     */
    public formValidateStatus(): boolean {
        const form: any = this.$refs[this.controlInstance.name];
        let validatestate: boolean = true;
        form.validate((valid: boolean) => {
            validatestate = valid ? true : false;
        });
        return validatestate;
    }

    /**
     * 表单项值变更
     *
     * @param {{ name: string, value: any }} $event
     * @returns {void}
     * @memberof FormControlBase
     */
    public onFormItemValueChange($event: { name: string, value: any }): void {
        if (!$event || !$event.name || Object.is($event.name, '') || !this.data.hasOwnProperty($event.name)) {
            return;
        }
        this.validateEditorRuleAction($event.name, $event.value);
        this.data[$event.name] = $event.value;
        this.formDataChange({ name: $event.name, newVal: $event.value, oldVal: null });

    }

    /**
     * 校验编辑器基础规则后续行为
     *
     * @param {string} name
     * @param {*} value
     * @returns {void}
     * @memberof FormControlBase
     */
    public validateEditorRuleAction(name: string, value: any) {
        let allFormItems: IPSDEEditFormItem[] = ModelTool.getAllFormItems(this.controlInstance);
        if (allFormItems?.length > 0) {
            let curFormItem: IPSDEEditFormItem | undefined = allFormItems?.find((item: any) => {
                return item.name === name;
            })
            if (!curFormItem)
                return;
            let condition: any = Verify.buildVerConditions(curFormItem.getPSEditor());
            if (condition && condition.length > 0) {
                // todo 提示info
            }
        }
    }


    /**
     * 设置数据项值
     *
     * @param {string} name
     * @param {*} value
     * @returns {void}
     * @memberof FormControlBase
     */
    public setDataItemValue(name: string, value: any): void {
        if (!name || Object.is(name, '') || !this.data.hasOwnProperty(name)) {
            return;
        }
        if (Object.is(this.data[name], value)) {
            return;
        }
        this.data[name] = value;
    }

    /**
     * 分组界面行为事件
     *
     * @param {*} $event
     * @memberof FormControlBase
     */
    public groupUIActionClick($event: any): void { }

    /**
     * 编辑表单初始化
     *
     * @memberof FormControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        // 初始化默认值
        this.formState = new Subject();
        this.dataChang = new Subject();
        this.ignorefieldvaluechange = false

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
            });
        }
    }

    /**
     * 表单销毁
     *
     * @memberof FormControlBase
     */
    public ctrlDestroyed(args?: any) {
        super.ctrlDestroyed();
        if (this.dataChangEvent) {
            this.dataChangEvent.unsubscribe();
        }
    }

    /**
     * 表单自动加载
     *
     * @param {*} [arg={}]
     * @returns {void}
     * @memberof FormControlBase
     */
    public autoLoad(arg: any = {}): void {
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
     * @public
     * @param {*} [opt={}]
     * @memberof FormControlBase
     */
    public load(opt: any = {}): void {
        if (!this.loadAction) {
            this.$throw( `${this.controlInstance.codeName}` + (this.$t('app.formpage.notconfig.loadaction') as string));
            return;
        }
        const arg: any = { ...opt };
        let viewparamResult: any = Object.assign(arg, this.viewparams);
        this.ctrlBeginLoading();
        const get: Promise<any> = this.service.get(this.loadAction, JSON.parse(JSON.stringify(this.context)), { viewparams: viewparamResult }, this.showBusyIndicator);
        get.then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                this.$throw(response);
                return;
            }
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
        }).catch((error: any) => {
            this.ctrlEndLoading();
            this.$throw(error);
        });
    }

    /**
     * 加载草稿
     *
     * @param {*} [opt={}]
     * @memberof FormControlBase
     */
    public loadDraft(opt: any = {}, mode?: string): void {
        if (!this.loaddraftAction) {
            this.$throw((this.$t('app.searchForm.notConfig.loaddraftAction') as string));
            return;
        }
        const arg: any = { ...opt };
        Object.assign(arg, { viewparams: this.viewparams });
        let post: Promise<any> = this.service.loadDraft(this.loaddraftAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                this.$throw(response);
                return;
            }
            const data = response.data;
            this.resetDraftFormStates();
            this.onFormLoad(data, 'loadDraft');
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
            if (Object.is(mode, 'RESET')) {
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
            this.ctrlEndLoading();
            this.$throw(response);
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
     * @memberof FormControlBase
     */
    public updateFormItems(mode: string, data: any = {}, updateDetails: string[], showloading?: boolean): void { }

    /**
     * 回车事件
     *
     * @param {*} $event
     * @memberof FormControlBase
     */
    public onEnter($event: any): void { }

    /**
     * 搜索
     *
     * @memberof FormControlBase
     */
    public onSearch() {
        if (!this.formValidateStatus()) {
            return;
        }
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'search',
            data: this.data,
        });
    }

    /**
     * 重置
     *
     * @memberof FormControlBase
     */
    public onReset() {
        this.loadDraft({}, 'RESET');
    }

}
