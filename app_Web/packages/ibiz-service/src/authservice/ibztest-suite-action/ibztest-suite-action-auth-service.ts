import { IBZTestSuiteActionAuthServiceBase } from './ibztest-suite-action-auth-service-base';


/**
 * 套件日志权限服务对象
 *
 * @export
 * @class IBZTestSuiteActionAuthService
 * @extends {IBZTestSuiteActionAuthServiceBase}
 */
export default class IBZTestSuiteActionAuthService extends IBZTestSuiteActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZTestSuiteActionAuthService}
     * @memberof IBZTestSuiteActionAuthService
     */
    private static basicUIServiceInstance: IBZTestSuiteActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZTestSuiteActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZTestSuiteActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTestSuiteActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZTestSuiteActionAuthService
     */
     public static getInstance(context: any): IBZTestSuiteActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZTestSuiteActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZTestSuiteActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZTestSuiteActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZTestSuiteActionAuthService({context:context}));
            }
            return IBZTestSuiteActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}