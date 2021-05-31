import { TestSuiteUIServiceBase } from './test-suite-ui-service-base';

/**
 * 测试套件UI服务对象
 *
 * @export
 * @class TestSuiteUIService
 */
export default class TestSuiteUIService extends TestSuiteUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestSuiteUIService
     */
    private static basicUIServiceInstance: TestSuiteUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestSuiteUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestSuiteUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestSuiteUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestSuiteUIService
     */
    public static getInstance(context: any): TestSuiteUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestSuiteUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestSuiteUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestSuiteUIService.UIServiceMap.set(context.srfdynainstid, new TestSuiteUIService({context:context}));
            }
            return TestSuiteUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}