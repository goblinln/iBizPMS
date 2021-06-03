import { AccountTestTaskAuthServiceBase } from './account-test-task-auth-service-base';


/**
 * 测试版本权限服务对象
 *
 * @export
 * @class AccountTestTaskAuthService
 * @extends {AccountTestTaskAuthServiceBase}
 */
export default class AccountTestTaskAuthService extends AccountTestTaskAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {AccountTestTaskAuthService}
     * @memberof AccountTestTaskAuthService
     */
    private static basicUIServiceInstance: AccountTestTaskAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof AccountTestTaskAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountTestTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountTestTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountTestTaskAuthService
     */
     public static getInstance(context: any): AccountTestTaskAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountTestTaskAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountTestTaskAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                AccountTestTaskAuthService.AuthServiceMap.set(context.srfdynainstid, new AccountTestTaskAuthService({context:context}));
            }
            return AccountTestTaskAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}