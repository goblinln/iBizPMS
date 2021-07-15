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
     * @return {*}  {SysUpdateLogService}
     * @memberof SysUpdateLogService
     */
    static getInstance(context?: any): SysUpdateLogService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysUpdateLogService` : `SysUpdateLogService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysUpdateLogService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysUpdateLogService;
