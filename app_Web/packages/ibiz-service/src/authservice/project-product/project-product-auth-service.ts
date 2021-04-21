import { ProjectProductAuthServiceBase } from './project-product-auth-service-base';


/**
 * 项目产品权限服务对象
 *
 * @export
 * @class ProjectProductAuthService
 * @extends {ProjectProductAuthServiceBase}
 */
export default class ProjectProductAuthService extends ProjectProductAuthServiceBase {

    /**
     * Creates an instance of  ProjectProductAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectProductAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}