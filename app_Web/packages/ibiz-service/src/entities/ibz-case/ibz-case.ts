import { IbzCaseBase } from './ibz-case-base';

/**
 * 测试用例
 *
 * @export
 * @class IbzCase
 * @extends {IbzCaseBase}
 * @implements {IIbzCase}
 */
export class IbzCase extends IbzCaseBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzCase
     */
    clone(): IbzCase {
        return new IbzCase(this);
    }
}
export default IbzCase;
