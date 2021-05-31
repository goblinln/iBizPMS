import { BugStatsBase } from './bug-stats-base';

/**
 * Bug统计
 *
 * @export
 * @class BugStats
 * @extends {BugStatsBase}
 * @implements {IBugStats}
 */
export class BugStats extends BugStatsBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof BugStats
     */
    clone(): BugStats {
        return new BugStats(this);
    }
}
export default BugStats;
