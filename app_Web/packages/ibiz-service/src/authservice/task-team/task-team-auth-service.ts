import { TaskTeamAuthServiceBase } from './task-team-auth-service-base';


/**
 * 任务团队权限服务对象
 *
 * @export
 * @class TaskTeamAuthService
 * @extends {TaskTeamAuthServiceBase}
 */
export default class TaskTeamAuthService extends TaskTeamAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TaskTeamAuthService}
     * @memberof TaskTeamAuthService
     */
    private static basicUIServiceInstance: TaskTeamAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TaskTeamAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskTeamAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskTeamAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskTeamAuthService
     */
     public static getInstance(context: any): TaskTeamAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskTeamAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskTeamAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TaskTeamAuthService.AuthServiceMap.set(context.srfdynainstid, new TaskTeamAuthService({context:context}));
            }
            return TaskTeamAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}