import { IEntityBase } from 'ibiz-core';

/**
 * 系统更新功能
 *
 * @export
 * @interface ISysUpdateFeatures
 * @extends {IEntityBase}
 */
export interface ISysUpdateFeatures extends IEntityBase {
    /**
     * 系统更新功能名称
     */
    sysupdatefeaturesname?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 更新类型
     *
     * @type {('10' | '20')} 10: 功能增强, 20: 优化
     */
    type?: '10' | '20';
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新功能
     */
    upfeatures?: any;
    /**
     * 系统更新功能标识
     */
    sysupdatefeaturesid?: any;
    /**
     * 展示顺序
     */
    displayorder?: any;
    /**
     * 功能描述
     */
    featuresdesc?: any;
    /**
     * 所属更新
     */
    sysupdatelogname?: any;
    /**
     * 系统更新日志标识
     */
    sysupdatelogid?: any;
}
