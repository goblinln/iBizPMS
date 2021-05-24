import { SubTaskAuthServiceBase } from './sub-task-auth-service-base';


/**
 * 任务权限服务对象
 *
 * @export
 * @class SubTaskAuthService
 * @extends {SubTaskAuthServiceBase}
 */
export default class SubTaskAuthService extends SubTaskAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SubTaskAuthService}
     * @memberof SubTaskAuthService
     */
    private static basicUIServiceInstance: SubTaskAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SubTaskAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SubTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SubTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SubTaskAuthService
     */
     public static getInstance(context: any): SubTaskAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SubTaskAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SubTaskAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SubTaskAuthService.AuthServiceMap.set(context.srfdynainstid, new SubTaskAuthService({context:context}));
            }
            return SubTaskAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}