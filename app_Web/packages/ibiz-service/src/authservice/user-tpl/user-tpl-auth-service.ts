import { UserTplAuthServiceBase } from './user-tpl-auth-service-base';


/**
 * 用户模板权限服务对象
 *
 * @export
 * @class UserTplAuthService
 * @extends {UserTplAuthServiceBase}
 */
export default class UserTplAuthService extends UserTplAuthServiceBase {

    /**
     * Creates an instance of  UserTplAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserTplAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}