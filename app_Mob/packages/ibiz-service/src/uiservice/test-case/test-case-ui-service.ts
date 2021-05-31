import { TestCaseUIServiceBase } from './test-case-ui-service-base';

/**
 * 测试用例UI服务对象
 *
 * @export
 * @class TestCaseUIService
 */
export default class TestCaseUIService extends TestCaseUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestCaseUIService
     */
    private static basicUIServiceInstance: TestCaseUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestCaseUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseUIService
     */
    public static getInstance(context: any): TestCaseUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestCaseUIService.UIServiceMap.set(context.srfdynainstid, new TestCaseUIService({context:context}));
            }
            return TestCaseUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}