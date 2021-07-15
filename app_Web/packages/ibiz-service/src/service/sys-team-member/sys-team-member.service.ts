import { SysTeamMemberBaseService } from './sys-team-member-base.service';

/**
 * 组成员服务
 *
 * @export
 * @class SysTeamMemberService
 * @extends {SysTeamMemberBaseService}
 */
export class SysTeamMemberService extends SysTeamMemberBaseService {
    /**
     * Creates an instance of SysTeamMemberService.
     * @memberof SysTeamMemberService
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
     * @return {*}  {SysTeamMemberService}
     * @memberof SysTeamMemberService
     */
    static getInstance(context?: any): SysTeamMemberService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysTeamMemberService` : `SysTeamMemberService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysTeamMemberService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysTeamMemberService;
