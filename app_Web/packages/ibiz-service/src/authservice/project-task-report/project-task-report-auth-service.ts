import { ProjectTaskReportAuthServiceBase } from './project-task-report-auth-service-base';


/**
 * 任务权限服务对象
 *
 * @export
 * @class ProjectTaskReportAuthService
 * @extends {ProjectTaskReportAuthServiceBase}
 */
export default class ProjectTaskReportAuthService extends ProjectTaskReportAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectTaskReportAuthService}
     * @memberof ProjectTaskReportAuthService
     */
    private static basicUIServiceInstance: ProjectTaskReportAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectTaskReportAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTaskReportAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskReportAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTaskReportAuthService
     */
     public static getInstance(context: any): ProjectTaskReportAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTaskReportAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTaskReportAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectTaskReportAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectTaskReportAuthService({context:context}));
            }
            return ProjectTaskReportAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}