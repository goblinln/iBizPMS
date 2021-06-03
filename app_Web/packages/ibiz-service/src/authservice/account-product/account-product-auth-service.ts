import { AccountProductAuthServiceBase } from './account-product-auth-service-base';


/**
 * 产品权限服务对象
 *
 * @export
 * @class AccountProductAuthService
 * @extends {AccountProductAuthServiceBase}
 */
export default class AccountProductAuthService extends AccountProductAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {AccountProductAuthService}
     * @memberof AccountProductAuthService
     */
    private static basicUIServiceInstance: AccountProductAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof AccountProductAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountProductAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountProductAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountProductAuthService
     */
     public static getInstance(context: any): AccountProductAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountProductAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountProductAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                AccountProductAuthService.AuthServiceMap.set(context.srfdynainstid, new AccountProductAuthService({context:context}));
            }
            return AccountProductAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}