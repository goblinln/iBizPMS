import { EntityBase } from 'ibiz-core';
import { IDynaFilter } from '../interface';

/**
 * 动态搜索栏基类
 *
 * @export
 * @abstract
 * @class DynaFilterBase
 * @extends {EntityBase}
 * @implements {IDynaFilter}
 */
export abstract class DynaFilterBase extends EntityBase implements IDynaFilter {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof DynaFilterBase
     */
    get srfdename(): string {
        return 'DYNAFILTER';
    }
    get srfkey() {
        return this.dynafilterid;
    }
    set srfkey(val: any) {
        this.dynafilterid = val;
    }
    get srfmajortext() {
        return this.dynafiltername;
    }
    set srfmajortext(val: any) {
        this.dynafiltername = val;
    }
    /**
     * 动态搜索栏标识
     */
    dynafilterid?: any;
    /**
     * 组织部门标识
     */
    deptid?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 表单名称
     */
    formname?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 实体名称
     */
    dename?: any;
    /**
     * 动态搜索栏名称
     */
    dynafiltername?: any;
    /**
     * 数据
     */
    data?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof DynaFilterBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.dynafilterid = data.dynafilterid || data.srfkey;
        this.dynafiltername = data.dynafiltername || data.srfmajortext;
    }
}
