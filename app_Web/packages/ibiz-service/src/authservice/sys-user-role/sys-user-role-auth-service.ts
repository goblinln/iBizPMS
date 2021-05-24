import { SysUserRoleAuthServiceBase } from './sys-user-role-auth-service-base';


/**
 * 用户角色关系权限服务对象
 *
 * @export
 * @class SysUserRoleAuthService
 * @extends {SysUserRoleAuthServiceBase}
 */
export default class SysUserRoleAuthService extends SysUserRoleAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysUserRoleAuthService}
     * @memberof SysUserRoleAuthService
     */
    private static basicUIServiceInstance: SysUserRoleAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysUserRoleAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysUserRoleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUserRoleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysUserRoleAuthService
     */
     public static getInstance(context: any): SysUserRoleAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysUserRoleAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysUserRoleAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysUserRoleAuthService.AuthServiceMap.set(context.srfdynainstid, new SysUserRoleAuthService({context:context}));
            }
            return SysUserRoleAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}