import { IEntityBase } from 'ibiz-core';

/**
 * 任务工时统计
 *
 * @export
 * @interface ITaskestimatestats
 * @extends {IEntityBase}
 */
export interface ITaskestimatestats extends IEntityBase {
    /**
     * 日期
     */
    date?: any;
    /**
     * 消耗的工时
     */
    consumed?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 项目名称
     */
    name?: any;
    /**
     * 任务数
     */
    taskcnt?: any;
}
