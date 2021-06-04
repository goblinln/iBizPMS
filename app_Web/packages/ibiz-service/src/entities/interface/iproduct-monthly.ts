import { IEntityBase } from 'ibiz-core';

/**
 * 产品月报
 *
 * @export
 * @interface IProductMonthly
 * @extends {IEntityBase}
 */
export interface IProductMonthly extends IEntityBase {
    /**
     * 任务
     */
    tasks?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 总工时
     */
    totalestimates?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 年月
     */
    yearmonth?: any;
    /**
     * 产品月报标识
     */
    ibizproproductmonthlyid?: any;
    /**
     * 产品月报名称
     */
    ibizproproductmonthlyname?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 产品负责人
     */
    po?: any;
    /**
     * 产品名称
     */
    productname?: any;
    /**
     * 产品编号
     */
    product?: any;
}
