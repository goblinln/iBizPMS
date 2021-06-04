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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestCaseLibModuleService')) {
            return ___ibz___.sc.get('TestCaseLibModuleService');
        }
        ___ibz___.sc.set('TestCaseLibModuleService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestCaseLibModuleService}
     * @memberof TestCaseLibModuleService
     */
    static getInstance(): TestCaseLibModuleService {
        if (!___ibz___.sc.has('TestCaseLibModuleService')) {
            new TestCaseLibModuleService();
        }
        return ___ibz___.sc.get('TestCaseLibModuleService');
    }
}
export default TestCaseLibModuleService;
