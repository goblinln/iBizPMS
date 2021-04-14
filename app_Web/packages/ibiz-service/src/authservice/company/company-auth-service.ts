import { CompanyAuthServiceBase } from './company-auth-service-base';


/**
 * 公司权限服务对象
 *
 * @export
 * @class CompanyAuthService
 * @extends {CompanyAuthServiceBase}
 */
export default class CompanyAuthService extends CompanyAuthServiceBase {

    /**
     * Creates an instance of  CompanyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  CompanyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}