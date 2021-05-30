import { ProjectStoryAuthServiceBase } from './project-story-auth-service-base';


/**
 * 需求权限服务对象
 *
 * @export
 * @class ProjectStoryAuthService
 * @extends {ProjectStoryAuthServiceBase}
 */
export default class ProjectStoryAuthService extends ProjectStoryAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectStoryAuthService}
     * @memberof ProjectStoryAuthService
     */
    private static basicUIServiceInstance: ProjectStoryAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectStoryAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectStoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectStoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectStoryAuthService
     */
     public static getInstance(context: any): ProjectStoryAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectStoryAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectStoryAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectStoryAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectStoryAuthService({context:context}));
            }
            return ProjectStoryAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}