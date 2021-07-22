import { SysAccountBase } from './sys-account-base';

/**
 * 系统用户
 *
 * @export
 * @class SysAccount
 * @extends {SysAccountBase}
 * @implements {ISysAccount}
 */
export class SysAccount extends SysAccountBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysAccount
     */
    clone(): SysAccount {
        return new SysAccount(this);
    }
}
export default SysAccount;
