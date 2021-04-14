import { IbzReportRoleConfigBaseService } from './ibz-report-role-config-base.service';

/**
 * 汇报角色配置服务
 *
 * @export
 * @class IbzReportRoleConfigService
 * @extends {IbzReportRoleConfigBaseService}
 */
export class IbzReportRoleConfigService extends IbzReportRoleConfigBaseService {
    /**
     * Creates an instance of IbzReportRoleConfigService.
     * @memberof IbzReportRoleConfigService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzReportRoleConfigService')) {
            return ___ibz___.sc.get('IbzReportRoleConfigService');
        }
        ___ibz___.sc.set('IbzReportRoleConfigService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzReportRoleConfigService}
     * @memberof IbzReportRoleConfigService
     */
    static getInstance(): IbzReportRoleConfigService {
        if (!___ibz___.sc.has('IbzReportRoleConfigService')) {
            new IbzReportRoleConfigService();
        }
        return ___ibz___.sc.get('IbzReportRoleConfigService');
    }
}
export default IbzReportRoleConfigService;