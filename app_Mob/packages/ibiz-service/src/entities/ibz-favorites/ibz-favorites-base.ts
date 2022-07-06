import { EntityBase } from 'ibiz-core';
import { IIbzFavorites } from '../interface';

/**
 * 收藏基类
 *
 * @export
 * @abstract
 * @class IbzFavoritesBase
 * @extends {EntityBase}
 * @implements {IIbzFavorites}
 */
export abstract class IbzFavoritesBase extends EntityBase implements IIbzFavorites {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbzFavoritesBase
     */
    get srfdename(): string {
        return 'IBZ_FAVORITES';
    }
    get srfkey() {
        return this.ibzfavoritesid;
    }
    set srfkey(val: any) {
        this.ibzfavoritesid = val;
    }
    get srfmajortext() {
        return this.ibzfavoritesname;
    }
    set srfmajortext(val: any) {
        this.ibzfavoritesname = val;
    }
    /**
     * 类型
     */
    type?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 收藏标识
     */
    ibzfavoritesid?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 数据对象标识
     */
    objectid?: any;
    /**
     * 收藏用户
     */
    account?: any;
    /**
     * 收藏名称
     */
    ibzfavoritesname?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbzFavoritesBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibzfavoritesid = data.ibzfavoritesid || data.srfkey;
        this.ibzfavoritesname = data.ibzfavoritesname || data.srfmajortext;
    }
}
