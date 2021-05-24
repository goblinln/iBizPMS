import { AccountTaskestimateAuthServiceBase } from './account-taskestimate-auth-service-base';


/**
 * 用户工时统计权限服务对象
 *
 * @export
 * @class AccountTaskestimateAuthService
 * @extends {AccountTaskestimateAuthServiceBase}
 */
export default class AccountTaskestimateAuthService extends AccountTaskestimateAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {AccountTaskestimateAuthService}
     * @memberof AccountTaskestimateAuthService
     */
    private static basicUIServiceInstance: AccountTaskestimateAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof AccountTaskestimateAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountTaskestimateAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountTaskestimateAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountTaskestimateAuthService
     */
     public static getInstance(context: any): AccountTaskestimateAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountTaskestimateAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountTaskestimateAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                AccountTaskestimateAuthService.AuthServiceMap.set(context.srfdynainstid, new AccountTaskestimateAuthService({context:context}));
            }
            return AccountTaskestimateAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}