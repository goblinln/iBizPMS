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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysTeamService')) {
            return ___ibz___.sc.get('SysTeamService');
        }
        ___ibz___.sc.set('SysTeamService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysTeamService}
     * @memberof SysTeamService
     */
    static getInstance(): SysTeamService {
        if (!___ibz___.sc.has('SysTeamService')) {
            new SysTeamService();
        }
        return ___ibz___.sc.get('SysTeamService');
    }
}
export default SysTeamService;
