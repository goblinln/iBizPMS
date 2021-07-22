import { TestTaskBaseService } from './test-task-base.service';

/**
 * 测试版本服务
 *
 * @export
 * @class TestTaskService
 * @extends {TestTaskBaseService}
 */
export class TestTaskService extends TestTaskBaseService {
    /**
     * Creates an instance of TestTaskService.
     * @memberof TestTaskService
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
     * @return {*}  {TestTaskService}
     * @memberof TestTaskService
     */
    static getInstance(context?: any): TestTaskService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestTaskService` : `TestTaskService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestTaskService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TestTaskService;
