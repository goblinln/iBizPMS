import { IBZCaseStepAuthServiceBase } from './ibzcase-step-auth-service-base';


/**
 * 用例步骤权限服务对象
 *
 * @export
 * @class IBZCaseStepAuthService
 * @extends {IBZCaseStepAuthServiceBase}
 */
export default class IBZCaseStepAuthService extends IBZCaseStepAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZCaseStepAuthService}
     * @memberof IBZCaseStepAuthService
     */
    private static basicUIServiceInstance: IBZCaseStepAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZCaseStepAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZCaseStepAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZCaseStepAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZCaseStepAuthService
     */
     public static getInstance(context: any): IBZCaseStepAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZCaseStepAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZCaseStepAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZCaseStepAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZCaseStepAuthService({context:context}));
            }
            return IBZCaseStepAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}