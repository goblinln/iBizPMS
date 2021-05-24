import { UserAuthServiceBase } from './user-auth-service-base';


/**
 * 用户权限服务对象
 *
 * @export
 * @class UserAuthService
 * @extends {UserAuthServiceBase}
 */
export default class UserAuthService extends UserAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {UserAuthService}
     * @memberof UserAuthService
     */
    private static basicUIServiceInstance: UserAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof UserAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  UserAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof UserAuthService
     */
     public static getInstance(context: any): UserAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new UserAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!UserAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                UserAuthService.AuthServiceMap.set(context.srfdynainstid, new UserAuthService({context:context}));
            }
            return UserAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}