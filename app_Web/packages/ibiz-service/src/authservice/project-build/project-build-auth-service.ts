import { ProjectBuildAuthServiceBase } from './project-build-auth-service-base';


/**
 * 版本权限服务对象
 *
 * @export
 * @class ProjectBuildAuthService
 * @extends {ProjectBuildAuthServiceBase}
 */
export default class ProjectBuildAuthService extends ProjectBuildAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectBuildAuthService}
     * @memberof ProjectBuildAuthService
     */
    private static basicUIServiceInstance: ProjectBuildAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectBuildAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectBuildAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectBuildAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectBuildAuthService
     */
     public static getInstance(context: any): ProjectBuildAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectBuildAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectBuildAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectBuildAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectBuildAuthService({context:context}));
            }
            return ProjectBuildAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}