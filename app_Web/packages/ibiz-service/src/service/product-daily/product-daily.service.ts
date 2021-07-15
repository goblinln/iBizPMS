import { ProductDailyBaseService } from './product-daily-base.service';

/**
 * 产品日报服务
 *
 * @export
 * @class ProductDailyService
 * @extends {ProductDailyBaseService}
 */
export class ProductDailyService extends ProductDailyBaseService {
    /**
     * Creates an instance of ProductDailyService.
     * @memberof ProductDailyService
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
     * @return {*}  {ProductDailyService}
     * @memberof ProductDailyService
     */
    static getInstance(context?: any): ProductDailyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductDailyService` : `ProductDailyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductDailyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductDailyService;
