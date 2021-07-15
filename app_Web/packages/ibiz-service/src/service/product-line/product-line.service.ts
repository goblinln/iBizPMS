import { ProductLineBaseService } from './product-line-base.service';

/**
 * 产品线服务
 *
 * @export
 * @class ProductLineService
 * @extends {ProductLineBaseService}
 */
export class ProductLineService extends ProductLineBaseService {
    /**
     * Creates an instance of ProductLineService.
     * @memberof ProductLineService
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
     * @return {*}  {ProductLineService}
     * @memberof ProductLineService
     */
    static getInstance(context?: any): ProductLineService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductLineService` : `ProductLineService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductLineService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductLineService;
