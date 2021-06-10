import { MainControlInterface } from 'ibiz-core';

/**
 * 分页导航面板接口
 *
 * @interface TabExpPanelControlInterface
 */
export interface TabExpPanelControlInterface extends MainControlInterface{

    /**
     * 分页视图面板数据变更
     *
     * @memberof TabExpPanelControlInterface
     */
    tabViewPanelDatasChange(): void;

    /**
     * 分页面板选中
     *
     * @param {*} $event 选中分页
     * @returns
     * @memberof TabExpPanelControlInterface
     */
    tabPanelClick($event: any): void;
}