import { AccountBugBase } from './account-bug-base';

/**
 * Bug
 *
 * @export
 * @class AccountBug
 * @extends {AccountBugBase}
 * @implements {IAccountBug}
 */
export class AccountBug extends AccountBugBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof AccountBug
     */
    clone(): AccountBug {
        return new AccountBug(this);
    }
}
export default AccountBug;
