import { TestCaseLibModuleBaseService } from './test-case-lib-module-base.service';

/**
 * 用例库模块服务
 *
 * @export
 * @class TestCaseLibModuleService
 * @extends {TestCaseLibModuleBaseService}
 */
export class TestCaseLibModuleService extends TestCaseLibModuleBaseService {
    /**
     * Creates an instance of TestCaseLibModuleService.
     * @memberof TestCaseLibModuleService
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
     * @return {*}  {TestCaseLibModuleService}
     * @memberof TestCaseLibModuleService
     */
    static getInstance(context?: any): TestCaseLibModuleService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestCaseLibModuleService` : `TestCaseLibModuleService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestCaseLibModuleService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestCaseLibModuleService;
