import { IEntityBase } from 'ibiz-core';

/**
 * 需求模块
 *
 * @export
 * @interface IProductModule
 * @extends {IEntityBase}
 */
export interface IProductModule extends IEntityBase {
    /**
     * path
     */
    path?: any;
    /**
     * 数据选择排序
     */
    orderpk?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * branch
     */
    branch?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 排序值
     */
    order?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * 类型（story）
     */
    type?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * 叶子模块
     */
    isleaf?: any;
    /**
     * id
     */
    id?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * 所属产品
     */
    rootname?: any;
    /**
     * 上级模块
     */
    parentname?: any;
    /**
     * 产品
     */
    root?: any;
    /**
     * id
     */
    parent?: any;
}
