import { TestModuleAuthServiceBase } from './test-module-auth-service-base';


/**
 * 测试模块权限服务对象
 *
 * @export
 * @class TestModuleAuthService
 * @extends {TestModuleAuthServiceBase}
 */
export default class TestModuleAuthService extends TestModuleAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestModuleAuthService}
     * @memberof TestModuleAuthService
     */
    private static basicUIServiceInstance: TestModuleAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestModuleAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestModuleAuthService
     */
     public static getInstance(context: any): TestModuleAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestModuleAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestModuleAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestModuleAuthService.AuthServiceMap.set(context.srfdynainstid, new TestModuleAuthService({context:context}));
            }
            return TestModuleAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}