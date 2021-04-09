import { TaskStatsAuthServiceBase } from './task-stats-auth-service-base';


/**
 * 任务统计权限服务对象
 *
 * @export
 * @class TaskStatsAuthService
 * @extends {TaskStatsAuthServiceBase}
 */
export default class TaskStatsAuthService extends TaskStatsAuthServiceBase {

    /**
     * Creates an instance of  TaskStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}