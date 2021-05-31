import { ProjectTaskAuthServiceBase } from './project-task-auth-service-base';


/**
 * 任务权限服务对象
 *
 * @export
 * @class ProjectTaskAuthService
 * @extends {ProjectTaskAuthServiceBase}
 */
export default class ProjectTaskAuthService extends ProjectTaskAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectTaskAuthService}
     * @memberof ProjectTaskAuthService
     */
    private static basicUIServiceInstance: ProjectTaskAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectTaskAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTaskAuthService
     */
     public static getInstance(context: any): ProjectTaskAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTaskAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTaskAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectTaskAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectTaskAuthService({context:context}));
            }
            return ProjectTaskAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}