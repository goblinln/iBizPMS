import { TestCaseStepUIServiceBase } from './test-case-step-ui-service-base';

/**
 * 用例步骤UI服务对象
 *
 * @export
 * @class TestCaseStepUIService
 */
export default class TestCaseStepUIService extends TestCaseStepUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestCaseStepUIService
     */
    private static basicUIServiceInstance: TestCaseStepUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestCaseStepUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseStepUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseStepUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseStepUIService
     */
    public static getInstance(context: any): TestCaseStepUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseStepUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseStepUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestCaseStepUIService.UIServiceMap.set(context.srfdynainstid, new TestCaseStepUIService({context:context}));
            }
            return TestCaseStepUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}