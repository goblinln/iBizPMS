import { EntityBase } from 'ibiz-core';
import { IProductLine } from '../interface';

/**
 * 产品线（废弃）基类
 *
 * @export
 * @abstract
 * @class ProductLineBase
 * @extends {EntityBase}
 * @implements {IProductLine}
 */
export abstract class ProductLineBase extends EntityBase implements IProductLine {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProductLineBase
     */
    get srfdename(): string {
        return 'IBZ_PRODUCTLINE';
    }
    get srfkey() {
        return this.productlineid;
    }
    set srfkey(val: any) {
        this.productlineid = val;
    }
    get srfmajortext() {
        return this.productlinename;
    }
    set srfmajortext(val: any) {
        this.productlinename = val;
    }
    /**
     * 产品线名称
     */
    productlinename?: any;
    /**
     * 产品线标识
     */
    productlineid?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 排序
     */
    order?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProductLineBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.productlineid = data.productlineid || data.srfkey;
        this.productlinename = data.productlinename || data.srfmajortext;
    }
}
