import { IEntityBase } from 'ibiz-core';

/**
 * 日报
 *
 * @export
 * @interface IIbzDaily
 * @extends {IEntityBase}
 */
export interface IIbzDaily extends IEntityBase {
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 提交时间
     */
    submittime?: any;
    /**
     * 完成任务
     */
    todaytask?: any;
    /**
     * 今日工作
     */
    worktoday?: any;
    /**
     * 明日计划任务
     */
    tomorrowplanstask?: any;
    /**
     * 汇报给
     */
    reportto?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 是否提交
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    issubmit?: '1' | '0';
    /**
     * 日期
     */
    date?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 更新人名称
     */
    updatemanname?: any;
    /**
     * 抄送给（选择）
     */
    mailtopk?: any;
    /**
     * 状态
     *
     * @type {('0' | '1')} 0: 未读, 1: 已读
     */
    reportstatus?: '0' | '1';
    /**
     * 其他事项
     */
    comment?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 明日计划
     */
    planstomorrow?: any;
    /**
     * 抄送给
     */
    mailto?: any;
    /**
     * 日报标识
     */
    ibzdailyid?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 汇报给（选择）
     */
    reporttopk?: any;
    /**
     * 建立人名称
     */
    createmanname?: any;
    /**
     * 日报名称
     */
    ibzdailyname?: any;
}
