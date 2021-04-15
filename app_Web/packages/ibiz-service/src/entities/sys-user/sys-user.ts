import { SysUserBase } from './sys-user-base';

/**
 * 系统用户
 *
 * @export
 * @class SysUser
 * @extends {SysUserBase}
 * @implements {ISysUser}
 */
export class SysUser extends SysUserBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysUser
     */
    clone(): SysUser {
        return new SysUser(this);
    }
}
export default SysUser;
