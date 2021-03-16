import { IEntityBase } from 'ibiz-core';

/**
 * 计划模板
 *
 * @export
 * @interface IIbzPlanTemplet
 * @extends {IEntityBase}
 */
export interface IIbzPlanTemplet extends IEntityBase {
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 计划
     */
    plans?: any;
    /**
     * 权限
     *
     * @type {('open' | 'private')} open: 公开, private: 私有
     */
    acl?: 'open' | 'private';
    /**
     * 创建人姓名
     */
    createmanname?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 产品计划模板标识
     */
    ibzplantempletid?: any;
    /**
     * 计划项
     */
    plantempletdetail?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 模板名称
     */
    ibzplantempletname?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立人
     */
    createman?: any;
}
