import { PSSysAppAuthServiceBase } from './pssys-app-auth-service-base';


/**
 * 系统应用权限服务对象
 *
 * @export
 * @class PSSysAppAuthService
 * @extends {PSSysAppAuthServiceBase}
 */
export default class PSSysAppAuthService extends PSSysAppAuthServiceBase {

    /**
     * Creates an instance of  PSSysAppAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSysAppAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}