import { ProjectAuthServiceBase } from './project-auth-service-base';


/**
 * 项目权限服务对象
 *
 * @export
 * @class ProjectAuthService
 * @extends {ProjectAuthServiceBase}
 */
export default class ProjectAuthService extends ProjectAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectAuthService}
     * @memberof ProjectAuthService
     */
    private static basicUIServiceInstance: ProjectAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectAuthService
     */
     public static getInstance(context: any): ProjectAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectAuthService({context:context}));
            }
            return ProjectAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}