import { EntityBase } from 'ibiz-core';
import { IIBZProProduct } from '../interface';

/**
 * 平台产品基类
 *
 * @export
 * @abstract
 * @class IBZProProductBase
 * @extends {EntityBase}
 * @implements {IIBZProProduct}
 */
export abstract class IBZProProductBase extends EntityBase implements IIBZProProduct {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IBZProProductBase
     */
    get srfdename(): string {
        return 'IBZPRO_PRODUCT';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.name;
    }
    set srfmajortext(val: any) {
        this.name = val;
    }
    /**
     * IBIZ标识
     */
    ibizid?: any;
    /**
     * 产品代号
     */
    code?: any;
    /**
     * 产品名称
     */
    name?: any;
    /**
     * 编号
     */
    id?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IBZProProductBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
