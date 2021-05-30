import { TestCaseAuthServiceBase } from './test-case-auth-service-base';


/**
 * 测试用例权限服务对象
 *
 * @export
 * @class TestCaseAuthService
 * @extends {TestCaseAuthServiceBase}
 */
export default class TestCaseAuthService extends TestCaseAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestCaseAuthService}
     * @memberof TestCaseAuthService
     */
    private static basicUIServiceInstance: TestCaseAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestCaseAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseAuthService
     */
     public static getInstance(context: any): TestCaseAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestCaseAuthService.AuthServiceMap.set(context.srfdynainstid, new TestCaseAuthService({context:context}));
            }
            return TestCaseAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}