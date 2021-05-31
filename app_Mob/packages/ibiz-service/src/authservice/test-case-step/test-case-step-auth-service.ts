import { TestCaseStepAuthServiceBase } from './test-case-step-auth-service-base';


/**
 * 用例步骤权限服务对象
 *
 * @export
 * @class TestCaseStepAuthService
 * @extends {TestCaseStepAuthServiceBase}
 */
export default class TestCaseStepAuthService extends TestCaseStepAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestCaseStepAuthService}
     * @memberof TestCaseStepAuthService
     */
    private static basicUIServiceInstance: TestCaseStepAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestCaseStepAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseStepAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseStepAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseStepAuthService
     */
     public static getInstance(context: any): TestCaseStepAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseStepAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseStepAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestCaseStepAuthService.AuthServiceMap.set(context.srfdynainstid, new TestCaseStepAuthService({context:context}));
            }
            return TestCaseStepAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}