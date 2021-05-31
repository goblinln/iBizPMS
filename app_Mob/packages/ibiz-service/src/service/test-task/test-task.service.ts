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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestTaskService')) {
            return ___ibz___.sc.get('TestTaskService');
        }
        ___ibz___.sc.set('TestTaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestTaskService}
     * @memberof TestTaskService
     */
    static getInstance(): TestTaskService {
        if (!___ibz___.sc.has('TestTaskService')) {
            new TestTaskService();
        }
        return ___ibz___.sc.get('TestTaskService');
    }
}
export default TestTaskService;
