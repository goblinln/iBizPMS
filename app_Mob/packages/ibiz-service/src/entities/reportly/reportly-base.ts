import { EntityBase } from 'ibiz-core';
import { IReportly } from '../interface';

/**
 * 汇报基类
 *
 * @export
 * @abstract
 * @class ReportlyBase
 * @extends {EntityBase}
 * @implements {IReportly}
 */
export abstract class ReportlyBase extends EntityBase implements IReportly {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ReportlyBase
     */
    get srfdename(): string {
        return 'IBZ_REPORTLY';
    }
    get srfkey() {
        return this.ibzreportlyid;
    }
    set srfkey(val: any) {
        this.ibzreportlyid = val;
    }
    get srfmajortext() {
        return this.ibzreportlyname;
    }
    set srfmajortext(val: any) {
        this.ibzreportlyname = val;
    }
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ReportlyBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibzreportlyid = data.ibzreportlyid || data.srfkey;
        this.ibzreportlyname = data.ibzreportlyname || data.srfmajortext;
    }
}
