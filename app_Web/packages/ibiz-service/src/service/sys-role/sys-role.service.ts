import { SysRoleBaseService } from './sys-role-base.service';

/**
 * 系统角色服务
 *
 * @export
 * @class SysRoleService
 * @extends {SysRoleBaseService}
 */
export class SysRoleService extends SysRoleBaseService {
    /**
     * Creates an instance of SysRoleService.
     * @memberof SysRoleService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysRoleService')) {
            return ___ibz___.sc.get('SysRoleService');
        }
        ___ibz___.sc.set('SysRoleService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysRoleService}
     * @memberof SysRoleService
     */
    static getInstance(): SysRoleService {
        if (!___ibz___.sc.has('SysRoleService')) {
            new SysRoleService();
        }
        return ___ibz___.sc.get('SysRoleService');
    }
}
export default SysRoleService;