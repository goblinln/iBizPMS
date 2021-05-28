import { ProjectTaskEstimateAuthServiceBase } from './project-task-estimate-auth-service-base';


/**
 * 任务预计权限服务对象
 *
 * @export
 * @class ProjectTaskEstimateAuthService
 * @extends {ProjectTaskEstimateAuthServiceBase}
 */
export default class ProjectTaskEstimateAuthService extends ProjectTaskEstimateAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectTaskEstimateAuthService}
     * @memberof ProjectTaskEstimateAuthService
     */
    private static basicUIServiceInstance: ProjectTaskEstimateAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectTaskEstimateAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTaskEstimateAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskEstimateAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTaskEstimateAuthService
     */
     public static getInstance(context: any): ProjectTaskEstimateAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTaskEstimateAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTaskEstimateAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectTaskEstimateAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectTaskEstimateAuthService({context:context}));
            }
            return ProjectTaskEstimateAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}