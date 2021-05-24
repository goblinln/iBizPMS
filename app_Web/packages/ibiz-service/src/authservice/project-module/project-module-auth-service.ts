import { ProjectModuleAuthServiceBase } from './project-module-auth-service-base';


/**
 * 任务模块权限服务对象
 *
 * @export
 * @class ProjectModuleAuthService
 * @extends {ProjectModuleAuthServiceBase}
 */
export default class ProjectModuleAuthService extends ProjectModuleAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectModuleAuthService}
     * @memberof ProjectModuleAuthService
     */
    private static basicUIServiceInstance: ProjectModuleAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectModuleAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectModuleAuthService
     */
     public static getInstance(context: any): ProjectModuleAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectModuleAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectModuleAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectModuleAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectModuleAuthService({context:context}));
            }
            return ProjectModuleAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}