import { BugStatsBaseService } from './bug-stats-base.service';

/**
 * Bug统计服务
 *
 * @export
 * @class BugStatsService
 * @extends {BugStatsBaseService}
 */
export class BugStatsService extends BugStatsBaseService {
    /**
     * Creates an instance of BugStatsService.
     * @memberof BugStatsService
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
     * @return {*}  {BugStatsService}
     * @memberof BugStatsService
     */
    static getInstance(context?: any): BugStatsService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}BugStatsService` : `BugStatsService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new BugStatsService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default BugStatsService;
