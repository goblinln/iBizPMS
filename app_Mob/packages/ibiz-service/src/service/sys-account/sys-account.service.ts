import { SysAccountBaseService } from './sys-account-base.service';

/**
 * 系统用户服务
 *
 * @export
 * @class SysAccountService
 * @extends {SysAccountBaseService}
 */
export class SysAccountService extends SysAccountBaseService {
    /**
     * Creates an instance of SysAccountService.
     * @memberof SysAccountService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysAccountService')) {
            return ___ibz___.sc.get('SysAccountService');
        }
        ___ibz___.sc.set('SysAccountService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysAccountService}
     * @memberof SysAccountService
     */
    static getInstance(): SysAccountService {
        if (!___ibz___.sc.has('SysAccountService')) {
            new SysAccountService();
        }
        return ___ibz___.sc.get('SysAccountService');
    }
}
export default SysAccountService;
