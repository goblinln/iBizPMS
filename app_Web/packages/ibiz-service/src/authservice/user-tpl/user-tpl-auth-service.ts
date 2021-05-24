import { UserTplAuthServiceBase } from './user-tpl-auth-service-base';


/**
 * 用户模板权限服务对象
 *
 * @export
 * @class UserTplAuthService
 * @extends {UserTplAuthServiceBase}
 */
export default class UserTplAuthService extends UserTplAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {UserTplAuthService}
     * @memberof UserTplAuthService
     */
    private static basicUIServiceInstance: UserTplAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof UserTplAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  UserTplAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserTplAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof UserTplAuthService
     */
     public static getInstance(context: any): UserTplAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new UserTplAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!UserTplAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                UserTplAuthService.AuthServiceMap.set(context.srfdynainstid, new UserTplAuthService({context:context}));
            }
            return UserTplAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}