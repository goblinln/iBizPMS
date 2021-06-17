import { TestCaseLibCaseStepNestedUIServiceBase } from './test-case-lib-case-step-nested-ui-service-base';

/**
 * 用例库用例步骤UI服务对象
 *
 * @export
 * @class TestCaseLibCaseStepNestedUIService
 */
export default class TestCaseLibCaseStepNestedUIService extends TestCaseLibCaseStepNestedUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestCaseLibCaseStepNestedUIService
     */
    private static basicUIServiceInstance: TestCaseLibCaseStepNestedUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestCaseLibCaseStepNestedUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestCaseLibCaseStepNestedUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseLibCaseStepNestedUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestCaseLibCaseStepNestedUIService
     */
    public static getInstance(context: any): TestCaseLibCaseStepNestedUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestCaseLibCaseStepNestedUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestCaseLibCaseStepNestedUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestCaseLibCaseStepNestedUIService.UIServiceMap.set(context.srfdynainstid, new TestCaseLibCaseStepNestedUIService({context:context}));
            }
            return TestCaseLibCaseStepNestedUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}