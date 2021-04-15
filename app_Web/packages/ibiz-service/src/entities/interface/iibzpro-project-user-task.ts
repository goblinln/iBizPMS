import { IEntityBase } from 'ibiz-core';

/**
 * 项目汇报用户任务
 *
 * @export
 * @interface IIbzproProjectUserTask
 * @extends {IEntityBase}
 */
export interface IIbzproProjectUserTask extends IEntityBase {
    /**
     * work
     */
    work?: any;
    /**
     * 任务类型
     *
     * @type {('design' | 'devel' | 'test' | 'study' | 'discuss' | 'ui' | 'affair' | 'serve' | 'misc')} design: 设计, devel: 开发, test: 测试, study: 研究, discuss: 讨论, ui: 界面, affair: 事务, serve: 服务, misc: 其他
     */
    tasktype?: 'design' | 'devel' | 'test' | 'study' | 'discuss' | 'ui' | 'affair' | 'serve' | 'misc';
    /**
     * 用户
     */
    account?: any;
    /**
     * 总计消耗
     */
    consumed?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 延期天数
     */
    delaydays?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 进度
     */
    progressrate?: any;
    /**
     * 预计开始
     */
    eststarted?: any;
    /**
     * 截止日期
     */
    deadline?: any;
    /**
     * 任务
     */
    task?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 任务名称
     */
    taskname?: any;
}
