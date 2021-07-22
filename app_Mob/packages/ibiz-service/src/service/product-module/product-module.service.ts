import { ProductModuleBaseService } from './product-module-base.service';

/**
 * 需求模块服务
 *
 * @export
 * @class ProductModuleService
 * @extends {ProductModuleBaseService}
 */
export class ProductModuleService extends ProductModuleBaseService {
    /**
     * Creates an instance of ProductModuleService.
     * @memberof ProductModuleService
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
     * @return {*}  {ProductModuleService}
     * @memberof ProductModuleService
     */
    static getInstance(context?: any): ProductModuleService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductModuleService` : `ProductModuleService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductModuleService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductModuleService;
