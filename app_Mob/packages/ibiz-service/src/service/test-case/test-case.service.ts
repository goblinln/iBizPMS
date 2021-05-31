import { TestCaseBaseService } from './test-case-base.service';

/**
 * 测试用例服务
 *
 * @export
 * @class TestCaseService
 * @extends {TestCaseBaseService}
 */
export class TestCaseService extends TestCaseBaseService {
    /**
     * Creates an instance of TestCaseService.
     * @memberof TestCaseService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestCaseService')) {
            return ___ibz___.sc.get('TestCaseService');
        }
        ___ibz___.sc.set('TestCaseService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestCaseService}
     * @memberof TestCaseService
     */
    static getInstance(): TestCaseService {
        if (!___ibz___.sc.has('TestCaseService')) {
            new TestCaseService();
        }
        return ___ibz___.sc.get('TestCaseService');
    }
}
export default TestCaseService;
