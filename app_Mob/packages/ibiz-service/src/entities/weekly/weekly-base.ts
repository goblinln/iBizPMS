import { EntityBase } from 'ibiz-core';
import { IWeekly } from '../interface';

/**
 * 周报基类
 *
 * @export
 * @abstract
 * @class WeeklyBase
 * @extends {EntityBase}
 * @implements {IWeekly}
 */
export abstract class WeeklyBase extends EntityBase implements IWeekly {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof WeeklyBase
     */
    get srfdename(): string {
        return 'IBZ_WEEKLY';
    }
    get srfkey() {
        return this.ibzweeklyid;
    }
    set srfkey(val: any) {
        this.ibzweeklyid = val;
    }
    get srfmajortext() {
        return this.ibzweeklyname;
    }
    set srfmajortext(val: any) {
        this.ibzweeklyname = val;
    }
    /**
     * 是否提交
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    issubmit?: '1' | '0';
    /**
     * 下周计划
     */
    plannextweek?: any;
    /**
     * 周报名称
     */
    ibzweeklyname?: any;
    /**
     * 抄送给
     */
    mailto?: any;
    /**
     * 汇报给(选择)
     */
    reporttopk?: any;
    /**
     * 下周计划任务
     */
    nextweektask?: any;
    /**
     * 周报标识
     */
    ibzweeklyid?: any;
    /**
     * 提交时间
     */
    submittime?: any;
    /**
     * 抄送给(选择)
     */
    mailtopk?: any;
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
     * 附件
     */
    files?: any;
    /**
     * 本周工作
     */
    workthisweek?: any;
    /**
     * 汇报给
     */
    reportto?: any;
    /**
     * 更新人名称
     */
    updatemanname?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 本周完成任务
     */
    thisweektask?: any;
    /**
     * 其他事项
     */
    comment?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 建立人名称
     */
    createmanname?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新时间
     */
    updatedate?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof WeeklyBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibzweeklyid = data.ibzweeklyid || data.srfkey;
        this.ibzweeklyname = data.ibzweeklyname || data.srfmajortext;
    }
}
