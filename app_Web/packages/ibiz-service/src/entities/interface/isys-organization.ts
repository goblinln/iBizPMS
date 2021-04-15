import { IEntityBase } from 'ibiz-core';

/**
 * 单位
 *
 * @export
 * @interface ISysOrganization
 * @extends {IEntityBase}
 */
export interface ISysOrganization extends IEntityBase {
    /**
     * 单位标识
     */
    orgid?: any;
    /**
     * 单位代码
     */
    orgcode?: any;
    /**
     * 名称
     */
    orgname?: any;
    /**
     * 上级单位
     */
    parentorgid?: any;
    /**
     * 单位简称
     */
    shortname?: any;
    /**
     * 单位级别
     */
    orglevel?: any;
    /**
     * 排序
     */
    showorder?: any;
    /**
     * 上级单位
     */
    parentorgname?: any;
    /**
     * 区属
     */
    domains?: any;
    /**
     * 逻辑有效
     */
    enable?: any;
    /**
     * 创建时间
     */
    createdate?: any;
    /**
     * 最后修改时间
     */
    updatedate?: any;
}
