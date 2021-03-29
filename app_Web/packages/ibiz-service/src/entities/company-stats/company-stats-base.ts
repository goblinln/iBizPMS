import { EntityBase } from 'ibiz-core';
import { ICompanyStats } from '../interface';

/**
 * 公司动态汇总基类
 *
 * @export
 * @abstract
 * @class CompanyStatsBase
 * @extends {EntityBase}
 * @implements {ICompanyStats}
 */
export abstract class CompanyStatsBase extends EntityBase implements ICompanyStats {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof CompanyStatsBase
     */
    get srfdename(): string {
        return 'IBZ_COMPANYSTATS';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.comment;
    }
    set srfmajortext(val: any) {
        this.comment = val;
    }
    /**
     * 登录次数
     */
    logincnt?: any;
    /**
     * 新增需求数
     */
    openedstorycnt?: any;
    /**
     * 日期
     */
    ztdate?: any;
    /**
     * 日志日期
     */
    date?: any;
    /**
     * 关闭需求数
     */
    closedstorycnt?: any;
    /**
     * 新增Bug数
     */
    openedbugcnt?: any;
    /**
     * 日志工时
     */
    loghours?: any;
    /**
     * 动态数
     */
    dynamiccnt?: any;
    /**
     * 完成任务数
     */
    finishedtaskcnt?: any;
    /**
     * 解决Bug数
     */
    resolvedbugcnt?: any;
    /**
     * 备注
     */
    comment?: any;
    /**
     * 新增任务数
     */
    openedtaskcnt?: any;
    /**
     * 标识
     */
    id?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof CompanyStatsBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.comment = data.comment || data.srfmajortext;
    }
}
