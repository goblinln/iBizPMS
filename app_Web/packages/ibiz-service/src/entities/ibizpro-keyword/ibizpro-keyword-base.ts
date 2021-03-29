import { EntityBase } from 'ibiz-core';
import { IIBIZProKeyword } from '../interface';

/**
 * 关键字基类
 *
 * @export
 * @abstract
 * @class IBIZProKeywordBase
 * @extends {EntityBase}
 * @implements {IIBIZProKeyword}
 */
export abstract class IBIZProKeywordBase extends EntityBase implements IIBIZProKeyword {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IBIZProKeywordBase
     */
    get srfdename(): string {
        return 'IBIZPRO_KEYWORD';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    // IBIZProKeyword 实体未设置主文本属性
    /**
     * ID
     */
    id?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IBIZProKeywordBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        // IBIZProKeyword 实体未设置主文本属性
    }
}
