import { TestSuiteAuthServiceBase } from './test-suite-auth-service-base';


/**
 * 测试套件权限服务对象
 *
 * @export
 * @class TestSuiteAuthService
 * @extends {TestSuiteAuthServiceBase}
 */
export default class TestSuiteAuthService extends TestSuiteAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestSuiteAuthService}
     * @memberof TestSuiteAuthService
     */
    private static basicUIServiceInstance: TestSuiteAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestSuiteAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestSuiteAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestSuiteAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestSuiteAuthService
     */
     public static getInstance(context: any): TestSuiteAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestSuiteAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestSuiteAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestSuiteAuthService.AuthServiceMap.set(context.srfdynainstid, new TestSuiteAuthService({context:context}));
            }
            return TestSuiteAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}