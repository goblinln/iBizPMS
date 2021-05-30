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
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
}
