import { TaskestimatestatsAuthServiceBase } from './taskestimatestats-auth-service-base';


/**
 * 任务工时统计权限服务对象
 *
 * @export
 * @class TaskestimatestatsAuthService
 * @extends {TaskestimatestatsAuthServiceBase}
 */
export default class TaskestimatestatsAuthService extends TaskestimatestatsAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TaskestimatestatsAuthService}
     * @memberof TaskestimatestatsAuthService
     */
    private static basicUIServiceInstance: TaskestimatestatsAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TaskestimatestatsAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskestimatestatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskestimatestatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskestimatestatsAuthService
     */
     public static getInstance(context: any): TaskestimatestatsAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskestimatestatsAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskestimatestatsAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TaskestimatestatsAuthService.AuthServiceMap.set(context.srfdynainstid, new TaskestimatestatsAuthService({context:context}));
            }
            return TaskestimatestatsAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}