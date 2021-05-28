import { ProjectBurnAuthServiceBase } from './project-burn-auth-service-base';


/**
 * burn权限服务对象
 *
 * @export
 * @class ProjectBurnAuthService
 * @extends {ProjectBurnAuthServiceBase}
 */
export default class ProjectBurnAuthService extends ProjectBurnAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectBurnAuthService}
     * @memberof ProjectBurnAuthService
     */
    private static basicUIServiceInstance: ProjectBurnAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectBurnAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectBurnAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectBurnAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectBurnAuthService
     */
     public static getInstance(context: any): ProjectBurnAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectBurnAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectBurnAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectBurnAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectBurnAuthService({context:context}));
            }
            return ProjectBurnAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}