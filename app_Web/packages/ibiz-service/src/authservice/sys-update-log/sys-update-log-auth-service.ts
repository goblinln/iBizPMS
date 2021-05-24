import { SysUpdateLogAuthServiceBase } from './sys-update-log-auth-service-base';


/**
 * 更新日志权限服务对象
 *
 * @export
 * @class SysUpdateLogAuthService
 * @extends {SysUpdateLogAuthServiceBase}
 */
export default class SysUpdateLogAuthService extends SysUpdateLogAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysUpdateLogAuthService}
     * @memberof SysUpdateLogAuthService
     */
    private static basicUIServiceInstance: SysUpdateLogAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysUpdateLogAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysUpdateLogAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUpdateLogAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysUpdateLogAuthService
     */
     public static getInstance(context: any): SysUpdateLogAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysUpdateLogAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysUpdateLogAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysUpdateLogAuthService.AuthServiceMap.set(context.srfdynainstid, new SysUpdateLogAuthService({context:context}));
            }
            return SysUpdateLogAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}