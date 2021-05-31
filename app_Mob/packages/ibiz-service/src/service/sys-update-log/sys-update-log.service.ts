import { SysUpdateLogBaseService } from './sys-update-log-base.service';

/**
 * 更新日志服务
 *
 * @export
 * @class SysUpdateLogService
 * @extends {SysUpdateLogBaseService}
 */
export class SysUpdateLogService extends SysUpdateLogBaseService {
    /**
     * Creates an instance of SysUpdateLogService.
     * @memberof SysUpdateLogService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysUpdateLogService')) {
            return ___ibz___.sc.get('SysUpdateLogService');
        }
        ___ibz___.sc.set('SysUpdateLogService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysUpdateLogService}
     * @memberof SysUpdateLogService
     */
    static getInstance(): SysUpdateLogService {
        if (!___ibz___.sc.has('SysUpdateLogService')) {
            new SysUpdateLogService();
        }
        return ___ibz___.sc.get('SysUpdateLogService');
    }
}
export default SysUpdateLogService;
