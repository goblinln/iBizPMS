import { TestRunBaseService } from './test-run-base.service';

/**
 * 测试运行服务
 *
 * @export
 * @class TestRunService
 * @extends {TestRunBaseService}
 */
export class TestRunService extends TestRunBaseService {
    /**
     * Creates an instance of TestRunService.
     * @memberof TestRunService
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
     * @return {*}  {TestRunService}
     * @memberof TestRunService
     */
    static getInstance(context?: any): TestRunService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestRunService` : `TestRunService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestRunService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestRunService;
