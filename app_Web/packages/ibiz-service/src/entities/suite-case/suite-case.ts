import { SuiteCaseBase } from './suite-case-base';

/**
 * 套件用例
 *
 * @export
 * @class SuiteCase
 * @extends {SuiteCaseBase}
 * @implements {ISuiteCase}
 */
export class SuiteCase extends SuiteCaseBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SuiteCase
     */
    clone(): SuiteCase {
        return new SuiteCase(this);
    }
}
export default SuiteCase;
