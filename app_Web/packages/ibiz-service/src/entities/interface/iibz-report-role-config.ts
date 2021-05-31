import { IEntityBase } from 'ibiz-core';

/**
 * 汇报角色配置
 *
 * @export
 * @interface IIbzReportRoleConfig
 * @extends {IEntityBase}
 */
export interface IIbzReportRoleConfig extends IEntityBase {
    /**
     * 汇报角色配置名称
     */
    ibzreportroleconfigname?: any;
    /**
     * 汇报角色配置标识
     */
    ibzreportroleconfigid?: any;
    /**
     * 角色
     */
    report_role?: any;
    /**
     * 类型
     *
     * @type {('weekly' | 'daily' | 'monthly' | 'reportly')} weekly: 周报, daily: 日报, monthly: 月报, reportly: 汇报
     */
    type?: 'weekly' | 'daily' | 'monthly' | 'reportly';
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新人
     */
    updateman?: any;
}
