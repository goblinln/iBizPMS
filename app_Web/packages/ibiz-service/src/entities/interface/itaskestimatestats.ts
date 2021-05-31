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
     * 年
     */
    year?: any;
    /**
     * 消耗的工时
     */
    consumed?: any;
    /**
     * 评估状态
     */
    evaluationstatus?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 月（显示）
     */
    monthname?: any;
    /**
     * 项目名称
     */
    name?: any;
    /**
     * 年（显示）
     */
    yearname?: any;
    /**
     * 评估工时
     */
    evaluationtime?: any;
    /**
     * 评估成本
     */
    evaluationcost?: any;
    /**
     * 投入成本
     */
    inputcost?: any;
    /**
     * 月
     */
    month?: any;
    /**
     * 任务数
     */
    taskcnt?: any;
}
