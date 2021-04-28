import { TaskAuthServiceBase } from './task-auth-service-base';


/**
 * 任务权限服务对象
 *
 * @export
 * @class TaskAuthService
 * @extends {TaskAuthServiceBase}
 */
export default class TaskAuthService extends TaskAuthServiceBase {

    /**
     * Creates an instance of  TaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}