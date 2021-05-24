import { TaskStatsAuthServiceBase } from './task-stats-auth-service-base';


/**
 * 任务统计权限服务对象
 *
 * @export
 * @class TaskStatsAuthService
 * @extends {TaskStatsAuthServiceBase}
 */
export default class TaskStatsAuthService extends TaskStatsAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TaskStatsAuthService}
     * @memberof TaskStatsAuthService
     */
    private static basicUIServiceInstance: TaskStatsAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TaskStatsAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskStatsAuthService
     */
     public static getInstance(context: any): TaskStatsAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskStatsAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskStatsAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TaskStatsAuthService.AuthServiceMap.set(context.srfdynainstid, new TaskStatsAuthService({context:context}));
            }
            return TaskStatsAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}