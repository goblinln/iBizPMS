import { TestCaseLibBase } from './test-case-lib-base';

/**
 * 用例库
 *
 * @export
 * @class TestCaseLib
 * @extends {TestCaseLibBase}
 * @implements {ITestCaseLib}
 */
export class TestCaseLib extends TestCaseLibBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestCaseLib
     */
    clone(): TestCaseLib {
        return new TestCaseLib(this);
    }
}
export default TestCaseLib;
