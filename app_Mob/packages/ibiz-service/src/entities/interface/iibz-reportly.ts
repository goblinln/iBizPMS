import { IEntityBase } from 'ibiz-core';

/**
 * 汇报
 *
 * @export
 * @interface IIbzReportly
 * @extends {IEntityBase}
 */
export interface IIbzReportly extends IEntityBase {
    /**
     * 附件
     */
    files?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 抄送给
     */
    mailto?: any;
    /**
     * 汇报标识
     */
    ibzreportlyid?: any;
    /**
     * 汇报名称
     */
    ibzreportlyname?: any;
    /**
     * 状态
     *
     * @type {('0' | '1')} 0: 未读, 1: 已读
     */
    reportstatus?: '0' | '1';
    /**
     * 提交时间
     */
    submittime?: any;
    /**
     * 汇报给（选择）
     */
    reporttopk?: any;
    /**
     * 汇报给
     */
    reportto?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 是否提交
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    issubmit?: '1' | '0';
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 工作内容
     */
    content?: any;
    /**
     * 汇报日期
     */
    date?: any;
    /**
     * 抄送给（选择）
     */
    mailtopk?: any;
}
