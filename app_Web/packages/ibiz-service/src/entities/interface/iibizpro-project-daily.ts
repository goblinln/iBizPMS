import { IEntityBase } from 'ibiz-core';

/**
 * 项目日报
 *
 * @export
 * @interface IIbizproProjectDaily
 * @extends {IEntityBase}
 */
export interface IIbizproProjectDaily extends IEntityBase {
    /**
     * 项目日报名称
     */
    ibizproprojectdailyname?: any;
    /**
     * 任务
     */
    tasks?: any;
    /**
     * 开始日期
     */
    begin?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 结束日期
     */
    end?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 总工时
     */
    totalestimates?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 项目负责人
     */
    pm?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 项目日报标识
     */
    ibizproprojectdailyid?: any;
    /**
     * 项目名称
     */
    projectname?: any;
    /**
     * 项目编号
     */
    project?: any;
}
