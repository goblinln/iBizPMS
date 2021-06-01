import { TestReultUIServiceBase } from './test-reult-ui-service-base';

/**
 * 测试结果UI服务对象
 *
 * @export
 * @class TestReultUIService
 */
export default class TestReultUIService extends TestReultUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestReultUIService
     */
    private static basicUIServiceInstance: TestReultUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestReultUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestReultUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestReultUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestReultUIService
     */
    public static getInstance(context: any): TestReultUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestReultUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestReultUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestReultUIService.UIServiceMap.set(context.srfdynainstid, new TestReultUIService({context:context}));
            }
            return TestReultUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}