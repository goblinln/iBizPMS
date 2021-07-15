import { ProductWeeklyBaseService } from './product-weekly-base.service';

/**
 * 产品周报服务
 *
 * @export
 * @class ProductWeeklyService
 * @extends {ProductWeeklyBaseService}
 */
export class ProductWeeklyService extends ProductWeeklyBaseService {
    /**
     * Creates an instance of ProductWeeklyService.
     * @memberof ProductWeeklyService
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
     * @return {*}  {ProductWeeklyService}
     * @memberof ProductWeeklyService
     */
    static getInstance(context?: any): ProductWeeklyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductWeeklyService` : `ProductWeeklyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductWeeklyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductWeeklyService;
