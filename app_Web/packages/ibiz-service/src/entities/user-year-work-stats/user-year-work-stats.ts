import { UserYearWorkStatsBase } from './user-year-work-stats-base';

/**
 * 用户年度工作内容统计
 *
 * @export
 * @class UserYearWorkStats
 * @extends {UserYearWorkStatsBase}
 * @implements {IUserYearWorkStats}
 */
export class UserYearWorkStats extends UserYearWorkStatsBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof UserYearWorkStats
     */
    clone(): UserYearWorkStats {
        return new UserYearWorkStats(this);
    }
}
export default UserYearWorkStats;
