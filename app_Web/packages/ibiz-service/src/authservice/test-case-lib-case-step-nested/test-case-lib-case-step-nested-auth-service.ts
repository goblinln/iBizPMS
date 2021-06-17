import { TestCaseLibCaseStepNestedAuthServiceBase } from './test-case-lib-case-step-nested-auth-service-base';


/**
 * 用例库用例步骤权限服务对象
 *
 * @export
 * @class TestCaseLibCaseStepNestedAuthService
 * @extends {TestCaseLibCaseStepNestedAuthServiceBase}
 */
export default class TestCaseLibCaseStepNestedAuthService extends TestCaseLibCaseStepNestedAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestCaseLibCaseStepNestedAuthService}
     * @memberof TestCaseLibCaseStepNestedAuthService
     */
    private static basicUIServiceInstance: TestCaseLibCaseStepNestedAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestCaseLibCaseStepNestedAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseLibCaseStepNestedAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseLibCaseStepNestedAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseLibCaseStepNestedAuthService
     */
     public static getInstance(context: any): TestCaseLibCaseStepNestedAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseLibCaseStepNestedAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseLibCaseStepNestedAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestCaseLibCaseStepNestedAuthService.AuthServiceMap.set(context.srfdynainstid, new TestCaseLibCaseStepNestedAuthService({context:context}));
            }
            return TestCaseLibCaseStepNestedAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}