import { TestResultBaseService } from './test-result-base.service';

/**
 * 测试结果服务
 *
 * @export
 * @class TestResultService
 * @extends {TestResultBaseService}
 */
export class TestResultService extends TestResultBaseService {
    /**
     * Creates an instance of TestResultService.
     * @memberof TestResultService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestResultService')) {
            return ___ibz___.sc.get('TestResultService');
        }
        ___ibz___.sc.set('TestResultService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestResultService}
     * @memberof TestResultService
     */
    static getInstance(): TestResultService {
        if (!___ibz___.sc.has('TestResultService')) {
            new TestResultService();
        }
        return ___ibz___.sc.get('TestResultService');
    }
}
export default TestResultService;