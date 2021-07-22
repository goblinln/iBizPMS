import { IEntityBase } from 'ibiz-core';

/**
 * 汇报汇总
 *
 * @export
 * @interface IReportSummary
 * @extends {IEntityBase}
 */
export interface IReportSummary extends IEntityBase {
    /**
     * 更新人名称
     */
    updatemanname?: any;
    /**
     * 抄送给
     */
    mailto?: any;
    /**
     * 汇报标识
     */
    ibzdailyid?: any;
    /**
     * 未读汇报数
     */
    reportlycnt?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 工作
     */
    worktoday?: any;
    /**
     * 未读月报数
     */
    monthlycnt?: any;
    /**
     * 未读日报数
     */
    dailycnt?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 完成任务
     */
    todaytask?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 类型
     *
     * @type {('weekly' | 'daily' | 'monthly' | 'reportly')} weekly: 周报, daily: 日报, monthly: 月报, reportly: 汇报
     */
    type?: 'weekly' | 'daily' | 'monthly' | 'reportly';
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 汇报名称
     */
    ibzdailyname?: any;
    /**
     * 汇报给
     */
    reportto?: any;
    /**
     * 提交时间
     */
    submittime?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 建立人名称
     */
    createmanname?: any;
    /**
     * 计划
     */
    planstomorrow?: any;
    /**
     * 状态
     *
     * @type {('0' | '1')} 0: 未读, 1: 已读
     */
    reportstatus?: '0' | '1';
    /**
     * 日期
     */
    date?: any;
    /**
     * 是否提交
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    issubmit?: '1' | '0';
    /**
     * 其他事项
     */
    comment?: any;
    /**
     * 计划任务
     */
    tomorrowplanstask?: any;
}
