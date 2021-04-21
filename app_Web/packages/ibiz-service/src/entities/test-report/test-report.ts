import { TestReportBase } from './test-report-base';

/**
 * 测试报告
 *
 * @export
 * @class TestReport
 * @extends {TestReportBase}
 * @implements {ITestReport}
 */
export class TestReport extends TestReportBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestReport
     */
    clone(): TestReport {
        return new TestReport(this);
    }
}
export default TestReport;
