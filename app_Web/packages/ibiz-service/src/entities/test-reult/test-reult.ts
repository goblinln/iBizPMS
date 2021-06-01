import { TestReultBase } from './test-reult-base';

/**
 * 测试结果
 *
 * @export
 * @class TestReult
 * @extends {TestReultBase}
 * @implements {ITestReult}
 */
export class TestReult extends TestReultBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestReult
     */
    clone(): TestReult {
        return new TestReult(this);
    }
}
export default TestReult;
