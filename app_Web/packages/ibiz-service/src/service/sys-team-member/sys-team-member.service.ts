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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysTeamMemberService')) {
            return ___ibz___.sc.get('SysTeamMemberService');
        }
        ___ibz___.sc.set('SysTeamMemberService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysTeamMemberService}
     * @memberof SysTeamMemberService
     */
    static getInstance(): SysTeamMemberService {
        if (!___ibz___.sc.has('SysTeamMemberService')) {
            new SysTeamMemberService();
        }
        return ___ibz___.sc.get('SysTeamMemberService');
    }
}
export default SysTeamMemberService;