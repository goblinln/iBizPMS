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
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectTeamAuthService}
     * @memberof ProjectTeamAuthService
     */
    private static basicUIServiceInstance: ProjectTeamAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectTeamAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTeamAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTeamAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTeamAuthService
     */
     public static getInstance(context: any): ProjectTeamAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTeamAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTeamAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectTeamAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectTeamAuthService({context:context}));
            }
            return ProjectTeamAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}