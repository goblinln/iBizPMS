import { TestCaseLibCaseStepNestedBase } from './test-case-lib-case-step-nested-base';

/**
 * 用例库用例步骤
 *
 * @export
 * @class TestCaseLibCaseStepNested
 * @extends {TestCaseLibCaseStepNestedBase}
 * @implements {ITestCaseLibCaseStepNested}
 */
export class TestCaseLibCaseStepNested extends TestCaseLibCaseStepNestedBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestCaseLibCaseStepNested
     */
    clone(): TestCaseLibCaseStepNested {
        return new TestCaseLibCaseStepNested(this);
    }
}
export default TestCaseLibCaseStepNested;
