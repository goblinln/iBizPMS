import { IEntityBase } from 'ibiz-core';

/**
 * 收藏
 *
 * @export
 * @interface IIbzFavorites
 * @extends {IEntityBase}
 */
export interface IIbzFavorites extends IEntityBase {
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
}
