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
     * @return {*}  {TestCaseStepNestedService}
     * @memberof TestCaseStepNestedService
     */
    static getInstance(context?: any): TestCaseStepNestedService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestCaseStepNestedService` : `TestCaseStepNestedService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestCaseStepNestedService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestCaseStepNestedService;
