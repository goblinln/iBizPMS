import { TestRunUIServiceBase } from './test-run-ui-service-base';

/**
 * 测试运行UI服务对象
 *
 * @export
 * @class TestRunUIService
 */
export default class TestRunUIService extends TestRunUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestRunUIService
     */
    private static basicUIServiceInstance: TestRunUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestRunUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestRunUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestRunUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestRunUIService
     */
    public static getInstance(context: any): TestRunUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestRunUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestRunUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestRunUIService.UIServiceMap.set(context.srfdynainstid, new TestRunUIService({context:context}));
            }
            return TestRunUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}