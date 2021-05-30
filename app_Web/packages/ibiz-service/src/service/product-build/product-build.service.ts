import { ProductBuildBaseService } from './product-build-base.service';

/**
 * 版本服务
 *
 * @export
 * @class ProductBuildService
 * @extends {ProductBuildBaseService}
 */
export class ProductBuildService extends ProductBuildBaseService {
    /**
     * Creates an instance of ProductBuildService.
     * @memberof ProductBuildService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductBuildService')) {
            return ___ibz___.sc.get('ProductBuildService');
        }
        ___ibz___.sc.set('ProductBuildService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductBuildService}
     * @memberof ProductBuildService
     */
    static getInstance(): ProductBuildService {
        if (!___ibz___.sc.has('ProductBuildService')) {
            new ProductBuildService();
        }
        return ___ibz___.sc.get('ProductBuildService');
    }
}
export default ProductBuildService;
