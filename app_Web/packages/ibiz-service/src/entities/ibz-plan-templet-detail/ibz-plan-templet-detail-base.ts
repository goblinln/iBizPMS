import { EntityBase } from 'ibiz-core';
import { IIbzPlanTempletDetail } from '../interface';

/**
 * 计划模板详情基类
 *
 * @export
 * @abstract
 * @class IbzPlanTempletDetailBase
 * @extends {EntityBase}
 * @implements {IIbzPlanTempletDetail}
 */
export abstract class IbzPlanTempletDetailBase extends EntityBase implements IIbzPlanTempletDetail {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbzPlanTempletDetailBase
     */
    get srfdename(): string {
        return 'IBZ_PLANTEMPLETDETAIL';
    }
    get srfkey() {
        return this.ibzplantempletdetailid;
    }
    set srfkey(val: any) {
        this.ibzplantempletdetailid = val;
    }
    get srfmajortext() {
        return this.ibzplantempletdetailname;
    }
    set srfmajortext(val: any) {
        this.ibzplantempletdetailname = val;
    }
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbzPlanTempletDetailBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibzplantempletdetailid = data.ibzplantempletdetailid || data.srfkey;
        this.ibzplantempletdetailname = data.ibzplantempletdetailname || data.srfmajortext;
    }
}
