import { ProjectTestTaskAuthServiceBase } from './project-test-task-auth-service-base';


/**
 * 测试版本权限服务对象
 *
 * @export
 * @class ProjectTestTaskAuthService
 * @extends {ProjectTestTaskAuthServiceBase}
 */
export default class ProjectTestTaskAuthService extends ProjectTestTaskAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectTestTaskAuthService}
     * @memberof ProjectTestTaskAuthService
     */
    private static basicUIServiceInstance: ProjectTestTaskAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectTestTaskAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTestTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTestTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTestTaskAuthService
     */
     public static getInstance(context: any): ProjectTestTaskAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTestTaskAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTestTaskAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectTestTaskAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectTestTaskAuthService({context:context}));
            }
            return ProjectTestTaskAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}