import { MainControlInterface } from 'ibiz-core';

/**
 * 状态向导面板接口
 *
 * @interface WizardPanelControlInterface
 */
export interface WizardPanelControlInterface extends MainControlInterface{
    /**
     * 上一步
     *
     * @memberof WizardPanelControlInterface
     */
    onClickPrev(): void;

    /**
     * 下一步
     *
     * @memberof WizardPanelControlInterface
     */
    onClickNext(): void;

    /**
     * 完成
     *
     * @memberof WizardPanelControlInterface
     */
    onClickFinish(): void;

    /**
    * 向导表单加载完成
    *
    * @param {*} args
    * @param {string} name
    * @memberof WizardPanelControlInterface
    */
    wizardpanelFormload(args: any, name: string): void;

    /**
     * 向导表单保存完成
     *
     * @param {*} args 
     * @param {string} name
     * @memberof WizardPanelControlInterface
     */
    wizardpanelFormsave(args: any, name: string): void;
}