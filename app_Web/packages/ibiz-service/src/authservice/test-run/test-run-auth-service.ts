import { TestRunAuthServiceBase } from './test-run-auth-service-base';


/**
 * 测试运行权限服务对象
 *
 * @export
 * @class TestRunAuthService
 * @extends {TestRunAuthServiceBase}
 */
export default class TestRunAuthService extends TestRunAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestRunAuthService}
     * @memberof TestRunAuthService
     */
    private static basicUIServiceInstance: TestRunAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestRunAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestRunAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestRunAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestRunAuthService
     */
     public static getInstance(context: any): TestRunAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestRunAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestRunAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestRunAuthService.AuthServiceMap.set(context.srfdynainstid, new TestRunAuthService({context:context}));
            }
            return TestRunAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}