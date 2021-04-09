import { EntityBase } from 'ibiz-core';
import { IIbzReportRoleConfig } from '../interface';

/**
 * 汇报角色配置基类
 *
 * @export
 * @abstract
 * @class IbzReportRoleConfigBase
 * @extends {EntityBase}
 * @implements {IIbzReportRoleConfig}
 */
export abstract class IbzReportRoleConfigBase extends EntityBase implements IIbzReportRoleConfig {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbzReportRoleConfigBase
     */
    get srfdename(): string {
        return 'IBZ_REPORT_ROLE_CONFIG';
    }
    get srfkey() {
        return this.ibzreportroleconfigid;
    }
    set srfkey(val: any) {
        this.ibzreportroleconfigid = val;
    }
    get srfmajortext() {
        return this.ibzreportroleconfigname;
    }
    set srfmajortext(val: any) {
        this.ibzreportroleconfigname = val;
    }
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbzReportRoleConfigBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibzreportroleconfigid = data.ibzreportroleconfigid || data.srfkey;
        this.ibzreportroleconfigname = data.ibzreportroleconfigname || data.srfmajortext;
    }
}
