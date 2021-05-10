import { IEntityBase } from 'ibiz-core';

/**
 * 模块
 *
 * @export
 * @interface IModule
 * @extends {IEntityBase}
 */
export interface IModule extends IEntityBase {
    /**
     * 所属根
     */
    root?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 级别
     */
    grade?: any;
    /**
     * 类型
     *
     * @type {('line' | 'story' | 'task' | 'doc' | 'case' | 'bug')} line: 产品线, story: 需求, task: 任务, doc: 文档目录, case: 测试用例, bug: Bug
     */
    type?: 'line' | 'story' | 'task' | 'doc' | 'case' | 'bug';
    /**
     * 模块名称
     */
    name?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 负责人
     */
    owner?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 数据选择排序
     */
    orderpk?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 收藏者
     */
    collector?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 路径
     */
    path?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 组织标识
     */
    orgid?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 上级模块
     */
    parentname?: any;
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 上级模块
     */
    parent?: any;
    /**
     * 模块编号
     */
    modulesn?: any;
}
