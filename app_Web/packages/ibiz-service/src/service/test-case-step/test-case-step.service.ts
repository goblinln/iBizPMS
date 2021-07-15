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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {TestCaseStepService}
     * @memberof TestCaseStepService
     */
    static getInstance(context?: any): TestCaseStepService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestCaseStepService` : `TestCaseStepService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestCaseStepService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestCaseStepService;
