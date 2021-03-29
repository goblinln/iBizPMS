import { BugAuthServiceBase } from './bug-auth-service-base';


/**
 * Bug权限服务对象
 *
 * @export
 * @class BugAuthService
 * @extends {BugAuthServiceBase}
 */
export default class BugAuthService extends BugAuthServiceBase {

    /**
     * Creates an instance of  BugAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  BugAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}