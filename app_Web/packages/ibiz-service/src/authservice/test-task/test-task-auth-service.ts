import { TestTaskAuthServiceBase } from './test-task-auth-service-base';


/**
 * 测试版本权限服务对象
 *
 * @export
 * @class TestTaskAuthService
 * @extends {TestTaskAuthServiceBase}
 */
export default class TestTaskAuthService extends TestTaskAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestTaskAuthService}
     * @memberof TestTaskAuthService
     */
    private static basicUIServiceInstance: TestTaskAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestTaskAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestTaskAuthService
     */
     public static getInstance(context: any): TestTaskAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestTaskAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestTaskAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestTaskAuthService.AuthServiceMap.set(context.srfdynainstid, new TestTaskAuthService({context:context}));
            }
            return TestTaskAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}