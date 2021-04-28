import { UserContactAuthServiceBase } from './user-contact-auth-service-base';


/**
 * 用户联系方式权限服务对象
 *
 * @export
 * @class UserContactAuthService
 * @extends {UserContactAuthServiceBase}
 */
export default class UserContactAuthService extends UserContactAuthServiceBase {

    /**
     * Creates an instance of  UserContactAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserContactAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}