import { MobMainControlInterface } from 'ibiz-core';

/**
 * 数据关系分页部件接口
 *
 * @interface MobDrtabControlInterface
 */
export interface MobDrtabControlInterface extends MobMainControlInterface{

    /**
     *  选中节点
     *
     * @param {string} tabPaneName 分页name
     * @memberof MobDrtabControlInterface
     */
    tabPanelClick(tabPaneName: string): void;
}