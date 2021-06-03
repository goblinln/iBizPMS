import { AccountProjectAuthServiceBase } from './account-project-auth-service-base';


/**
 * 项目权限服务对象
 *
 * @export
 * @class AccountProjectAuthService
 * @extends {AccountProjectAuthServiceBase}
 */
export default class AccountProjectAuthService extends AccountProjectAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {AccountProjectAuthService}
     * @memberof AccountProjectAuthService
     */
    private static basicUIServiceInstance: AccountProjectAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof AccountProjectAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountProjectAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountProjectAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountProjectAuthService
     */
     public static getInstance(context: any): AccountProjectAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountProjectAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountProjectAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                AccountProjectAuthService.AuthServiceMap.set(context.srfdynainstid, new AccountProjectAuthService({context:context}));
            }
            return AccountProjectAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}