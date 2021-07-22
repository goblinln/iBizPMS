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
     * @return {*}  {SysAccountService}
     * @memberof SysAccountService
     */
    static getInstance(context?: any): SysAccountService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysAccountService` : `SysAccountService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysAccountService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysAccountService;
