import { IEntityBase } from 'ibiz-core';

/**
 * 产品日报
 *
 * @export
 * @interface IProductDaily
 * @extends {IEntityBase}
 */
export interface IProductDaily extends IEntityBase {
    /**
     * 结束日期
     */
    end?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 产品日报名称
     */
    ibizproproductdailyname?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 任务
     */
    tasks?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 产品日报标识
     */
    ibizproproductdailyid?: any;
    /**
     * 总工时
     */
    totalestimates?: any;
    /**
     * 开始日期
     */
    begin?: any;
    /**
     * 产品负责人
     */
    po?: any;
    /**
     * 产品名称
     */
    productname?: any;
    /**
     * 产品
     */
    product?: any;
}
