import { MDControlInterface } from 'ibiz-core';

/**
 * 地图接口
 *
 * @interface MapControlInterface
 */
export interface MapControlInterface extends MDControlInterface {
    
    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof MapControlInterface
     */
    refresh(args?: any): void;

    /**
     * 地图数据加载
     *
     * @param {*} [data={}] 额外参数
     * @returns {void}
     * @memberof MapControlInterface
     */
    load(data?: any): void;
}
