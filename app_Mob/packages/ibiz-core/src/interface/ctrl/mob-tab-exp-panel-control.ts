import { MobMainControlInterface } from 'ibiz-core';

/**
 * 分页导航面板接口
 *
 * @interface MobTabExpPanelControlInterface
 */
export interface MobTabExpPanelControlInterface extends MobMainControlInterface{

    /**
     * 分页面板选中
     *
     * @param {*} $event 选中分页
     * @returns
     * @memberof MobTabExpPanelControlInterface
     */
    tabPanelClick($event: any): void;
}