import { TaskEstimateAuthServiceBase } from './task-estimate-auth-service-base';


/**
 * 任务预计权限服务对象
 *
 * @export
 * @class TaskEstimateAuthService
 * @extends {TaskEstimateAuthServiceBase}
 */
export default class TaskEstimateAuthService extends TaskEstimateAuthServiceBase {

    /**
     * Creates an instance of  TaskEstimateAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskEstimateAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}