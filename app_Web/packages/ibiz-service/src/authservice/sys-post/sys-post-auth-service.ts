import { SysPostAuthServiceBase } from './sys-post-auth-service-base';


/**
 * 岗位权限服务对象
 *
 * @export
 * @class SysPostAuthService
 * @extends {SysPostAuthServiceBase}
 */
export default class SysPostAuthService extends SysPostAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysPostAuthService}
     * @memberof SysPostAuthService
     */
    private static basicUIServiceInstance: SysPostAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysPostAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysPostAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysPostAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysPostAuthService
     */
     public static getInstance(context: any): SysPostAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysPostAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysPostAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysPostAuthService.AuthServiceMap.set(context.srfdynainstid, new SysPostAuthService({context:context}));
            }
            return SysPostAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}