import { IEntityBase } from 'ibiz-core';

/**
 * 需求模块
 *
 * @export
 * @interface IIBZProStoryModule
 * @extends {IEntityBase}
 */
export interface IIBZProStoryModule extends IEntityBase {
    /**
     * 级别
     */
    grade?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * 需求模块类型
     *
     * @type {('pmsStoryModule' | 'iBizSysModule' | 'iBizReqModule')} pmsStoryModule: PMS需求模块, iBizSysModule: iBiz系统模块, iBizReqModule: iBiz需求模块
     */
    ibiz_storytype?: 'pmsStoryModule' | 'iBizSysModule' | 'iBizReqModule';
    /**
     * id
     */
    id?: any;
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
     * 名称
     */
    name?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 路径
     */
    path?: any;
    /**
     * IBIZ标识
     */
    ibizid?: any;
    /**
     * 产品
     */
    productname?: any;
    /**
     * 编号
     */
    root?: any;
    /**
     * id
     */
    parent?: any;
}
