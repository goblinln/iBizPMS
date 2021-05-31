import { ProjectBuildAuthServiceBase } from './project-build-auth-service-base';


/**
 * 版本权限服务对象
 *
 * @export
 * @class ProjectBuildAuthService
 * @extends {ProjectBuildAuthServiceBase}
 */
export default class ProjectBuildAuthService extends ProjectBuildAuthServiceBase {

    /**
     * Creates an instance of  ProjectBuildAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectBuildAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}