import { TestReportBaseService } from './test-report-base.service';

/**
 * 测试报告服务
 *
 * @export
 * @class TestReportService
 * @extends {TestReportBaseService}
 */
export class TestReportService extends TestReportBaseService {
    /**
     * Creates an instance of TestReportService.
     * @memberof TestReportService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TestReportService')) {
            return ___ibz___.sc.get('TestReportService');
        }
        ___ibz___.sc.set('TestReportService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestReportService}
     * @memberof TestReportService
     */
    static getInstance(): TestReportService {
        if (!___ibz___.sc.has('TestReportService')) {
            new TestReportService();
        }
        return ___ibz___.sc.get('TestReportService');
    }
}
export default TestReportService;
