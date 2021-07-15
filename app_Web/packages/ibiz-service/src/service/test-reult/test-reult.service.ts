import { TestReultBaseService } from './test-reult-base.service';

/**
 * 测试结果服务
 *
 * @export
 * @class TestReultService
 * @extends {TestReultBaseService}
 */
export class TestReultService extends TestReultBaseService {
    /**
     * Creates an instance of TestReultService.
     * @memberof TestReultService
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
     * @return {*}  {TestReultService}
     * @memberof TestReultService
     */
    static getInstance(context?: any): TestReultService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestReultService` : `TestReultService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestReultService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestReultService;
