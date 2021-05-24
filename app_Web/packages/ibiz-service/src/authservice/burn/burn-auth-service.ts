import { BurnAuthServiceBase } from './burn-auth-service-base';


/**
 * burn权限服务对象
 *
 * @export
 * @class BurnAuthService
 * @extends {BurnAuthServiceBase}
 */
export default class BurnAuthService extends BurnAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {BurnAuthService}
     * @memberof BurnAuthService
     */
    private static basicUIServiceInstance: BurnAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof BurnAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  BurnAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  BurnAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof BurnAuthService
     */
     public static getInstance(context: any): BurnAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new BurnAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!BurnAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                BurnAuthService.AuthServiceMap.set(context.srfdynainstid, new BurnAuthService({context:context}));
            }
            return BurnAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}