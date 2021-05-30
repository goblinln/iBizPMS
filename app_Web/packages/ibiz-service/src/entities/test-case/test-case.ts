import { TestCaseBase } from './test-case-base';

/**
 * 测试用例
 *
 * @export
 * @class TestCase
 * @extends {TestCaseBase}
 * @implements {ITestCase}
 */
export class TestCase extends TestCaseBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestCase
     */
    clone(): TestCase {
        return new TestCase(this);
    }
}
export default TestCase;
