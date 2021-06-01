import { AccountBase } from './account-base';

/**
 * 系统用户
 *
 * @export
 * @class Account
 * @extends {AccountBase}
 * @implements {IAccount}
 */
export class Account extends AccountBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Account
     */
    clone(): Account {
        return new Account(this);
    }
}
export default Account;
