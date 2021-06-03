import { AccountTestCaseBase } from './account-test-case-base';

/**
 * 测试用例
 *
 * @export
 * @class AccountTestCase
 * @extends {AccountTestCaseBase}
 * @implements {IAccountTestCase}
 */
export class AccountTestCase extends AccountTestCaseBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof AccountTestCase
     */
    clone(): AccountTestCase {
        return new AccountTestCase(this);
    }
}
export default AccountTestCase;
