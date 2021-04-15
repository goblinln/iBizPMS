import { CompanyStatsAuthServiceBase } from './company-stats-auth-service-base';


/**
 * 公司动态汇总权限服务对象
 *
 * @export
 * @class CompanyStatsAuthService
 * @extends {CompanyStatsAuthServiceBase}
 */
export default class CompanyStatsAuthService extends CompanyStatsAuthServiceBase {

    /**
     * Creates an instance of  CompanyStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  CompanyStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}