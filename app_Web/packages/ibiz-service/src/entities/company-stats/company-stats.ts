import { CompanyStatsBase } from './company-stats-base';

/**
 * 公司动态汇总
 *
 * @export
 * @class CompanyStats
 * @extends {CompanyStatsBase}
 * @implements {ICompanyStats}
 */
export class CompanyStats extends CompanyStatsBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof CompanyStats
     */
    clone(): CompanyStats {
        return new CompanyStats(this);
    }
}
export default CompanyStats;
