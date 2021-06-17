import { TestCaseStepNestedBaseService } from './test-case-step-nested-base.service';

/**
 * 用例步骤服务
 *
 * @export
 * @class TestCaseStepNestedService
 * @extends {TestCaseStepNestedBaseService}
 */
export class TestCaseStepNestedService extends TestCaseStepNestedBaseService {
    /**
     * Creates an instance of TestCaseStepNestedService.
     * @memberof TestCaseStepNestedService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestCaseStepNestedService')) {
            return ___ibz___.sc.get('TestCaseStepNestedService');
        }
        ___ibz___.sc.set('TestCaseStepNestedService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestCaseStepNestedService}
     * @memberof TestCaseStepNestedService
     */
    static getInstance(): TestCaseStepNestedService {
        if (!___ibz___.sc.has('TestCaseStepNestedService')) {
            new TestCaseStepNestedService();
        }
        return ___ibz___.sc.get('TestCaseStepNestedService');
    }
}
export default TestCaseStepNestedService;
