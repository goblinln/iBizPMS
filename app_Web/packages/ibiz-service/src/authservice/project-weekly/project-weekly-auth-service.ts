import { ProjectWeeklyAuthServiceBase } from './project-weekly-auth-service-base';


/**
 * 项目周报权限服务对象
 *
 * @export
 * @class ProjectWeeklyAuthService
 * @extends {ProjectWeeklyAuthServiceBase}
 */
export default class ProjectWeeklyAuthService extends ProjectWeeklyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectWeeklyAuthService}
     * @memberof ProjectWeeklyAuthService
     */
    private static basicUIServiceInstance: ProjectWeeklyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectWeeklyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectWeeklyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectWeeklyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectWeeklyAuthService
     */
     public static getInstance(context: any): ProjectWeeklyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectWeeklyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectWeeklyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectWeeklyAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectWeeklyAuthService({context:context}));
            }
            return ProjectWeeklyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}