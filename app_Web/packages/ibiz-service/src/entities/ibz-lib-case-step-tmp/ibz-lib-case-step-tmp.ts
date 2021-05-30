import { IbzLibCaseStepTmpBase } from './ibz-lib-case-step-tmp-base';

/**
 * 用例库用例步骤
 *
 * @export
 * @class IbzLibCaseStepTmp
 * @extends {IbzLibCaseStepTmpBase}
 * @implements {IIbzLibCaseStepTmp}
 */
export class IbzLibCaseStepTmp extends IbzLibCaseStepTmpBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzLibCaseStepTmp
     */
    clone(): IbzLibCaseStepTmp {
        return new IbzLibCaseStepTmp(this);
    }
}
export default IbzLibCaseStepTmp;
