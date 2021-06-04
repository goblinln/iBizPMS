import { TestCaseLibModuleUIServiceBase } from './test-case-lib-module-ui-service-base';

/**
 * 用例库模块UI服务对象
 *
 * @export
 * @class TestCaseLibModuleUIService
 */
export default class TestCaseLibModuleUIService extends TestCaseLibModuleUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestCaseLibModuleUIService
     */
    private static basicUIServiceInstance: TestCaseLibModuleUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestCaseLibModuleUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseLibModuleUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseLibModuleUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseLibModuleUIService
     */
    public static getInstance(context: any): TestCaseLibModuleUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseLibModuleUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseLibModuleUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestCaseLibModuleUIService.UIServiceMap.set(context.srfdynainstid, new TestCaseLibModuleUIService({context:context}));
            }
            return TestCaseLibModuleUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}