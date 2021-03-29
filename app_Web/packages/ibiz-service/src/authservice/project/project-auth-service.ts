import { ProjectAuthServiceBase } from './project-auth-service-base';


/**
 * 项目权限服务对象
 *
 * @export
 * @class ProjectAuthService
 * @extends {ProjectAuthServiceBase}
 */
export default class ProjectAuthService extends ProjectAuthServiceBase {

    /**
     * Creates an instance of  ProjectAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}