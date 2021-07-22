import { ProductPlanBaseService } from './product-plan-base.service';

/**
 * 产品计划服务
 *
 * @export
 * @class ProductPlanService
 * @extends {ProductPlanBaseService}
 */
export class ProductPlanService extends ProductPlanBaseService {
    /**
     * Creates an instance of ProductPlanService.
     * @memberof ProductPlanService
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
     * @return {*}  {ProductPlanService}
     * @memberof ProductPlanService
     */
    static getInstance(context?: any): ProductPlanService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductPlanService` : `ProductPlanService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductPlanService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductPlanService;
