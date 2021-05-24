import { PSSysAppAuthServiceBase } from './pssys-app-auth-service-base';


/**
 * 系统应用权限服务对象
 *
 * @export
 * @class PSSysAppAuthService
 * @extends {PSSysAppAuthServiceBase}
 */
export default class PSSysAppAuthService extends PSSysAppAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {PSSysAppAuthService}
     * @memberof PSSysAppAuthService
     */
    private static basicUIServiceInstance: PSSysAppAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof PSSysAppAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  PSSysAppAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSysAppAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof PSSysAppAuthService
     */
     public static getInstance(context: any): PSSysAppAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new PSSysAppAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!PSSysAppAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                PSSysAppAuthService.AuthServiceMap.set(context.srfdynainstid, new PSSysAppAuthService({context:context}));
            }
            return PSSysAppAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}