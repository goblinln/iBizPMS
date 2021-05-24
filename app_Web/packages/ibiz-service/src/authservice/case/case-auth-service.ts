import { CaseAuthServiceBase } from './case-auth-service-base';


/**
 * 测试用例权限服务对象
 *
 * @export
 * @class CaseAuthService
 * @extends {CaseAuthServiceBase}
 */
export default class CaseAuthService extends CaseAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {CaseAuthService}
     * @memberof CaseAuthService
     */
    private static basicUIServiceInstance: CaseAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof CaseAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  CaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof CaseAuthService
     */
     public static getInstance(context: any): CaseAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new CaseAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!CaseAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                CaseAuthService.AuthServiceMap.set(context.srfdynainstid, new CaseAuthService({context:context}));
            }
            return CaseAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}