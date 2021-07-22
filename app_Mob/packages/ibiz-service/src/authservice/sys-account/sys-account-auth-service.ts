import { SysAccountAuthServiceBase } from './sys-account-auth-service-base';


/**
 * 系统用户权限服务对象
 *
 * @export
 * @class SysAccountAuthService
 * @extends {SysAccountAuthServiceBase}
 */
export default class SysAccountAuthService extends SysAccountAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysAccountAuthService}
     * @memberof SysAccountAuthService
     */
    private static basicUIServiceInstance: SysAccountAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysAccountAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysAccountAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysAccountAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysAccountAuthService
     */
     public static getInstance(context: any): SysAccountAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysAccountAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysAccountAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysAccountAuthService.AuthServiceMap.set(context.srfdynainstid, new SysAccountAuthService({context:context}));
            }
            return SysAccountAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}