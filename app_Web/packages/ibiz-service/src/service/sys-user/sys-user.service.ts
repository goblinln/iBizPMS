import { SysUserBaseService } from './sys-user-base.service';

/**
 * 系统用户服务
 *
 * @export
 * @class SysUserService
 * @extends {SysUserBaseService}
 */
export class SysUserService extends SysUserBaseService {
    /**
     * Creates an instance of SysUserService.
     * @memberof SysUserService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysUserService')) {
            return ___ibz___.sc.get('SysUserService');
        }
        ___ibz___.sc.set('SysUserService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysUserService}
     * @memberof SysUserService
     */
    static getInstance(): SysUserService {
        if (!___ibz___.sc.has('SysUserService')) {
            new SysUserService();
        }
        return ___ibz___.sc.get('SysUserService');
    }
}
export default SysUserService;
