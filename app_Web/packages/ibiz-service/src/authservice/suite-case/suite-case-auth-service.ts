import { SuiteCaseAuthServiceBase } from './suite-case-auth-service-base';


/**
 * 套件用例权限服务对象
 *
 * @export
 * @class SuiteCaseAuthService
 * @extends {SuiteCaseAuthServiceBase}
 */
export default class SuiteCaseAuthService extends SuiteCaseAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SuiteCaseAuthService}
     * @memberof SuiteCaseAuthService
     */
    private static basicUIServiceInstance: SuiteCaseAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SuiteCaseAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SuiteCaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SuiteCaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SuiteCaseAuthService
     */
     public static getInstance(context: any): SuiteCaseAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SuiteCaseAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SuiteCaseAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SuiteCaseAuthService.AuthServiceMap.set(context.srfdynainstid, new SuiteCaseAuthService({context:context}));
            }
            return SuiteCaseAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}