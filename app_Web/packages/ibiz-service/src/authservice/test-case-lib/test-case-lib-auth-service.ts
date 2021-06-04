import { TestCaseLibAuthServiceBase } from './test-case-lib-auth-service-base';


/**
 * 用例库权限服务对象
 *
 * @export
 * @class TestCaseLibAuthService
 * @extends {TestCaseLibAuthServiceBase}
 */
export default class TestCaseLibAuthService extends TestCaseLibAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestCaseLibAuthService}
     * @memberof TestCaseLibAuthService
     */
    private static basicUIServiceInstance: TestCaseLibAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestCaseLibAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseLibAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseLibAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseLibAuthService
     */
     public static getInstance(context: any): TestCaseLibAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseLibAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseLibAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestCaseLibAuthService.AuthServiceMap.set(context.srfdynainstid, new TestCaseLibAuthService({context:context}));
            }
            return TestCaseLibAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}