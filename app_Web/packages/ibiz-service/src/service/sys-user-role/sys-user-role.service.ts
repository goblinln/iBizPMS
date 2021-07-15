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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {SysUserRoleService}
     * @memberof SysUserRoleService
     */
    static getInstance(context?: any): SysUserRoleService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysUserRoleService` : `SysUserRoleService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysUserRoleService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysUserRoleService;
