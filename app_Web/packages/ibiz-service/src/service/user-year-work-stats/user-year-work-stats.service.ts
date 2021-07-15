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
     * @return {*}  {UserYearWorkStatsService}
     * @memberof UserYearWorkStatsService
     */
    static getInstance(context?: any): UserYearWorkStatsService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}UserYearWorkStatsService` : `UserYearWorkStatsService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new UserYearWorkStatsService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default UserYearWorkStatsService;
