import { IEntityBase } from 'ibiz-core';

/**
 * 产品线（废弃）
 *
 * @export
 * @interface IProductLine
 * @extends {IEntityBase}
 */
export interface IProductLine extends IEntityBase {
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
     * 排序
     */
    order?: any;
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
}
