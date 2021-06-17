import { TestCaseStepNestedBase } from './test-case-step-nested-base';

/**
 * 用例步骤
 *
 * @export
 * @class TestCaseStepNested
 * @extends {TestCaseStepNestedBase}
 * @implements {ITestCaseStepNested}
 */
export class TestCaseStepNested extends TestCaseStepNestedBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestCaseStepNested
     */
    clone(): TestCaseStepNested {
        return new TestCaseStepNested(this);
    }
}
export default TestCaseStepNested;
