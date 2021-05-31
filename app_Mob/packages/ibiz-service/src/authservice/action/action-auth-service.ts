import { ActionAuthServiceBase } from './action-auth-service-base';


/**
 * 系统日志权限服务对象
 *
 * @export
 * @class ActionAuthService
 * @extends {ActionAuthServiceBase}
 */
export default class ActionAuthService extends ActionAuthServiceBase {

    /**
     * Creates an instance of  ActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}