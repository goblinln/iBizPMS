import { IBZTaskTeamBaseService } from './ibztask-team-base.service';

/**
 * 任务团队服务
 *
 * @export
 * @class IBZTaskTeamService
 * @extends {IBZTaskTeamBaseService}
 */
export class IBZTaskTeamService extends IBZTaskTeamBaseService {
    /**
     * Creates an instance of IBZTaskTeamService.
     * @memberof IBZTaskTeamService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZTaskTeamService')) {
            return ___ibz___.sc.get('IBZTaskTeamService');
        }
        ___ibz___.sc.set('IBZTaskTeamService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZTaskTeamService}
     * @memberof IBZTaskTeamService
     */
    static getInstance(): IBZTaskTeamService {
        if (!___ibz___.sc.has('IBZTaskTeamService')) {
            new IBZTaskTeamService();
        }
        return ___ibz___.sc.get('IBZTaskTeamService');
    }
}
export default IBZTaskTeamService;
