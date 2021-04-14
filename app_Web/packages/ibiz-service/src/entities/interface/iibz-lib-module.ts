import { IEntityBase } from 'ibiz-core';

/**
 * 用例库模块
 *
 * @export
 * @interface IIbzLibModule
 * @extends {IEntityBase}
 */
export interface IIbzLibModule extends IEntityBase {
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * id
     */
    id?: any;
    /**
     * branch
     */
    branch?: any;
    /**
     * 叶子模块
     */
    isleaf?: any;
    /**
     * 类型（story）
     */
    type?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * path
     */
    path?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * 排序值
     */
    order?: any;
    /**
     * 上级模块
     */
    parentname?: any;
    /**
     * 编号
     */
    root?: any;
    /**
     * id
     */
    parent?: any;
}
