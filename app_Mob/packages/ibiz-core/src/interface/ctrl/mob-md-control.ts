import { MobMainControlInterface } from 'ibiz-core';
/**
 * 实体部件基类接口
 *
 * @interface MobMDControlInterface
 */
export interface MobMDControlInterface extends MobMainControlInterface{

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof MobMDControlInterface
     */
    getDatas(): any[]

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof MobMDControlInterface
     */
    getData(): any


}
