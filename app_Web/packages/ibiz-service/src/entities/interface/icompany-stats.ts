import { IEntityBase } from 'ibiz-core';

/**
 * 公司动态汇总
 *
 * @export
 * @interface ICompanyStats
 * @extends {IEntityBase}
 */
export interface ICompanyStats extends IEntityBase {
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
}
