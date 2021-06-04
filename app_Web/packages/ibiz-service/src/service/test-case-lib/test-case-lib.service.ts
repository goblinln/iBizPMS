import { TestCaseLibBaseService } from './test-case-lib-base.service';

/**
 * 用例库服务
 *
 * @export
 * @class TestCaseLibService
 * @extends {TestCaseLibBaseService}
 */
export class TestCaseLibService extends TestCaseLibBaseService {
    /**
     * Creates an instance of TestCaseLibService.
     * @memberof TestCaseLibService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestCaseLibService')) {
            return ___ibz___.sc.get('TestCaseLibService');
        }
        ___ibz___.sc.set('TestCaseLibService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestCaseLibService}
     * @memberof TestCaseLibService
     */
    static getInstance(): TestCaseLibService {
        if (!___ibz___.sc.has('TestCaseLibService')) {
            new TestCaseLibService();
        }
        return ___ibz___.sc.get('TestCaseLibService');
    }
}
export default TestCaseLibService;
