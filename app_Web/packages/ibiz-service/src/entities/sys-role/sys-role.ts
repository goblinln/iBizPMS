import { SysRoleBase } from './sys-role-base';

/**
 * 系统角色
 *
 * @export
 * @class SysRole
 * @extends {SysRoleBase}
 * @implements {ISysRole}
 */
export class SysRole extends SysRoleBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysRole
     */
    clone(): SysRole {
        return new SysRole(this);
    }
}
export default SysRole;
