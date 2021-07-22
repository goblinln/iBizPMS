import { ProductStatsBaseService } from './product-stats-base.service';

/**
 * 产品统计服务
 *
 * @export
 * @class ProductStatsService
 * @extends {ProductStatsBaseService}
 */
export class ProductStatsService extends ProductStatsBaseService {
    /**
     * Creates an instance of ProductStatsService.
     * @memberof ProductStatsService
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
     * @return {*}  {ProductStatsService}
     * @memberof ProductStatsService
     */
    static getInstance(context?: any): ProductStatsService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductStatsService` : `ProductStatsService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductStatsService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductStatsService;
