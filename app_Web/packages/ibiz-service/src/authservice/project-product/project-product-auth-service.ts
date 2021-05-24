import { ProjectProductAuthServiceBase } from './project-product-auth-service-base';


/**
 * 项目产品权限服务对象
 *
 * @export
 * @class ProjectProductAuthService
 * @extends {ProjectProductAuthServiceBase}
 */
export default class ProjectProductAuthService extends ProjectProductAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectProductAuthService}
     * @memberof ProjectProductAuthService
     */
    private static basicUIServiceInstance: ProjectProductAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectProductAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectProductAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectProductAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectProductAuthService
     */
     public static getInstance(context: any): ProjectProductAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectProductAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectProductAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectProductAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectProductAuthService({context:context}));
            }
            return ProjectProductAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}