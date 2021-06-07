import { IEntityBase } from 'ibiz-core';

/**
 * 用户模板
 *
 * @export
 * @interface IUserTpl
 * @extends {IEntityBase}
 */
export interface IUserTpl extends IEntityBase {
    /**
     * 模板标题
     */
    title?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * content
     */
    content?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * type
     *
     * @type {('story' | 'task' | 'bug' | 'product' | 'project' | 'productplan')} story: 需求, task: 任务, bug: Bug, product: 产品, project: 项目, productplan: 产品计划
     */
    type?: 'story' | 'task' | 'bug' | 'product' | 'project' | 'productplan';
    /**
     * account
     */
    account?: any;
    /**
     * 用户模板编号
     */
    usertplsn?: any;
    /**
     * 公开
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    ibizpublic?: '1' | '0';
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属部门
     */
    dept?: any;
}
