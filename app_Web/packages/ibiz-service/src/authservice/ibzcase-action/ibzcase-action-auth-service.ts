import { IBZCaseActionAuthServiceBase } from './ibzcase-action-auth-service-base';


/**
 * 测试用例日志权限服务对象
 *
 * @export
 * @class IBZCaseActionAuthService
 * @extends {IBZCaseActionAuthServiceBase}
 */
export default class IBZCaseActionAuthService extends IBZCaseActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZCaseActionAuthService}
     * @memberof IBZCaseActionAuthService
     */
    private static basicUIServiceInstance: IBZCaseActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZCaseActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZCaseActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZCaseActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZCaseActionAuthService
     */
     public static getInstance(context: any): IBZCaseActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZCaseActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZCaseActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZCaseActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZCaseActionAuthService({context:context}));
            }
            return IBZCaseActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}