import { EntityBase } from 'ibiz-core';
import { IIbizproProductMonthly } from '../interface';

/**
 * 产品月报基类
 *
 * @export
 * @abstract
 * @class IbizproProductMonthlyBase
 * @extends {EntityBase}
 * @implements {IIbizproProductMonthly}
 */
export abstract class IbizproProductMonthlyBase extends EntityBase implements IIbizproProductMonthly {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbizproProductMonthlyBase
     */
    get srfdename(): string {
        return 'IBIZPRO_PRODUCTMONTHLY';
    }
    get srfkey() {
        return this.ibizproproductmonthlyid;
    }
    set srfkey(val: any) {
        this.ibizproproductmonthlyid = val;
    }
    get srfmajortext() {
        return this.ibizproproductmonthlyname;
    }
    set srfmajortext(val: any) {
        this.ibizproproductmonthlyname = val;
    }
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbizproProductMonthlyBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibizproproductmonthlyid = data.ibizproproductmonthlyid || data.srfkey;
        this.ibizproproductmonthlyname = data.ibizproproductmonthlyname || data.srfmajortext;
    }
}
