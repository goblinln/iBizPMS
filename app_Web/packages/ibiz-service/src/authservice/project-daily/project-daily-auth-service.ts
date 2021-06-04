import { ProjectDailyAuthServiceBase } from './project-daily-auth-service-base';


/**
 * 项目日报权限服务对象
 *
 * @export
 * @class ProjectDailyAuthService
 * @extends {ProjectDailyAuthServiceBase}
 */
export default class ProjectDailyAuthService extends ProjectDailyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectDailyAuthService}
     * @memberof ProjectDailyAuthService
     */
    private static basicUIServiceInstance: ProjectDailyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectDailyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectDailyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectDailyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectDailyAuthService
     */
     public static getInstance(context: any): ProjectDailyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectDailyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectDailyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectDailyAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectDailyAuthService({context:context}));
            }
            return ProjectDailyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}