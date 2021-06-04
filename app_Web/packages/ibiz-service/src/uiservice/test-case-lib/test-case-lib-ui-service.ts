import { TestCaseLibUIServiceBase } from './test-case-lib-ui-service-base';

/**
 * 用例库UI服务对象
 *
 * @export
 * @class TestCaseLibUIService
 */
export default class TestCaseLibUIService extends TestCaseLibUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestCaseLibUIService
     */
    private static basicUIServiceInstance: TestCaseLibUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestCaseLibUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseLibUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseLibUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseLibUIService
     */
    public static getInstance(context: any): TestCaseLibUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseLibUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseLibUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestCaseLibUIService.UIServiceMap.set(context.srfdynainstid, new TestCaseLibUIService({context:context}));
            }
            return TestCaseLibUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}