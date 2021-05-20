import { IEntityBase } from 'ibiz-core';

/**
 * 产品线
 *
 * @export
 * @interface IIBZProProductLine
 * @extends {IEntityBase}
 */
export interface IIBZProProductLine extends IEntityBase {
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 类型
     *
     * @type {('line' | 'story' | 'task' | 'doc' | 'case' | 'bug')} line: 产品线, story: 需求, task: 任务, doc: 文档目录, case: 测试用例, bug: Bug
     */
    type?: 'line' | 'story' | 'task' | 'doc' | 'case' | 'bug';
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 模块名称
     */
    name?: any;
}
