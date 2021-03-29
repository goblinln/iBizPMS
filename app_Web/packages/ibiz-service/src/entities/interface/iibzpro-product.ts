import { IEntityBase } from 'ibiz-core';

/**
 * 平台产品
 *
 * @export
 * @interface IIBZProProduct
 * @extends {IEntityBase}
 */
export interface IIBZProProduct extends IEntityBase {
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
}
