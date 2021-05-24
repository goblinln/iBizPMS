import { SysUpdateFeaturesAuthServiceBase } from './sys-update-features-auth-service-base';


/**
 * 系统更新功能权限服务对象
 *
 * @export
 * @class SysUpdateFeaturesAuthService
 * @extends {SysUpdateFeaturesAuthServiceBase}
 */
export default class SysUpdateFeaturesAuthService extends SysUpdateFeaturesAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysUpdateFeaturesAuthService}
     * @memberof SysUpdateFeaturesAuthService
     */
    private static basicUIServiceInstance: SysUpdateFeaturesAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysUpdateFeaturesAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysUpdateFeaturesAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUpdateFeaturesAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysUpdateFeaturesAuthService
     */
     public static getInstance(context: any): SysUpdateFeaturesAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysUpdateFeaturesAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysUpdateFeaturesAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysUpdateFeaturesAuthService.AuthServiceMap.set(context.srfdynainstid, new SysUpdateFeaturesAuthService({context:context}));
            }
            return SysUpdateFeaturesAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}