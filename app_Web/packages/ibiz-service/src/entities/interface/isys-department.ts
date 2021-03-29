import { IEntityBase } from 'ibiz-core';

/**
 * 部门
 *
 * @export
 * @interface ISysDepartment
 * @extends {IEntityBase}
 */
export interface ISysDepartment extends IEntityBase {
    /**
     * 部门标识
     */
    deptid?: any;
    /**
     * 部门代码
     */
    deptcode?: any;
    /**
     * 部门名称
     */
    deptname?: any;
    /**
     * 单位
     */
    orgid?: any;
    /**
     * 上级部门
     */
    parentdeptid?: any;
    /**
     * 部门简称
     */
    shortname?: any;
    /**
     * 部门级别
     */
    deptlevel?: any;
    /**
     * 区属
     */
    domains?: any;
    /**
     * 排序
     */
    showorder?: any;
    /**
     * 业务编码
     */
    bcode?: any;
    /**
     * 分管领导标识
     */
    leaderid?: any;
    /**
     * 分管领导
     */
    leadername?: any;
    /**
     * 单位
     */
    orgname?: any;
    /**
     * 上级部门
     */
    parentdeptname?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 逻辑有效标志
     */
    enable?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立人
     */
    createman?: any;
}
