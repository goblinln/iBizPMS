import { AccountStoryAuthServiceBase } from './account-story-auth-service-base';


/**
 * 需求权限服务对象
 *
 * @export
 * @class AccountStoryAuthService
 * @extends {AccountStoryAuthServiceBase}
 */
export default class AccountStoryAuthService extends AccountStoryAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {AccountStoryAuthService}
     * @memberof AccountStoryAuthService
     */
    private static basicUIServiceInstance: AccountStoryAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof AccountStoryAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountStoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountStoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountStoryAuthService
     */
     public static getInstance(context: any): AccountStoryAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountStoryAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountStoryAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                AccountStoryAuthService.AuthServiceMap.set(context.srfdynainstid, new AccountStoryAuthService({context:context}));
            }
            return AccountStoryAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}