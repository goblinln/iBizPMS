import { ProjectTestTaskAuthServiceBase } from './project-test-task-auth-service-base';


/**
 * 测试版本权限服务对象
 *
 * @export
 * @class ProjectTestTaskAuthService
 * @extends {ProjectTestTaskAuthServiceBase}
 */
export default class ProjectTestTaskAuthService extends ProjectTestTaskAuthServiceBase {

    /**
     * Creates an instance of  ProjectTestTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTestTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}