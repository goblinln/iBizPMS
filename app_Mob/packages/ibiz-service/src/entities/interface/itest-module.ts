import { IEntityBase } from 'ibiz-core';

/**
 * 测试模块
 *
 * @export
 * @interface ITestModule
 * @extends {IEntityBase}
 */
export interface ITestModule extends IEntityBase {
    /**
     * 类型（story）
     */
    type?: any;
    /**
     * path
     */
    path?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * 排序值
     */
    order?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * branch
     */
    branch?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 叶子模块
     */
    isleaf?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * 上级模块
     */
    parentname?: any;
    /**
     * 测试
     */
    rootname?: any;
    /**
     * 编号
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
