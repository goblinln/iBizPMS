import { GroupAuthServiceBase } from './group-auth-service-base';


/**
 * 群组权限服务对象
 *
 * @export
 * @class GroupAuthService
 * @extends {GroupAuthServiceBase}
 */
export default class GroupAuthService extends GroupAuthServiceBase {

    /**
     * Creates an instance of  GroupAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  GroupAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}