import { SysUserRoleBase } from './sys-user-role-base';

/**
 * 用户角色关系
 *
 * @export
 * @class SysUserRole
 * @extends {SysUserRoleBase}
 * @implements {ISysUserRole}
 */
export class SysUserRole extends SysUserRoleBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysUserRole
     */
    clone(): SysUserRole {
        return new SysUserRole(this);
    }
}
export default SysUserRole;
