import { TestModuleBaseService } from './test-module-base.service';

/**
 * 测试模块服务
 *
 * @export
 * @class TestModuleService
 * @extends {TestModuleBaseService}
 */
export class TestModuleService extends TestModuleBaseService {
    /**
     * Creates an instance of TestModuleService.
     * @memberof TestModuleService
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
     * @return {*}  {TestModuleService}
     * @memberof TestModuleService
     */
    static getInstance(context?: any): TestModuleService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestModuleService` : `TestModuleService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestModuleService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestModuleService;
