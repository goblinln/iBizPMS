import { IBZCaseStepBase } from './ibzcase-step-base';

/**
 * 用例步骤
 *
 * @export
 * @class IBZCaseStep
 * @extends {IBZCaseStepBase}
 * @implements {IIBZCaseStep}
 */
export class IBZCaseStep extends IBZCaseStepBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZCaseStep
     */
    clone(): IBZCaseStep {
        return new IBZCaseStep(this);
    }
}
export default IBZCaseStep;
