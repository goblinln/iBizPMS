import { TestRunBase } from './test-run-base';

/**
 * 测试运行
 *
 * @export
 * @class TestRun
 * @extends {TestRunBase}
 * @implements {ITestRun}
 */
export class TestRun extends TestRunBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestRun
     */
    clone(): TestRun {
        return new TestRun(this);
    }
}
export default TestRun;
