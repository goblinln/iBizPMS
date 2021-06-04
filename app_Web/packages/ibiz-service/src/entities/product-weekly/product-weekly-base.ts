import { EntityBase } from 'ibiz-core';
import { IProductWeekly } from '../interface';

/**
 * 产品周报基类
 *
 * @export
 * @abstract
 * @class ProductWeeklyBase
 * @extends {EntityBase}
 * @implements {IProductWeekly}
 */
export abstract class ProductWeeklyBase extends EntityBase implements IProductWeekly {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProductWeeklyBase
     */
    get srfdename(): string {
        return 'IBIZPRO_PRODUCTWEEKLY';
    }
    get srfkey() {
        return this.ibizpro_productweeklyid;
    }
    set srfkey(val: any) {
        this.ibizpro_productweeklyid = val;
    }
    get srfmajortext() {
        return this.ibizproproductweeklyname;
    }
    set srfmajortext(val: any) {
        this.ibizproproductweeklyname = val;
    }
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProductWeeklyBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibizpro_productweeklyid = data.ibizpro_productweeklyid || data.srfkey;
        this.ibizproproductweeklyname = data.ibizproproductweeklyname || data.srfmajortext;
    }
}
