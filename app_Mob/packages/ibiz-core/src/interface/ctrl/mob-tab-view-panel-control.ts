import { MobMainControlInterface } from 'ibiz-core';

/**
 * 分页视图面板接口
 *
 * @interface MobTabViewPanelControlInterface
 */
export interface MobTabViewPanelControlInterface extends MobMainControlInterface{

    /**
     * 激活
     *
     * @param {*} [args]
     * @memberof MobTabViewPanelControlBase
     */
     actived(): void;

}