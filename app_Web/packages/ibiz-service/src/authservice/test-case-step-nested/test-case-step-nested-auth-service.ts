import { TestCaseStepNestedAuthServiceBase } from './test-case-step-nested-auth-service-base';


/**
 * 用例步骤权限服务对象
 *
 * @export
 * @class TestCaseStepNestedAuthService
 * @extends {TestCaseStepNestedAuthServiceBase}
 */
export default class TestCaseStepNestedAuthService extends TestCaseStepNestedAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestCaseStepNestedAuthService}
     * @memberof TestCaseStepNestedAuthService
     */
    private static basicUIServiceInstance: TestCaseStepNestedAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestCaseStepNestedAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseStepNestedAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseStepNestedAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseStepNestedAuthService
     */
     public static getInstance(context: any): TestCaseStepNestedAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseStepNestedAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseStepNestedAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestCaseStepNestedAuthService.AuthServiceMap.set(context.srfdynainstid, new TestCaseStepNestedAuthService({context:context}));
            }
            return TestCaseStepNestedAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}