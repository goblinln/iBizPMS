import { ActionAuthServiceBase } from './action-auth-service-base';


/**
 * 系统日志权限服务对象
 *
 * @export
 * @class ActionAuthService
 * @extends {ActionAuthServiceBase}
 */
export default class ActionAuthService extends ActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ActionAuthService}
     * @memberof ActionAuthService
     */
    private static basicUIServiceInstance: ActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ActionAuthService
     */
     public static getInstance(context: any): ActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ActionAuthService.AuthServiceMap.set(context.srfdynainstid, new ActionAuthService({context:context}));
            }
            return ActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}