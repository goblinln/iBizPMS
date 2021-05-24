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
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectStatsAuthService}
     * @memberof ProjectStatsAuthService
     */
    private static basicUIServiceInstance: ProjectStatsAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectStatsAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectStatsAuthService
     */
     public static getInstance(context: any): ProjectStatsAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectStatsAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectStatsAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectStatsAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectStatsAuthService({context:context}));
            }
            return ProjectStatsAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}