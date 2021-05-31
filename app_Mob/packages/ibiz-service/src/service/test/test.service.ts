import { TestBaseService } from './test-base.service';

/**
 * 产品服务
 *
 * @export
 * @class TestService
 * @extends {TestBaseService}
 */
export class TestService extends TestBaseService {
    /**
     * Creates an instance of TestService.
     * @memberof TestService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestService')) {
            return ___ibz___.sc.get('TestService');
        }
        ___ibz___.sc.set('TestService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestService}
     * @memberof TestService
     */
    static getInstance(): TestService {
        if (!___ibz___.sc.has('TestService')) {
            new TestService();
        }
        return ___ibz___.sc.get('TestService');
    }
}
export default TestService;
