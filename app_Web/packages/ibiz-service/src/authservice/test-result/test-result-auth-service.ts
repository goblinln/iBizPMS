import { TestResultAuthServiceBase } from './test-result-auth-service-base';


/**
 * 测试结果权限服务对象
 *
 * @export
 * @class TestResultAuthService
 * @extends {TestResultAuthServiceBase}
 */
export default class TestResultAuthService extends TestResultAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestResultAuthService}
     * @memberof TestResultAuthService
     */
    private static basicUIServiceInstance: TestResultAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestResultAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestResultAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestResultAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestResultAuthService
     */
     public static getInstance(context: any): TestResultAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestResultAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestResultAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestResultAuthService.AuthServiceMap.set(context.srfdynainstid, new TestResultAuthService({context:context}));
            }
            return TestResultAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}