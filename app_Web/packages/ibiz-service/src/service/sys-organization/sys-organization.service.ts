import { SysOrganizationBaseService } from './sys-organization-base.service';

/**
 * 单位服务
 *
 * @export
 * @class SysOrganizationService
 * @extends {SysOrganizationBaseService}
 */
export class SysOrganizationService extends SysOrganizationBaseService {
    /**
     * Creates an instance of SysOrganizationService.
     * @memberof SysOrganizationService
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
     * @return {*}  {SysOrganizationService}
     * @memberof SysOrganizationService
     */
    static getInstance(context?: any): SysOrganizationService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysOrganizationService` : `SysOrganizationService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysOrganizationService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysOrganizationService;
