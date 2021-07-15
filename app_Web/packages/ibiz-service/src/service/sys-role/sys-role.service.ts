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
     * @return {*}  {SysRoleService}
     * @memberof SysRoleService
     */
    static getInstance(context?: any): SysRoleService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysRoleService` : `SysRoleService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysRoleService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysRoleService;
