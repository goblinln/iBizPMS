import { IbzReportRoleConfigBase } from './ibz-report-role-config-base';

/**
 * 汇报角色配置
 *
 * @export
 * @class IbzReportRoleConfig
 * @extends {IbzReportRoleConfigBase}
 * @implements {IIbzReportRoleConfig}
 */
export class IbzReportRoleConfig extends IbzReportRoleConfigBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzReportRoleConfig
     */
    clone(): IbzReportRoleConfig {
        return new IbzReportRoleConfig(this);
    }
}
export default IbzReportRoleConfig;
