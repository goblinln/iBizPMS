import { IEntityBase } from 'ibiz-core';

/**
 * 任务模块
 *
 * @export
 * @interface IProjectModule
 * @extends {IEntityBase}
 */
export interface IProjectModule extends IEntityBase {
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 叶子模块
     */
    isleaf?: any;
    /**
     * 类型（task）
     */
    type?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 数据选择排序
     */
    orderpk?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 排序值
     */
    order?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * branch
     */
    branch?: any;
    /**
     * path
     */
    path?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * 所属项目
     */
    rootname?: any;
    /**
     * 上级模块
     */
    parentname?: any;
    /**
     * 项目
     */
    root?: any;
    /**
     * id
     */
    parent?: any;
}
