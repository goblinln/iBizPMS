import { ProductMonthlyBaseService } from './product-monthly-base.service';

/**
 * 产品月报服务
 *
 * @export
 * @class ProductMonthlyService
 * @extends {ProductMonthlyBaseService}
 */
export class ProductMonthlyService extends ProductMonthlyBaseService {
    /**
     * Creates an instance of ProductMonthlyService.
     * @memberof ProductMonthlyService
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
     * @return {*}  {ProductMonthlyService}
     * @memberof ProductMonthlyService
     */
    static getInstance(context?: any): ProductMonthlyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductMonthlyService` : `ProductMonthlyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductMonthlyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductMonthlyService;
