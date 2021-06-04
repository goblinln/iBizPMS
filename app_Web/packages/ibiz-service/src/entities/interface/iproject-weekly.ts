import { IEntityBase } from 'ibiz-core';

/**
 * 项目周报
 *
 * @export
 * @interface IProjectWeekly
 * @extends {IEntityBase}
 */
export interface IProjectWeekly extends IEntityBase {
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 年
     */
    year?: any;
    /**
     * 结束统计
     */
    enddatestats?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 任务
     */
    tasks?: any;
    /**
     * 项目周报名称
     */
    projectweeklyname?: any;
    /**
     * 周
     */
    week?: any;
    /**
     * 项目周报标识
     */
    projectweeklyid?: any;
    /**
     * 项目负责人
     */
    pm?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 总工时
     */
    totalestimates?: any;
    /**
     * 开始统计
     */
    begindatestats?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 月
     */
    month?: any;
    /**
     * 项目名称
     */
    projectname?: any;
    /**
     * 项目编号
     */
    project?: any;
}
