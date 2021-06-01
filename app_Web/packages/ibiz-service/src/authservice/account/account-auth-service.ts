import { AccountAuthServiceBase } from './account-auth-service-base';


/**
 * 系统用户权限服务对象
 *
 * @export
 * @class AccountAuthService
 * @extends {AccountAuthServiceBase}
 */
export default class AccountAuthService extends AccountAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {AccountAuthService}
     * @memberof AccountAuthService
     */
    private static basicUIServiceInstance: AccountAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof AccountAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountAuthService
     */
     public static getInstance(context: any): AccountAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                AccountAuthService.AuthServiceMap.set(context.srfdynainstid, new AccountAuthService({context:context}));
            }
            return AccountAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}