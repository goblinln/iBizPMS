import { UserYearWorkStatsBaseService } from './user-year-work-stats-base.service';

/**
 * 用户年度工作内容统计服务
 *
 * @export
 * @class UserYearWorkStatsService
 * @extends {UserYearWorkStatsBaseService}
 */
export class UserYearWorkStatsService extends UserYearWorkStatsBaseService {
    /**
     * Creates an instance of UserYearWorkStatsService.
     * @memberof UserYearWorkStatsService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('UserYearWorkStatsService')) {
            return ___ibz___.sc.get('UserYearWorkStatsService');
        }
        ___ibz___.sc.set('UserYearWorkStatsService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {UserYearWorkStatsService}
     * @memberof UserYearWorkStatsService
     */
    static getInstance(): UserYearWorkStatsService {
        if (!___ibz___.sc.has('UserYearWorkStatsService')) {
            new UserYearWorkStatsService();
        }
        return ___ibz___.sc.get('UserYearWorkStatsService');
    }
}
export default UserYearWorkStatsService;