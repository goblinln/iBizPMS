import { TestAuthServiceBase } from './test-auth-service-base';


/**
 * 产品权限服务对象
 *
 * @export
 * @class TestAuthService
 * @extends {TestAuthServiceBase}
 */
export default class TestAuthService extends TestAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestAuthService}
     * @memberof TestAuthService
     */
    private static basicUIServiceInstance: TestAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestAuthService
     */
     public static getInstance(context: any): TestAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestAuthService.AuthServiceMap.set(context.srfdynainstid, new TestAuthService({context:context}));
            }
            return TestAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}