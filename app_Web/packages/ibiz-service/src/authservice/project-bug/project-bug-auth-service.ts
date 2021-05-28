import { ProjectBugAuthServiceBase } from './project-bug-auth-service-base';


/**
 * Bug权限服务对象
 *
 * @export
 * @class ProjectBugAuthService
 * @extends {ProjectBugAuthServiceBase}
 */
export default class ProjectBugAuthService extends ProjectBugAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectBugAuthService}
     * @memberof ProjectBugAuthService
     */
    private static basicUIServiceInstance: ProjectBugAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectBugAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectBugAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectBugAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectBugAuthService
     */
     public static getInstance(context: any): ProjectBugAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectBugAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectBugAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectBugAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectBugAuthService({context:context}));
            }
            return ProjectBugAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}