import { TestSuiteBase } from './test-suite-base';

/**
 * 测试套件
 *
 * @export
 * @class TestSuite
 * @extends {TestSuiteBase}
 * @implements {ITestSuite}
 */
export class TestSuite extends TestSuiteBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestSuite
     */
    clone(): TestSuite {
        return new TestSuite(this);
    }
}
export default TestSuite;
