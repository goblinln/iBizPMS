import { TestCaseLibCaseStepNestedBaseService } from './test-case-lib-case-step-nested-base.service';

/**
 * 用例库用例步骤服务
 *
 * @export
 * @class TestCaseLibCaseStepNestedService
 * @extends {TestCaseLibCaseStepNestedBaseService}
 */
export class TestCaseLibCaseStepNestedService extends TestCaseLibCaseStepNestedBaseService {
    /**
     * Creates an instance of TestCaseLibCaseStepNestedService.
     * @memberof TestCaseLibCaseStepNestedService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestCaseLibCaseStepNestedService')) {
            return ___ibz___.sc.get('TestCaseLibCaseStepNestedService');
        }
        ___ibz___.sc.set('TestCaseLibCaseStepNestedService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestCaseLibCaseStepNestedService}
     * @memberof TestCaseLibCaseStepNestedService
     */
    static getInstance(): TestCaseLibCaseStepNestedService {
        if (!___ibz___.sc.has('TestCaseLibCaseStepNestedService')) {
            new TestCaseLibCaseStepNestedService();
        }
        return ___ibz___.sc.get('TestCaseLibCaseStepNestedService');
    }
}
export default TestCaseLibCaseStepNestedService;
