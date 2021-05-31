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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestRunService')) {
            return ___ibz___.sc.get('TestRunService');
        }
        ___ibz___.sc.set('TestRunService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestRunService}
     * @memberof TestRunService
     */
    static getInstance(): TestRunService {
        if (!___ibz___.sc.has('TestRunService')) {
            new TestRunService();
        }
        return ___ibz___.sc.get('TestRunService');
    }
}
export default TestRunService;
