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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('BugStatsService')) {
            return ___ibz___.sc.get('BugStatsService');
        }
        ___ibz___.sc.set('BugStatsService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {BugStatsService}
     * @memberof BugStatsService
     */
    static getInstance(): BugStatsService {
        if (!___ibz___.sc.has('BugStatsService')) {
            new BugStatsService();
        }
        return ___ibz___.sc.get('BugStatsService');
    }
}
export default BugStatsService;