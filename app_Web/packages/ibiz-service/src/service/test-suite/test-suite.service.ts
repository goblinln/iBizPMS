import { TestSuiteBaseService } from './test-suite-base.service';

/**
 * 测试套件服务
 *
 * @export
 * @class TestSuiteService
 * @extends {TestSuiteBaseService}
 */
export class TestSuiteService extends TestSuiteBaseService {
    /**
     * Creates an instance of TestSuiteService.
     * @memberof TestSuiteService
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
     * @return {*}  {TestSuiteService}
     * @memberof TestSuiteService
     */
    static getInstance(context?: any): TestSuiteService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestSuiteService` : `TestSuiteService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestSuiteService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestSuiteService;
