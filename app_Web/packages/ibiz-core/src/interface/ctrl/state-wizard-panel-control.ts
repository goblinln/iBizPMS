import { MainControlInterface } from 'ibiz-core';

/**
 * 状态向导面板接口
 *
 * @interface StateWizardPanelControlInterface
 */
export interface StateWizardPanelControlInterface extends MainControlInterface{

    /**
     * 状态表单加载完成
     *
     * @param {*} args 表单参数
     * @param {string} name 名称
     * @memberof StateWizardPanelControlInterface
     */
    wizardpanelFormload(args: any, name: string): void;

    /**
     * 向导表单保存完成
     *
     * @param {*} args 表单参数
     * @param {string} name 名称
     * @memberof StateWizardPanelControlInterface
     */
    wizardpanelFormsave(args: any, name: string): void;

    /**
     * 步骤标题点击
     *
     * @param {string} name 步骤名称
     * @return {*} 
     * @memberof StateWizardPanelControlInterface
     */
    stepTitleClick(name: string): void;

    /**
     * 左右按钮点击
     *
     * @param {*} mode 左右标识
     * @return {*} 
     * @memberof StateWizardPanelControlInterface
     */
    handleClick(mode: any): void;

    /**
     * 打开链接
     *
     * @param {string} name 表单名称
     * @memberof StateWizardPanelControlInterface
     */
    handleOpen(name:string): void;

     /**
     * 关闭
     *
     * @param {string} name 表单名称
     * @memberof StateWizardPanelControlInterface
     */
    handleClose(name:string): void;

    /**
     * 上一步
     *
     * @param {string} name 表单名称
     * @memberof StateWizardPanelControlInterface
     */
    onClickPrev(name:string): void;

    /**
     * 下一步
     *
     * @param {string} name 表单名称
     * @memberof StateWizardPanelControlInterface
     */
    onClickNext(name:string): void;

    /**
     * 完成
     *
     * @param {string} name 表单名称
     * @memberof StateWizardPanelControlInterface
     */
    onClickFinish(name:string): void;

    /**
     * 抽屉状态改变
     *
     * @param {*} value  值
     * @memberof StateWizardPanelControlInterface
     */
    onVisibleChange(value:any): void;
}