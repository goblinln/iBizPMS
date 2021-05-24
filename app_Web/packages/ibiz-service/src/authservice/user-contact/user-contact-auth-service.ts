import { UserContactAuthServiceBase } from './user-contact-auth-service-base';


/**
 * 用户联系方式权限服务对象
 *
 * @export
 * @class UserContactAuthService
 * @extends {UserContactAuthServiceBase}
 */
export default class UserContactAuthService extends UserContactAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {UserContactAuthService}
     * @memberof UserContactAuthService
     */
    private static basicUIServiceInstance: UserContactAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof UserContactAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  UserContactAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserContactAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof UserContactAuthService
     */
     public static getInstance(context: any): UserContactAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new UserContactAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!UserContactAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                UserContactAuthService.AuthServiceMap.set(context.srfdynainstid, new UserContactAuthService({context:context}));
            }
            return UserContactAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}