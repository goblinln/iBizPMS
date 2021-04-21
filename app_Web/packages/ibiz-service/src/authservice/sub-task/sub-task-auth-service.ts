import { SubTaskAuthServiceBase } from './sub-task-auth-service-base';


/**
 * 任务权限服务对象
 *
 * @export
 * @class SubTaskAuthService
 * @extends {SubTaskAuthServiceBase}
 */
export default class SubTaskAuthService extends SubTaskAuthServiceBase {

    /**
     * Creates an instance of  SubTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SubTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}