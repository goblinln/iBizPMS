import { ProjectMonthlyAuthServiceBase } from './project-monthly-auth-service-base';


/**
 * 项目月报权限服务对象
 *
 * @export
 * @class ProjectMonthlyAuthService
 * @extends {ProjectMonthlyAuthServiceBase}
 */
export default class ProjectMonthlyAuthService extends ProjectMonthlyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectMonthlyAuthService}
     * @memberof ProjectMonthlyAuthService
     */
    private static basicUIServiceInstance: ProjectMonthlyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectMonthlyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectMonthlyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectMonthlyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectMonthlyAuthService
     */
     public static getInstance(context: any): ProjectMonthlyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectMonthlyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectMonthlyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectMonthlyAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectMonthlyAuthService({context:context}));
            }
            return ProjectMonthlyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}