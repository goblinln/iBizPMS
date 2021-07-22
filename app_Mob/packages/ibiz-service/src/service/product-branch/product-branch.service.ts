import { ProductBranchBaseService } from './product-branch-base.service';

/**
 * 产品的分支和平台信息服务
 *
 * @export
 * @class ProductBranchService
 * @extends {ProductBranchBaseService}
 */
export class ProductBranchService extends ProductBranchBaseService {
    /**
     * Creates an instance of ProductBranchService.
     * @memberof ProductBranchService
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
     * @return {*}  {ProductBranchService}
     * @memberof ProductBranchService
     */
    static getInstance(context?: any): ProductBranchService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductBranchService` : `ProductBranchService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductBranchService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductBranchService;
