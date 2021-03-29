import { TestModuleBaseService } from './test-module-base.service';

/**
 * 测试模块服务
 *
 * @export
 * @class TestModuleService
 * @extends {TestModuleBaseService}
 */
export class TestModuleService extends TestModuleBaseService {
    /**
     * Creates an instance of TestModuleService.
     * @memberof TestModuleService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestModuleService')) {
            return ___ibz___.sc.get('TestModuleService');
        }
        ___ibz___.sc.set('TestModuleService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestModuleService}
     * @memberof TestModuleService
     */
    static getInstance(): TestModuleService {
        if (!___ibz___.sc.has('TestModuleService')) {
            new TestModuleService();
        }
        return ___ibz___.sc.get('TestModuleService');
    }
}
export default TestModuleService;