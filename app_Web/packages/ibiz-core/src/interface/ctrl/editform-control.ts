import { IPSDEFDCatGroupLogic } from "@ibiz/dynamic-model-api";
import { FormControlInterface } from "./form-control";

/**
 * 编辑表单基类接口
 *
 * @interface EditFormControlInterface
 */
export interface EditFormControlInterface extends FormControlInterface {

    /**
     * 自动保存
     *
     * @param {*} opt 额外参数
     * @memberof EditFormControlInterface
     */
    autoSave(opt: any): void;


    /**
     * 保存
     *
     * @param {*} opt 额外参数
     * @param {boolean} showResultInfo 是否显示提示信息
     * @param {boolean} isStateNext formState是否下发通知
     * @return {*}  {Promise<any>}
     * @memberof EditFormControlInterface
     */
    save(opt: any, showResultInfo: boolean, isStateNext: boolean): Promise<any>;


    /**
     * 删除
     *
     * @param {Array<any>} [opt=[]] 额外参数 
     * @param {boolean} [showResultInfo] 是否显示提示信息
     * @return {*}  {Promise<any>}
     * @memberof EditFormControlInterface
     */
    remove(opt: Array<any>, showResultInfo?: boolean): Promise<any>;


    /**
     * 工作流启动
     *
     * @param {*} data  表单数据
     * @param {*} [localdata] 补充逻辑完成参数
     * @return {*}  {Promise<any>}
     * @memberof EditFormControlInterface
     */
    wfstart(data: any, localdata?: any): Promise<any>;


    /**
     * 工作流提交
     *
     * @param {*} data  表单数据
     * @param {*} [localdata] 补充逻辑完成参数
     * @return {*}  {Promise<any>}
     * @memberof EditFormControlInterface
     */
    wfsubmit(data: any, localdata?: any): Promise<any>;


    /**
     * 表单刷新数据
     *
     * @param {*} args 额外参数
     * @memberof EditFormControlInterface
     */
    refresh(args: any): void;

    /**
     * 面板行为
     *
     * @param {string} [action] 调用的实体行为
     * @param {string} [emitAction] 抛出行为
     * @param {*} [data={}] 传入数据
     * @param {boolean} [showloading] 是否显示加载状态
     * @memberof EditFormControlInterface
     */
    panelAction(action: string, emitAction: string, data: any, showloading?: boolean): void;


    /**
     * 保存并退出
     *
     * @param {any[]} data 额外参数
     * @return {*}  {Promise<any>}
     * @memberof EditFormControlInterface
     */
    saveAndExit(data: any[]): Promise<any>;


    /**
     * 保存并新建
     *
     * @param {any[]} data 额外参数
     * @return {*}  {Promise<any>}
     * @memberof EditFormControlInterface
     */
    saveAndNew(data: any[]): Promise<any>;


    /**
     * 删除并退出
     *
     * @param {any[]} data 额外参数
     * @return {*}  {Promise<any>}
     * @memberof EditFormControlInterface
     */
    removeAndExit(data: any[]): Promise<any>;


    /**
     * 置空对象
     *
     * @param {*} _datas 滞空对象属性
     * @memberof EditFormControlInterface
     */
    ResetData(_datas: any): void;


    /**
     * 表单按钮行为触发
     *
     * @param {*} { formdetail, event }
     * @memberof EditFormControlInterface
     */
    onFormItemActionClick({ formdetail, event }: any): void;


    /**
     * 表单项检查逻辑
     *
     * @param {string} name 属性名
     * @return {*}  {Promise<any>}
     * @memberof EditFormControlInterface
     */
    checkItem(name: string): Promise<any>;


    /**
     * 计算表单按钮权限状态
     *
     * @param {*} data 传入数据
     * @memberof EditFormControlInterface
     */
    computeButtonState(data: any): void;


    /**
     * 显示更多模式切换操作
     *
     * @param {string} name 名称
     * @memberof EditFormControlInterface
     */
    manageContainerClick(name: string): void;


    /**
     * 打印
     *
     * @memberof EditFormControlInterface
     */
    print(): void;


    /**
     * 更新默认值
     *
     * @memberof EditFormControlInterface
     */
    updateDefault(): void;


    /**
     * 校验动态逻辑结果
     *
     * @param {*} data 数据对象
     * @param {IPSDEFDCatGroupLogic} logic 逻辑对象
     * @return {*}  {boolean}
     * @memberof EditFormControlInterface
     */
    verifyGroupLogic(data: any, logic: IPSDEFDCatGroupLogic): boolean


    /**
     * 处理操作列点击
     *
     * @param {*} event 事件对象
     * @param {*} formDetail 表单成员模型对象
     * @param {*} actionDetal 界面行为模型对象
     * @memberof EditFormControlInterface
     */
    handleActionClick(event: any, formDetail: any, actionDetal: any): void;

}
