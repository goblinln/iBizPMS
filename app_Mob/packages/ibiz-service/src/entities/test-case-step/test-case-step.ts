import { TestCaseStepBase } from './test-case-step-base';

/**
 * 用例步骤
 *
 * @export
 * @class TestCaseStep
 * @extends {TestCaseStepBase}
 * @implements {ITestCaseStep}
 */
export class TestCaseStep extends TestCaseStepBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestCaseStep
     */
    clone(): TestCaseStep {
        return new TestCaseStep(this);
    }
}
export default TestCaseStep;
