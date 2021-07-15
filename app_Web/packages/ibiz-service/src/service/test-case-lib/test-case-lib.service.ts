import { TestCaseLibBaseService } from './test-case-lib-base.service';

/**
 * 用例库服务
 *
 * @export
 * @class TestCaseLibService
 * @extends {TestCaseLibBaseService}
 */
export class TestCaseLibService extends TestCaseLibBaseService {
    /**
     * Creates an instance of TestCaseLibService.
     * @memberof TestCaseLibService
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
     * @return {*}  {TestCaseLibService}
     * @memberof TestCaseLibService
     */
    static getInstance(context?: any): TestCaseLibService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestCaseLibService` : `TestCaseLibService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestCaseLibService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestCaseLibService;
