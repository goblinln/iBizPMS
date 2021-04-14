import { BurnAuthServiceBase } from './burn-auth-service-base';


/**
 * burn权限服务对象
 *
 * @export
 * @class BurnAuthService
 * @extends {BurnAuthServiceBase}
 */
export default class BurnAuthService extends BurnAuthServiceBase {

    /**
     * Creates an instance of  BurnAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  BurnAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}