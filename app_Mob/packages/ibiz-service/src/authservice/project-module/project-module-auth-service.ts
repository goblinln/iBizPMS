import { ProjectModuleAuthServiceBase } from './project-module-auth-service-base';


/**
 * 任务模块权限服务对象
 *
 * @export
 * @class ProjectModuleAuthService
 * @extends {ProjectModuleAuthServiceBase}
 */
export default class ProjectModuleAuthService extends ProjectModuleAuthServiceBase {

    /**
     * Creates an instance of  ProjectModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}