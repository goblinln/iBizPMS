import { MainControlInterface } from 'ibiz-core';

/**
 * 分页视图面板接口
 *
 * @interface TabViewPanelControlInterface
 */
export interface TabViewPanelControlInterface extends MainControlInterface{

    /**
     * 视图数据变化
     *
     * @param {*} $event
     * @memberof TabViewPanelControlInterface
     */
    viewDatasChange($event: any): void;

    /**
     * 激活项变化
     *
     * @memberof TabViewPanelControlInterface
     */
    activiedChange(): Promise<any>;
}