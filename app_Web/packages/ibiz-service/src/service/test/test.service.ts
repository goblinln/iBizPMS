import { TestBaseService } from './test-base.service';

/**
 * 产品服务
 *
 * @export
 * @class TestService
 * @extends {TestBaseService}
 */
export class TestService extends TestBaseService {
    /**
     * Creates an instance of TestService.
     * @memberof TestService
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
     * @return {*}  {TestService}
     * @memberof TestService
     */
    static getInstance(context?: any): TestService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestService` : `TestService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestService;
