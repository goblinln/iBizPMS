import { IEntityBase } from 'ibiz-core';

/**
 * 索引检索
 *
 * @export
 * @interface IIbizproIndex
 * @extends {IEntityBase}
 */
export interface IIbizproIndex extends IEntityBase {
    /**
     * 主键
     */
    indexid?: any;
    /**
     * 权限
     */
    acl?: any;
    /**
     * docid
     */
    docid?: any;
    /**
     * 标题[需求、任务等]
     */
    indexname?: any;
    /**
     * 颜色
     */
    color?: any;
    /**
     * 逻辑标识
     */
    deleted?: any;
    /**
     * 权限列表
     */
    acllist?: any;
    /**
     * 内容[需求、任务等]
     */
    indexdesc?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 类型
     *
     * @type {('bug' | 'product' | 'task' | 'case' | 'doc' | 'story' | 'project')} bug: Bug, product: 产品, task: 任务, case: 功能测试, doc: 文档, story: 需求, project: 项目
     */
    indextype?: 'bug' | 'product' | 'task' | 'case' | 'doc' | 'story' | 'project';
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 项目
     */
    project?: any;
    /**
     * 组织标识
     */
    orgid?: any;
}
