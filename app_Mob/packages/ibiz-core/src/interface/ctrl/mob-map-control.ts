import { MobMDControlInterface } from 'ibiz-core';

/**
 * 地图接口
 *
 * @interface MobMapControlInterface
 */
export interface MobMapControlInterface extends MobMDControlInterface {
    
    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof MobMapControlInterface
     */
    refresh(args?: any): void;

    /**
     * 地图数据加载
     *
     * @param {*} [data={}] 额外参数
     * @returns {void}
     * @memberof MobMapControlInterface
     */
    load(data?: any): void;
}
