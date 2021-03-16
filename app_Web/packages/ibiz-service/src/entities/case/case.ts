import { CaseBase } from './case-base';

/**
 * 测试用例
 *
 * @export
 * @class Case
 * @extends {CaseBase}
 * @implements {ICase}
 */
export class Case extends CaseBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Case
     */
    clone(): Case {
        return new Case(this);
    }
}
export default Case;
