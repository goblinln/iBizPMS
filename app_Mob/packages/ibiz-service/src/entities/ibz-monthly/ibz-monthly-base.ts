import { EntityBase } from 'ibiz-core';
import { IIbzMonthly } from '../interface';

/**
 * 月报基类
 *
 * @export
 * @abstract
 * @class IbzMonthlyBase
 * @extends {EntityBase}
 * @implements {IIbzMonthly}
 */
export abstract class IbzMonthlyBase extends EntityBase implements IIbzMonthly {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbzMonthlyBase
     */
    get srfdename(): string {
        return 'IBZ_MONTHLY';
    }
    get srfkey() {
        return this.ibzmonthlyid;
    }
    set srfkey(val: any) {
        this.ibzmonthlyid = val;
    }
    get srfmajortext() {
        return this.ibzmonthlyname;
    }
    set srfmajortext(val: any) {
        this.ibzmonthlyname = val;
    }
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 状态
     *
     * @type {('0' | '1')} 0: 未读, 1: 已读
     */
    reportstatus?: '0' | '1';
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 下月计划
     */
    plansnextmonth?: any;
    /**
     * 汇报给（选择）
     */
    reporttopk?: any;
    /**
     * 是否提交
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    issubmit?: '1' | '0';
    /**
     * 抄送给（选择）
     */
    mailtopk?: any;
    /**
     * 更新人名称
     */
    updatemanname?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 提交时间
     */
    submittime?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 建立人名称
     */
    createmanname?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 月报标识
     */
    ibzmonthlyid?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 下月计划任务
     */
    nextmonthplanstask?: any;
    /**
     * 本月完成任务
     */
    thismonthtask?: any;
    /**
     * 本月工作
     */
    workthismonth?: any;
    /**
     * 汇报给
     */
    reportto?: any;
    /**
     * 月报名称
     */
    ibzmonthlyname?: any;
    /**
     * 其他事项
     */
    comment?: any;
    /**
     * 抄送给
     */
    mailto?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbzMonthlyBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibzmonthlyid = data.ibzmonthlyid || data.srfkey;
        this.ibzmonthlyname = data.ibzmonthlyname || data.srfmajortext;
    }
}
