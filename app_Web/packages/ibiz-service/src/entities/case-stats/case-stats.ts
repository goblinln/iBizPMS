import { CaseStatsBase } from './case-stats-base';

/**
 * 测试用例统计
 *
 * @export
 * @class CaseStats
 * @extends {CaseStatsBase}
 * @implements {ICaseStats}
 */
export class CaseStats extends CaseStatsBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof CaseStats
     */
    clone(): CaseStats {
        return new CaseStats(this);
    }
}
export default CaseStats;
