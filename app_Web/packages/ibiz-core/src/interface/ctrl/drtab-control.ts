import { MainControlInterface } from 'ibiz-core';

/**
 * 数据关系分页部件接口
 *
 * @interface DrtabControlInterface
 */
export interface DrtabControlInterface extends MainControlInterface{

    /**
     *  选中节点
     *
     * @param {string} tabPaneName 分页name
     * @memberof DrtabControlInterface
     */
    tabPanelClick(tabPaneName: string): void;
}