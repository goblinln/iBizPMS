import { ProductSumBaseService } from './product-sum-base.service';

/**
 * 产品汇总表服务
 *
 * @export
 * @class ProductSumService
 * @extends {ProductSumBaseService}
 */
export class ProductSumService extends ProductSumBaseService {
    /**
     * Creates an instance of ProductSumService.
     * @memberof ProductSumService
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
     * @return {*}  {ProductSumService}
     * @memberof ProductSumService
     */
    static getInstance(context?: any): ProductSumService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductSumService` : `ProductSumService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductSumService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductSumService;
