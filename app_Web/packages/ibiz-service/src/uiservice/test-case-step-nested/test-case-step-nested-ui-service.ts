import { TestCaseStepNestedUIServiceBase } from './test-case-step-nested-ui-service-base';

/**
 * 用例步骤UI服务对象
 *
 * @export
 * @class TestCaseStepNestedUIService
 */
export default class TestCaseStepNestedUIService extends TestCaseStepNestedUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestCaseStepNestedUIService
     */
    private static basicUIServiceInstance: TestCaseStepNestedUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestCaseStepNestedUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseStepNestedUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseStepNestedUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseStepNestedUIService
     */
    public static getInstance(context: any): TestCaseStepNestedUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseStepNestedUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseStepNestedUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestCaseStepNestedUIService.UIServiceMap.set(context.srfdynainstid, new TestCaseStepNestedUIService({context:context}));
            }
            return TestCaseStepNestedUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}