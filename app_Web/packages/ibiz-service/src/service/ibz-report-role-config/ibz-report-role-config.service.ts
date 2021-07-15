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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {IbzReportRoleConfigService}
     * @memberof IbzReportRoleConfigService
     */
    static getInstance(context?: any): IbzReportRoleConfigService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzReportRoleConfigService` : `IbzReportRoleConfigService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzReportRoleConfigService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzReportRoleConfigService;
