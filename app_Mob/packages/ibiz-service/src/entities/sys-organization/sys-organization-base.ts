import { EntityBase } from 'ibiz-core';
import { ISysOrganization } from '../interface';

/**
 * 单位基类
 *
 * @export
 * @abstract
 * @class SysOrganizationBase
 * @extends {EntityBase}
 * @implements {ISysOrganization}
 */
export abstract class SysOrganizationBase extends EntityBase implements ISysOrganization {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SysOrganizationBase
     */
    get srfdename(): string {
        return 'SYS_ORG';
    }
    get srfkey() {
        return this.orgid;
    }
    set srfkey(val: any) {
        this.orgid = val;
    }
    get srfmajortext() {
        return this.orgname;
    }
    set srfmajortext(val: any) {
        this.orgname = val;
    }
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
    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SysOrganizationBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.orgid = data.orgid || data.srfkey;
        this.orgname = data.orgname || data.srfmajortext;
    }
}
