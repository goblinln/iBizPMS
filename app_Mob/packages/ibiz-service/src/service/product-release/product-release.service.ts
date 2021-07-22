import { ProductReleaseBaseService } from './product-release-base.service';

/**
 * 发布服务
 *
 * @export
 * @class ProductReleaseService
 * @extends {ProductReleaseBaseService}
 */
export class ProductReleaseService extends ProductReleaseBaseService {
    /**
     * Creates an instance of ProductReleaseService.
     * @memberof ProductReleaseService
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
     * @return {*}  {ProductReleaseService}
     * @memberof ProductReleaseService
     */
    static getInstance(context?: any): ProductReleaseService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductReleaseService` : `ProductReleaseService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductReleaseService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProductReleaseService;
