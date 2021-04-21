import { SysUserRoleBaseService } from './sys-user-role-base.service';

/**
 * 用户角色关系服务
 *
 * @export
 * @class SysUserRoleService
 * @extends {SysUserRoleBaseService}
 */
export class SysUserRoleService extends SysUserRoleBaseService {
    /**
     * Creates an instance of SysUserRoleService.
     * @memberof SysUserRoleService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysUserRoleService')) {
            return ___ibz___.sc.get('SysUserRoleService');
        }
        ___ibz___.sc.set('SysUserRoleService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysUserRoleService}
     * @memberof SysUserRoleService
     */
    static getInstance(): SysUserRoleService {
        if (!___ibz___.sc.has('SysUserRoleService')) {
            new SysUserRoleService();
        }
        return ___ibz___.sc.get('SysUserRoleService');
    }
}
export default SysUserRoleService;
