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
     * @return {*}  {TestCaseLibCaseStepNestedService}
     * @memberof TestCaseLibCaseStepNestedService
     */
    static getInstance(context?: any): TestCaseLibCaseStepNestedService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestCaseLibCaseStepNestedService` : `TestCaseLibCaseStepNestedService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestCaseLibCaseStepNestedService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestCaseLibCaseStepNestedService;
