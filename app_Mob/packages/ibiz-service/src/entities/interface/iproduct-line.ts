import { IEntityBase } from 'ibiz-core';

/**
 * 产品线（废弃）
 *
 * @export
 * @interface IProductLine
 * @extends {IEntityBase}
 */
export interface IProductLine extends IEntityBase {
    /**
     * id
     */
    id?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 产品线名称
     */
    name?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 类型
     *
     * @type {('line' | 'story' | 'task' | 'doc' | 'case' | 'bug')} line: 产品线, story: 需求, task: 任务, doc: 文档目录, case: 测试用例, bug: Bug
     */
    type?: 'line' | 'story' | 'task' | 'doc' | 'case' | 'bug';
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
}
