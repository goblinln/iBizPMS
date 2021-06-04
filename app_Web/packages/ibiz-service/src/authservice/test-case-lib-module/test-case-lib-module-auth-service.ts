import { TestCaseLibModuleAuthServiceBase } from './test-case-lib-module-auth-service-base';


/**
 * 用例库模块权限服务对象
 *
 * @export
 * @class TestCaseLibModuleAuthService
 * @extends {TestCaseLibModuleAuthServiceBase}
 */
export default class TestCaseLibModuleAuthService extends TestCaseLibModuleAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestCaseLibModuleAuthService}
     * @memberof TestCaseLibModuleAuthService
     */
    private static basicUIServiceInstance: TestCaseLibModuleAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestCaseLibModuleAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseLibModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseLibModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseLibModuleAuthService
     */
     public static getInstance(context: any): TestCaseLibModuleAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseLibModuleAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseLibModuleAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestCaseLibModuleAuthService.AuthServiceMap.set(context.srfdynainstid, new TestCaseLibModuleAuthService({context:context}));
            }
            return TestCaseLibModuleAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}