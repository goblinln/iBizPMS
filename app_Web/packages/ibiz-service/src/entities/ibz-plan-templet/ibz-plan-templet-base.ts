import { EntityBase } from 'ibiz-core';
import { IIbzPlanTemplet } from '../interface';

/**
 * 计划模板基类
 *
 * @export
 * @abstract
 * @class IbzPlanTempletBase
 * @extends {EntityBase}
 * @implements {IIbzPlanTemplet}
 */
export abstract class IbzPlanTempletBase extends EntityBase implements IIbzPlanTemplet {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbzPlanTempletBase
     */
    get srfdename(): string {
        return 'IBZ_PLANTEMPLET';
    }
    get srfkey() {
        return this.ibzplantempletid;
    }
    set srfkey(val: any) {
        this.ibzplantempletid = val;
    }
    get srfmajortext() {
        return this.ibzplantempletname;
    }
    set srfmajortext(val: any) {
        this.ibzplantempletname = val;
    }
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbzPlanTempletBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibzplantempletid = data.ibzplantempletid || data.srfkey;
        this.ibzplantempletname = data.ibzplantempletname || data.srfmajortext;
    }
}
