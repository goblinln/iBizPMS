import { AccountTestCaseAuthServiceBase } from './account-test-case-auth-service-base';


/**
 * 测试用例权限服务对象
 *
 * @export
 * @class AccountTestCaseAuthService
 * @extends {AccountTestCaseAuthServiceBase}
 */
export default class AccountTestCaseAuthService extends AccountTestCaseAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {AccountTestCaseAuthService}
     * @memberof AccountTestCaseAuthService
     */
    private static basicUIServiceInstance: AccountTestCaseAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof AccountTestCaseAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountTestCaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountTestCaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountTestCaseAuthService
     */
     public static getInstance(context: any): AccountTestCaseAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountTestCaseAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountTestCaseAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                AccountTestCaseAuthService.AuthServiceMap.set(context.srfdynainstid, new AccountTestCaseAuthService({context:context}));
            }
            return AccountTestCaseAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}