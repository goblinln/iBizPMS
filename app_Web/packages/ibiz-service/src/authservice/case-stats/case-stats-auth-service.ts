import { CaseStatsAuthServiceBase } from './case-stats-auth-service-base';


/**
 * 测试用例统计权限服务对象
 *
 * @export
 * @class CaseStatsAuthService
 * @extends {CaseStatsAuthServiceBase}
 */
export default class CaseStatsAuthService extends CaseStatsAuthServiceBase {

    /**
     * Creates an instance of  CaseStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}