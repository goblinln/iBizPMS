import { TestCaseStepBaseService } from './test-case-step-base.service';

/**
 * 用例步骤服务
 *
 * @export
 * @class TestCaseStepService
 * @extends {TestCaseStepBaseService}
 */
export class TestCaseStepService extends TestCaseStepBaseService {
    /**
     * Creates an instance of TestCaseStepService.
     * @memberof TestCaseStepService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestCaseStepService')) {
            return ___ibz___.sc.get('TestCaseStepService');
        }
        ___ibz___.sc.set('TestCaseStepService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestCaseStepService}
     * @memberof TestCaseStepService
     */
    static getInstance(): TestCaseStepService {
        if (!___ibz___.sc.has('TestCaseStepService')) {
            new TestCaseStepService();
        }
        return ___ibz___.sc.get('TestCaseStepService');
    }
}
export default TestCaseStepService;
