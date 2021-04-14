import { CaseStepBase } from './case-step-base';

/**
 * 用例步骤
 *
 * @export
 * @class CaseStep
 * @extends {CaseStepBase}
 * @implements {ICaseStep}
 */
export class CaseStep extends CaseStepBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof CaseStep
     */
    clone(): CaseStep {
        return new CaseStep(this);
    }
}
export default CaseStep;
