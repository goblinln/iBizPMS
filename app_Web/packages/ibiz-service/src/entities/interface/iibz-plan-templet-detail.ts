import { IEntityBase } from 'ibiz-core';

/**
 * 计划模板详情
 *
 * @export
 * @interface IIbzPlanTempletDetail
 * @extends {IEntityBase}
 */
export interface IIbzPlanTempletDetail extends IEntityBase {
    /**
     * 类型
     *
     * @type {('step' | 'group' | 'item')} step: 计划, group: 父计划, item: 子计划
     */
    type?: 'step' | 'group' | 'item';
    /**
     * 计划编号
     */
    plancode?: any;
    /**
     * 计划名称
     */
    desc?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 计划模板详情标识
     */
    ibzplantempletdetailid?: any;
    /**
     * 计划模板详情名称
     */
    ibzplantempletdetailname?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 描述
     */
    expect?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 产品计划模板标识
     */
    plantempletid?: any;
}
