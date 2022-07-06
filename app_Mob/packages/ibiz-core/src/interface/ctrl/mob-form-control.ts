import { IPSDEFDCatGroupLogic } from '@ibiz/dynamic-model-api';
import { MobMainControlInterface } from 'ibiz-core';
/**
 * 表单基类接口
 *
 * @interface MobFormControlInterface
 */
export interface MobFormControlInterface extends MobMainControlInterface{

    /**
     * 重置表单项值
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal } 名称,新值，旧值
     * @memberof MobFormControlInterface
     */
    resetFormData({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void;

    /**
     * 表单逻辑
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal } 名称,新值，旧值
     * @memberof MobFormControlInterface
     */
    formLogic({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void;

    /**
     * 表单值变化
     *
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal } 名称,新值，旧值
     * @memberof MobFormControlInterface
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
     * @memberof MobFormControlInterface
     */
    setFormEnableCond(data: any): void;

    /**
     * 新建默认值
     *
     * @memberof MobFormControlInterface
     */
    createDefault(): void;

    /**
     * 重置草稿表单状态
     *
     * @memberof MobFormControlInterface
     */
    resetDraftFormStates(): void;

    /**
     * 重置校验结果
     *
     * @memberof MobFormControlInterface
     */
    resetValidates(): void;

    /**
     * 表单项值变更
     *
     * @param {{ name: string, value: any }} $event 名称，值 
     * @memberof MobFormControlInterface
     */
    onFormItemValueChange($event: { name: string, value: any }): void;

    /**
     * 表单自动加载
     *
     * @param {*} [arg={}] 加载参数
     * @memberof MobFormControlInterface
     */
    autoLoad(arg: any): void;

    /**
     * 加载
     *
     * @param {*} opt 加载参数
     * @memberof MobFormControlInterface
     */
    load(opt: any): void;

    /**
     * 加载草稿
     *
     * @param {*} opt 加载参数
     * @param {string} [mode] 加载模式 
     * @memberof MobFormControlInterface
     */
    loadDraft(opt: any, mode?: string): void;

    /**
     * 表单项更新
     *
     * @param {string} mode 界面行为名称
     * @param {*} [data={}] 请求数据
     * @param {string[]} updateDetails 更新项
     * @param {boolean} [showloading] 是否显示加载状态
     * @memberof MobFormControlInterface
     */
    updateFormItems(mode: string, data: any, updateDetails: string[], showloading?: boolean): void;

    /**
     * 校验动态逻辑结果
     *
     * @param {*} data 数据对象
     * @param {IPSDEFDCatGroupLogic} logic 逻辑对象
     * @return {*}  {boolean}
     * @memberof MobFormControlInterface
     */
    verifyGroupLogic(data: any, logic: IPSDEFDCatGroupLogic): boolean

    /**
     * 自动保存
     *
     * @param {*} opt 额外参数
     * @memberof MobFormControlInterface
     */
    autoSave(opt: any): void;

    /**
     * 保存
     *
     * @param {*} opt 额外参数
     * @param {boolean} showResultInfo 是否显示提示信息
     * @param {boolean} isStateNext formState是否下发通知
     * @return {*}  {Promise<any>}
     * @memberof MobFormControlInterface
     */
    save(opt: any, showResultInfo: boolean, isStateNext: boolean): Promise<any>;    

    /**
     * 删除
     *
     * @param {Array<any>} [opt=[]] 额外参数 
     * @param {boolean} [showResultInfo] 是否显示提示信息
     * @return {*}  {Promise<any>}
     * @memberof MobFormControlInterface
     */
    remove(opt: Array<any>, showResultInfo?: boolean): Promise<any>;

    /**
     * 保存并退出
     *
     * @param {any[]} data 额外参数
     * @return {*}  {Promise<any>}
     * @memberof MobFormControlInterface
     */
    saveAndExit(data: any[]): Promise<any>;     

    /**
     * 置空对象
     *
     * @param {*} _datas 滞空对象属性
     * @memberof MobFormControlInterface
     */
    ResetData(_datas: any): void;

    /**
     * 保存并新建
     *
     * @param {any[]} data 额外参数
     * @return {*}  {Promise<any>}
     * @memberof MobFormControlInterface
     */
     saveAndNew(data: any[]): Promise<any>;    

    /**
     * 删除并退出
     *
     * @param {any[]} data 额外参数
     * @return {*}  {Promise<any>}
     * @memberof MobFormControlInterface
     */
     removeAndExit(data: any[]): Promise<any>;     

    /**
     * 表单刷新数据
     *
     * @param {*} args 额外参数
     * @memberof MobFormControlInterface
     */
     refresh(args: any): void;    
     
}
