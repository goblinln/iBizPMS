import { TestReultAuthServiceBase } from './test-reult-auth-service-base';


/**
 * 测试结果权限服务对象
 *
 * @export
 * @class TestReultAuthService
 * @extends {TestReultAuthServiceBase}
 */
export default class TestReultAuthService extends TestReultAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestReultAuthService}
     * @memberof TestReultAuthService
     */
    private static basicUIServiceInstance: TestReultAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestReultAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestReultAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestReultAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestReultAuthService
     */
     public static getInstance(context: any): TestReultAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestReultAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestReultAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestReultAuthService.AuthServiceMap.set(context.srfdynainstid, new TestReultAuthService({context:context}));
            }
            return TestReultAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}