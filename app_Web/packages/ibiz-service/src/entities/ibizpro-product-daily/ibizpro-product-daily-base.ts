import { EntityBase } from 'ibiz-core';
import { IIbizproProductDaily } from '../interface';

/**
 * 产品日报基类
 *
 * @export
 * @abstract
 * @class IbizproProductDailyBase
 * @extends {EntityBase}
 * @implements {IIbizproProductDaily}
 */
export abstract class IbizproProductDailyBase extends EntityBase implements IIbizproProductDaily {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbizproProductDailyBase
     */
    get srfdename(): string {
        return 'IBIZPRO_PRODUCTDAILY';
    }
    get srfkey() {
        return this.ibizproproductdailyid;
    }
    set srfkey(val: any) {
        this.ibizproproductdailyid = val;
    }
    get srfmajortext() {
        return this.ibizproproductdailyname;
    }
    set srfmajortext(val: any) {
        this.ibizproproductdailyname = val;
    }
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbizproProductDailyBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibizproproductdailyid = data.ibizproproductdailyid || data.srfkey;
        this.ibizproproductdailyname = data.ibizproproductdailyname || data.srfmajortext;
    }
}
