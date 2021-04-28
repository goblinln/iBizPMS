import { ProjectTeamAuthServiceBase } from './project-team-auth-service-base';


/**
 * 项目团队权限服务对象
 *
 * @export
 * @class ProjectTeamAuthService
 * @extends {ProjectTeamAuthServiceBase}
 */
export default class ProjectTeamAuthService extends ProjectTeamAuthServiceBase {

    /**
     * Creates an instance of  ProjectTeamAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTeamAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}