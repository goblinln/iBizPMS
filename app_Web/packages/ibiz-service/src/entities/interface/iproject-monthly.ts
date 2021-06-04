import { IEntityBase } from 'ibiz-core';

/**
 * 项目月报
 *
 * @export
 * @interface IProjectMonthly
 * @extends {IEntityBase}
 */
export interface IProjectMonthly extends IEntityBase {
    /**
     * 项目月报名称
     */
    ibizproprojectmonthlyname?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 年月
     */
    yearmonth?: any;
    /**
     * 总工时
     */
    totalestimates?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 任务
     */
    tasks?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 项目月报标识
     */
    ibizproprojectmonthlyid?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 项目负责人
     */
    pm?: any;
    /**
     * 项目名称
     */
    projectname?: any;
    /**
     * 项目编号
     */
    project?: any;
}
