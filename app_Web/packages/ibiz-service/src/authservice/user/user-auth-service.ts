import { UserAuthServiceBase } from './user-auth-service-base';


/**
 * 用户权限服务对象
 *
 * @export
 * @class UserAuthService
 * @extends {UserAuthServiceBase}
 */
export default class UserAuthService extends UserAuthServiceBase {

    /**
     * Creates an instance of  UserAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}