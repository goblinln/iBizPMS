import { DynaFilterBaseService } from './dyna-filter-base.service';

/**
 * 动态搜索栏服务
 *
 * @export
 * @class DynaFilterService
 * @extends {DynaFilterBaseService}
 */
export class DynaFilterService extends DynaFilterBaseService {
    /**
     * Creates an instance of DynaFilterService.
     * @memberof DynaFilterService
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
     * @return {*}  {DynaFilterService}
     * @memberof DynaFilterService
     */
    static getInstance(context?: any): DynaFilterService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}DynaFilterService` : `DynaFilterService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new DynaFilterService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default DynaFilterService;
