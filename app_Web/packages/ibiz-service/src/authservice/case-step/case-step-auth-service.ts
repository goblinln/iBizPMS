import { CaseStepAuthServiceBase } from './case-step-auth-service-base';


/**
 * 用例步骤权限服务对象
 *
 * @export
 * @class CaseStepAuthService
 * @extends {CaseStepAuthServiceBase}
 */
export default class CaseStepAuthService extends CaseStepAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {CaseStepAuthService}
     * @memberof CaseStepAuthService
     */
    private static basicUIServiceInstance: CaseStepAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof CaseStepAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  CaseStepAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseStepAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof CaseStepAuthService
     */
     public static getInstance(context: any): CaseStepAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new CaseStepAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!CaseStepAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                CaseStepAuthService.AuthServiceMap.set(context.srfdynainstid, new CaseStepAuthService({context:context}));
            }
            return CaseStepAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}