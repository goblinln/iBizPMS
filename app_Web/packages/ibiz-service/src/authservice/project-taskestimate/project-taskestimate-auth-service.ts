import { ProjectTaskestimateAuthServiceBase } from './project-taskestimate-auth-service-base';


/**
 * 项目工时统计权限服务对象
 *
 * @export
 * @class ProjectTaskestimateAuthService
 * @extends {ProjectTaskestimateAuthServiceBase}
 */
export default class ProjectTaskestimateAuthService extends ProjectTaskestimateAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectTaskestimateAuthService}
     * @memberof ProjectTaskestimateAuthService
     */
    private static basicUIServiceInstance: ProjectTaskestimateAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectTaskestimateAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTaskestimateAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskestimateAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTaskestimateAuthService
     */
     public static getInstance(context: any): ProjectTaskestimateAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTaskestimateAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTaskestimateAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectTaskestimateAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectTaskestimateAuthService({context:context}));
            }
            return ProjectTaskestimateAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}