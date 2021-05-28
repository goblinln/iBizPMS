import { ProjectTaskGanttAuthServiceBase } from './project-task-gantt-auth-service-base';


/**
 * 任务权限服务对象
 *
 * @export
 * @class ProjectTaskGanttAuthService
 * @extends {ProjectTaskGanttAuthServiceBase}
 */
export default class ProjectTaskGanttAuthService extends ProjectTaskGanttAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProjectTaskGanttAuthService}
     * @memberof ProjectTaskGanttAuthService
     */
    private static basicUIServiceInstance: ProjectTaskGanttAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProjectTaskGanttAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTaskGanttAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskGanttAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTaskGanttAuthService
     */
     public static getInstance(context: any): ProjectTaskGanttAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTaskGanttAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTaskGanttAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProjectTaskGanttAuthService.AuthServiceMap.set(context.srfdynainstid, new ProjectTaskGanttAuthService({context:context}));
            }
            return ProjectTaskGanttAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}