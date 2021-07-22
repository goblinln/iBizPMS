import { DynaDashboardBaseService } from './dyna-dashboard-base.service';

/**
 * 动态数据看板服务
 *
 * @export
 * @class DynaDashboardService
 * @extends {DynaDashboardBaseService}
 */
export class DynaDashboardService extends DynaDashboardBaseService {
    /**
     * Creates an instance of DynaDashboardService.
     * @memberof DynaDashboardService
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
     * @return {*}  {DynaDashboardService}
     * @memberof DynaDashboardService
     */
    static getInstance(context?: any): DynaDashboardService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}DynaDashboardService` : `DynaDashboardService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new DynaDashboardService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default DynaDashboardService;
