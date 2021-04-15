import { IEntityBase } from 'ibiz-core';

/**
 * 测试运行
 *
 * @export
 * @interface ITestRun
 * @extends {IEntityBase}
 */
export interface ITestRun extends IEntityBase {
    /**
     * 结果
     *
     * @type {('n/a' | 'pass' | 'fail' | 'blocked')} n/a: 忽略, pass: 通过, fail: 失败, blocked: 阻塞
     */
    lastrunresult?: 'n/a' | 'pass' | 'fail' | 'blocked';
    /**
     * 最后执行时间
     */
    lastrundate?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 指派给
     */
    assignedto?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 最后执行人
     */
    lastrunner?: any;
    /**
     * 当前状态
     *
     * @type {('wait' | 'doing' | 'done' | 'blocked')} wait: 未开始, doing: 进行中, done: 已完成, blocked: 被阻塞
     */
    status?: 'wait' | 'doing' | 'done' | 'blocked';
    /**
     * 编号
     */
    id?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 用例版本
     */
    version?: any;
    /**
     * 测试用例
     */
    ibizcase?: any;
    /**
     * 测试单
     */
    task?: any;
}
