import { ProjectStatsAuthServiceBase } from './project-stats-auth-service-base';


/**
 * 项目统计权限服务对象
 *
 * @export
 * @class ProjectStatsAuthService
 * @extends {ProjectStatsAuthServiceBase}
 */
export default class ProjectStatsAuthService extends ProjectStatsAuthServiceBase {

    /**
     * Creates an instance of  ProjectStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}