import { MobMainControlInterface } from 'ibiz-core';

/**
 * 选择视图面板基类接口
 *
 * @interface MobPickUpViewPanelControlInterface
 */
export interface MobPickUpViewPanelControlInterface extends MobMainControlInterface{

    /**
     * 视图数据变化
     *
     * @param {*} $event
     * @memberof MobPickUpViewPanelControlInterface
     */
    onViewDatasChange($event: any): void;

}