import { EntityBase } from 'ibiz-core';
import { IProductLife } from '../interface';

/**
 * 产品生命周期基类
 *
 * @export
 * @abstract
 * @class ProductLifeBase
 * @extends {EntityBase}
 * @implements {IProductLife}
 */
export abstract class ProductLifeBase extends EntityBase implements IProductLife {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProductLifeBase
     */
    get srfdename(): string {
        return 'IBZ_PRODUCTLIFE';
    }
    get srfkey() {
        return this.productlifeid;
    }
    set srfkey(val: any) {
        this.productlifeid = val;
    }
    get srfmajortext() {
        return this.productlifename;
    }
    set srfmajortext(val: any) {
        this.productlifename = val;
    }
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 产品生命周期名称
     */
    productlifename?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 父对象
     */
    parent?: any;
    /**
     * 年
     */
    year?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 属性
     */
    type?: any;
    /**
     * 里程碑
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    marker?: 'yes' | 'no';
    /**
     * 开始日期
     */
    begin?: any;
    /**
     * 产品生命周期标识
     */
    productlifeid?: any;
    /**
     * 结束日期
     */
    end?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProductLifeBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.productlifeid = data.productlifeid || data.srfkey;
        this.productlifename = data.productlifename || data.srfmajortext;
    }
}
