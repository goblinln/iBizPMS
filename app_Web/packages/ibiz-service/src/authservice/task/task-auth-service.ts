import { TaskAuthServiceBase } from './task-auth-service-base';


/**
 * 任务权限服务对象
 *
 * @export
 * @class TaskAuthService
 * @extends {TaskAuthServiceBase}
 */
export default class TaskAuthService extends TaskAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TaskAuthService}
     * @memberof TaskAuthService
     */
    private static basicUIServiceInstance: TaskAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TaskAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskAuthService
     */
     public static getInstance(context: any): TaskAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TaskAuthService.AuthServiceMap.set(context.srfdynainstid, new TaskAuthService({context:context}));
            }
            return TaskAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}