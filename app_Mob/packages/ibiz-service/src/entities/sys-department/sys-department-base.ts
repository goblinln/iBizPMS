import { EntityBase } from 'ibiz-core';
import { ISysDepartment } from '../interface';

/**
 * 部门基类
 *
 * @export
 * @abstract
 * @class SysDepartmentBase
 * @extends {EntityBase}
 * @implements {ISysDepartment}
 */
export abstract class SysDepartmentBase extends EntityBase implements ISysDepartment {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SysDepartmentBase
     */
    get srfdename(): string {
        return 'SYS_DEPT';
    }
    get srfkey() {
        return this.deptid;
    }
    set srfkey(val: any) {
        this.deptid = val;
    }
    get srfmajortext() {
        return this.deptname;
    }
    set srfmajortext(val: any) {
        this.deptname = val;
    }
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
    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SysDepartmentBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.deptid = data.deptid || data.srfkey;
        this.deptname = data.deptname || data.srfmajortext;
    }
}
