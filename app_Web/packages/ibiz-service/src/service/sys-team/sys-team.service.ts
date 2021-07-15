import { SysTeamBaseService } from './sys-team-base.service';

/**
 * 组服务
 *
 * @export
 * @class SysTeamService
 * @extends {SysTeamBaseService}
 */
export class SysTeamService extends SysTeamBaseService {
    /**
     * Creates an instance of SysTeamService.
     * @memberof SysTeamService
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
     * @return {*}  {SysTeamService}
     * @memberof SysTeamService
     */
    static getInstance(context?: any): SysTeamService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysTeamService` : `SysTeamService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysTeamService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysTeamService;
