import { ProductLifeBaseService } from './product-life-base.service';

/**
 * 产品生命周期服务
 *
 * @export
 * @class ProductLifeService
 * @extends {ProductLifeBaseService}
 */
export class ProductLifeService extends ProductLifeBaseService {
    /**
     * Creates an instance of ProductLifeService.
     * @memberof ProductLifeService
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
     * @return {*}  {ProductLifeService}
     * @memberof ProductLifeService
     */
    static getInstance(context?: any): ProductLifeService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductLifeService` : `ProductLifeService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductLifeService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductLifeService;
