import { BugStatsAuthServiceBase } from './bug-stats-auth-service-base';


/**
 * Bug统计权限服务对象
 *
 * @export
 * @class BugStatsAuthService
 * @extends {BugStatsAuthServiceBase}
 */
export default class BugStatsAuthService extends BugStatsAuthServiceBase {

    /**
     * Creates an instance of  BugStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  BugStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}