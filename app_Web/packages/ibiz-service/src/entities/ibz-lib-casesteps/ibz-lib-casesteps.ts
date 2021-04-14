import { IbzLibCasestepsBase } from './ibz-lib-casesteps-base';

/**
 * 用例库用例步骤
 *
 * @export
 * @class IbzLibCasesteps
 * @extends {IbzLibCasestepsBase}
 * @implements {IIbzLibCasesteps}
 */
export class IbzLibCasesteps extends IbzLibCasestepsBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzLibCasesteps
     */
    clone(): IbzLibCasesteps {
        return new IbzLibCasesteps(this);
    }
}
export default IbzLibCasesteps;
