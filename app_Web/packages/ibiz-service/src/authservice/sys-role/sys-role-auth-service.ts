import { SysRoleAuthServiceBase } from './sys-role-auth-service-base';


/**
 * 系统角色权限服务对象
 *
 * @export
 * @class SysRoleAuthService
 * @extends {SysRoleAuthServiceBase}
 */
export default class SysRoleAuthService extends SysRoleAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysRoleAuthService}
     * @memberof SysRoleAuthService
     */
    private static basicUIServiceInstance: SysRoleAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysRoleAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysRoleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysRoleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysRoleAuthService
     */
     public static getInstance(context: any): SysRoleAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysRoleAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysRoleAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysRoleAuthService.AuthServiceMap.set(context.srfdynainstid, new SysRoleAuthService({context:context}));
            }
            return SysRoleAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}