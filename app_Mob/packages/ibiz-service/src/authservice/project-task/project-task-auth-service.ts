import { ProjectTaskAuthServiceBase } from './project-task-auth-service-base';


/**
 * 任务权限服务对象
 *
 * @export
 * @class ProjectTaskAuthService
 * @extends {ProjectTaskAuthServiceBase}
 */
export default class ProjectTaskAuthService extends ProjectTaskAuthServiceBase {

    /**
     * Creates an instance of  ProjectTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}