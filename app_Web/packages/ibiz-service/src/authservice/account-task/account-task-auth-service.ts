import { AccountTaskAuthServiceBase } from './account-task-auth-service-base';


/**
 * 任务权限服务对象
 *
 * @export
 * @class AccountTaskAuthService
 * @extends {AccountTaskAuthServiceBase}
 */
export default class AccountTaskAuthService extends AccountTaskAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {AccountTaskAuthService}
     * @memberof AccountTaskAuthService
     */
    private static basicUIServiceInstance: AccountTaskAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof AccountTaskAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountTaskAuthService
     */
     public static getInstance(context: any): AccountTaskAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountTaskAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountTaskAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                AccountTaskAuthService.AuthServiceMap.set(context.srfdynainstid, new AccountTaskAuthService({context:context}));
            }
            return AccountTaskAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}