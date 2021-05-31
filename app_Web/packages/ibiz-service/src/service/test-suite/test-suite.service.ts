import { TestSuiteBaseService } from './test-suite-base.service';

/**
 * 测试套件服务
 *
 * @export
 * @class TestSuiteService
 * @extends {TestSuiteBaseService}
 */
export class TestSuiteService extends TestSuiteBaseService {
    /**
     * Creates an instance of TestSuiteService.
     * @memberof TestSuiteService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestSuiteService')) {
            return ___ibz___.sc.get('TestSuiteService');
        }
        ___ibz___.sc.set('TestSuiteService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestSuiteService}
     * @memberof TestSuiteService
     */
    static getInstance(): TestSuiteService {
        if (!___ibz___.sc.has('TestSuiteService')) {
            new TestSuiteService();
        }
        return ___ibz___.sc.get('TestSuiteService');
    }
}
export default TestSuiteService;
