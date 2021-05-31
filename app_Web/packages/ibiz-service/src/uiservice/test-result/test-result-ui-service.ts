import { TestResultUIServiceBase } from './test-result-ui-service-base';

/**
 * 测试结果UI服务对象
 *
 * @export
 * @class TestResultUIService
 */
export default class TestResultUIService extends TestResultUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestResultUIService
     */
    private static basicUIServiceInstance: TestResultUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestResultUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestResultUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestResultUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestResultUIService
     */
    public static getInstance(context: any): TestResultUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestResultUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestResultUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestResultUIService.UIServiceMap.set(context.srfdynainstid, new TestResultUIService({context:context}));
            }
            return TestResultUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}