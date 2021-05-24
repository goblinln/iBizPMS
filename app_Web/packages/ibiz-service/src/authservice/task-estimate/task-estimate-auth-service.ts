import { TaskEstimateAuthServiceBase } from './task-estimate-auth-service-base';


/**
 * 任务预计权限服务对象
 *
 * @export
 * @class TaskEstimateAuthService
 * @extends {TaskEstimateAuthServiceBase}
 */
export default class TaskEstimateAuthService extends TaskEstimateAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TaskEstimateAuthService}
     * @memberof TaskEstimateAuthService
     */
    private static basicUIServiceInstance: TaskEstimateAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TaskEstimateAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskEstimateAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskEstimateAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskEstimateAuthService
     */
     public static getInstance(context: any): TaskEstimateAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskEstimateAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskEstimateAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TaskEstimateAuthService.AuthServiceMap.set(context.srfdynainstid, new TaskEstimateAuthService({context:context}));
            }
            return TaskEstimateAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}