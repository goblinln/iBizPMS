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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestReultService')) {
            return ___ibz___.sc.get('TestReultService');
        }
        ___ibz___.sc.set('TestReultService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestReultService}
     * @memberof TestReultService
     */
    static getInstance(): TestReultService {
        if (!___ibz___.sc.has('TestReultService')) {
            new TestReultService();
        }
        return ___ibz___.sc.get('TestReultService');
    }
}
export default TestReultService;
