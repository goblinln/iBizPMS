import { EntityBase } from 'ibiz-core';
import { IIBIZProTag } from '../interface';

/**
 * 标签基类
 *
 * @export
 * @abstract
 * @class IBIZProTagBase
 * @extends {EntityBase}
 * @implements {IIBIZProTag}
 */
export abstract class IBIZProTagBase extends EntityBase implements IIBIZProTag {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IBIZProTagBase
     */
    get srfdename(): string {
        return 'IBIZPRO_TAG';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    // IBIZProTag 实体未设置主文本属性
    /**
     * ID
     */
    id?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IBIZProTagBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        // IBIZProTag 实体未设置主文本属性
    }
}
