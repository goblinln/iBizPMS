import { TestResultBase } from './test-result-base';

/**
 * 测试结果
 *
 * @export
 * @class TestResult
 * @extends {TestResultBase}
 * @implements {ITestResult}
 */
export class TestResult extends TestResultBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestResult
     */
    clone(): TestResult {
        return new TestResult(this);
    }
}
export default TestResult;
