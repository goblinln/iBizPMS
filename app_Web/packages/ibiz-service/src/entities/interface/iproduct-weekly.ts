import { IEntityBase } from 'ibiz-core';

/**
 * 产品周报
 *
 * @export
 * @interface IProductWeekly
 * @extends {IEntityBase}
 */
export interface IProductWeekly extends IEntityBase {
    /**
     * 产品负责人
     */
    po?: any;
    /**
     * 产品周报标识
     */
    ibizpro_productweeklyid?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 任务
     */
    tasks?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 产品周报名称
     */
    ibizproproductweeklyname?: any;
    /**
     * 开始统计
     */
    begindatestats?: any;
    /**
     * 总工时
     */
    totalestimates?: any;
    /**
     * 结束统计
     */
    enddatestats?: any;
    /**
     * 产品名称
     */
    productname?: any;
    /**
     * 编号
     */
    product?: any;
}
