import { TestModuleUIServiceBase } from './test-module-ui-service-base';

/**
 * 测试模块UI服务对象
 *
 * @export
 * @class TestModuleUIService
 */
export default class TestModuleUIService extends TestModuleUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestModuleUIService
     */
    private static basicUIServiceInstance: TestModuleUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestModuleUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestModuleUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestModuleUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestModuleUIService
     */
    public static getInstance(context: any): TestModuleUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestModuleUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestModuleUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestModuleUIService.UIServiceMap.set(context.srfdynainstid, new TestModuleUIService({context:context}));
            }
            return TestModuleUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}