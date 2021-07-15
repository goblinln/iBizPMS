import { PSSystemDBCfgBaseService } from './pssystem-dbcfg-base.service';

/**
 * 系统数据库服务
 *
 * @export
 * @class PSSystemDBCfgService
 * @extends {PSSystemDBCfgBaseService}
 */
export class PSSystemDBCfgService extends PSSystemDBCfgBaseService {
    /**
     * Creates an instance of PSSystemDBCfgService.
     * @memberof PSSystemDBCfgService
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
     * @return {*}  {PSSystemDBCfgService}
     * @memberof PSSystemDBCfgService
     */
    static getInstance(context?: any): PSSystemDBCfgService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}PSSystemDBCfgService` : `PSSystemDBCfgService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new PSSystemDBCfgService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default PSSystemDBCfgService;
