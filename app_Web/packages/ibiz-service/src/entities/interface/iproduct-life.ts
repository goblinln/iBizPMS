import { IEntityBase } from 'ibiz-core';

/**
 * 产品生命周期
 *
 * @export
 * @interface IProductLife
 * @extends {IEntityBase}
 */
export interface IProductLife extends IEntityBase {
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
     * 编号
     */
    product?: any;
}
