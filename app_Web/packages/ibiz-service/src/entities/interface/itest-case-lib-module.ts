import { IEntityBase } from 'ibiz-core';

/**
 * 用例库模块
 *
 * @export
 * @interface ITestCaseLibModule
 * @extends {IEntityBase}
 */
export interface ITestCaseLibModule extends IEntityBase {
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
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
}
