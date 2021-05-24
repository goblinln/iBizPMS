import { SysUserAuthServiceBase } from './sys-user-auth-service-base';


/**
 * 系统用户权限服务对象
 *
 * @export
 * @class SysUserAuthService
 * @extends {SysUserAuthServiceBase}
 */
export default class SysUserAuthService extends SysUserAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysUserAuthService}
     * @memberof SysUserAuthService
     */
    private static basicUIServiceInstance: SysUserAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysUserAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysUserAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUserAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysUserAuthService
     */
     public static getInstance(context: any): SysUserAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysUserAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysUserAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysUserAuthService.AuthServiceMap.set(context.srfdynainstid, new SysUserAuthService({context:context}));
            }
            return SysUserAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}