/**
 * 表单基类接口
 *
 * @interface FormControlInterface
 */
export interface FormControlInterface {


    /**
     * 重置表单项值
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal } 名称,新值，旧值
     * @memberof FormControlInterface
     */
    resetFormData({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void;


    /**
     * 表单逻辑
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal } 名称,新值，旧值
     * @memberof FormControlInterface
     */
    formLogic({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void;

    /**
     * 表单值变化
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal } 名称,新值，旧值
     * @memberof FormControlInterface
     */
    formDataChange({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void;

    /**
     * 值填充
     *
     * @param {*} [_datas={}] 表单数据
     * @param {string} [action] 行为标识
     * @memberof FormControlBase
     */
    fillForm(_datas: any, action: string): void;


    /**
     * 设置表单项是否启用
     *
     * @param {*} data 表单数据
     * @memberof FormControlInterface
     */
    setFormEnableCond(data: any): void;


    /**
     * 新建默认值
     *
     * @memberof FormControlInterface
     */
    createDefault(): void;


    /**
     * 重置草稿表单状态
     *
     * @memberof FormControlInterface
     */
    resetDraftFormStates(): void;


    /**
     * 重置校验结果
     *
     * @memberof FormControlInterface
     */
    resetValidates(): void;



    /**
     * 表单校验状态
     *
     * @return {*}  {boolean}
     * @memberof FormControlInterface
     */
    formValidateStatus(): boolean;


    /**
     * 表单项值变更
     *
     * @param {{ name: string, value: any }} $event 名称，值 
     * @memberof FormControlInterface
     */
    onFormItemValueChange($event: { name: string, value: any }): void;


    /**
     * 校验编辑器基础规则后续行为
     *
     * @param {string} name 名称
     * @param {*} value 值
     * @memberof FormControlInterface
     */
    validateEditorRuleAction(name: string, value: any): void;


    /**
     * 设置数据项值
     *
     * @param {string} name 名称
     * @param {*} value 值
     * @memberof FormControlInterface
     */
    setDataItemValue(name: string, value: any): void;


    /**
     * 分组界面行为事件
     *
     * @param {*} $event
     * @memberof FormControlInterface
     */
    groupUIActionClick($event: any): void;


    /**
     * 表单自动加载
     *
     * @param {*} [arg={}] 加载参数
     * @memberof FormControlInterface
     */
    autoLoad(arg: any): void;


    /**
     * 加载
     *
     * @param {*} opt 加载参数
     * @memberof FormControlInterface
     */
    load(opt: any): void;


    /**
     * 加载草稿
     *
     * @param {*} opt 加载参数
     * @param {string} [mode] 加载模式 
     * @memberof FormControlInterface
     */
    loadDraft(opt: any, mode?: string): void;


    /**
     * 表单项更新
     *
     * @param {string} mode 界面行为名称
     * @param {*} [data={}] 请求数据
     * @param {string[]} updateDetails 更新项
     * @param {boolean} [showloading] 是否显示加载状态
     * @memberof FormControlInterface
     */
    updateFormItems(mode: string, data: any, updateDetails: string[], showloading?: boolean): void;


    /**
     * 搜索
     *
     * @memberof FormControlInterface
     */
    onSearch(): void;


    /**
     * 重置
     *
     * @memberof FormControlInterface
     */
    onReset(): void;

}
