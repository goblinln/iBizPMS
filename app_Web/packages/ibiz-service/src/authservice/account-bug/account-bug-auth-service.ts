import { AccountBugAuthServiceBase } from './account-bug-auth-service-base';


/**
 * Bug权限服务对象
 *
 * @export
 * @class AccountBugAuthService
 * @extends {AccountBugAuthServiceBase}
 */
export default class AccountBugAuthService extends AccountBugAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {AccountBugAuthService}
     * @memberof AccountBugAuthService
     */
    private static basicUIServiceInstance: AccountBugAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof AccountBugAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountBugAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountBugAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountBugAuthService
     */
     public static getInstance(context: any): AccountBugAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountBugAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountBugAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                AccountBugAuthService.AuthServiceMap.set(context.srfdynainstid, new AccountBugAuthService({context:context}));
            }
            return AccountBugAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}