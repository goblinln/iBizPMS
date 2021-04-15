import { TaskTeamAuthServiceBase } from './task-team-auth-service-base';


/**
 * 任务团队权限服务对象
 *
 * @export
 * @class TaskTeamAuthService
 * @extends {TaskTeamAuthServiceBase}
 */
export default class TaskTeamAuthService extends TaskTeamAuthServiceBase {

    /**
     * Creates an instance of  TaskTeamAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskTeamAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}