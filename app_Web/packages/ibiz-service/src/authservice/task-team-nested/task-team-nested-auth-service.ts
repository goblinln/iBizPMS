import { TaskTeamNestedAuthServiceBase } from './task-team-nested-auth-service-base';


/**
 * 任务团队权限服务对象
 *
 * @export
 * @class TaskTeamNestedAuthService
 * @extends {TaskTeamNestedAuthServiceBase}
 */
export default class TaskTeamNestedAuthService extends TaskTeamNestedAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TaskTeamNestedAuthService}
     * @memberof TaskTeamNestedAuthService
     */
    private static basicUIServiceInstance: TaskTeamNestedAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TaskTeamNestedAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskTeamNestedAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskTeamNestedAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskTeamNestedAuthService
     */
     public static getInstance(context: any): TaskTeamNestedAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskTeamNestedAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskTeamNestedAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TaskTeamNestedAuthService.AuthServiceMap.set(context.srfdynainstid, new TaskTeamNestedAuthService({context:context}));
            }
            return TaskTeamNestedAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}