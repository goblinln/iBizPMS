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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductReleaseService')) {
            return ___ibz___.sc.get('ProductReleaseService');
        }
        ___ibz___.sc.set('ProductReleaseService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductReleaseService}
     * @memberof ProductReleaseService
     */
    static getInstance(): ProductReleaseService {
        if (!___ibz___.sc.has('ProductReleaseService')) {
            new ProductReleaseService();
        }
        return ___ibz___.sc.get('ProductReleaseService');
    }
}
export default ProductReleaseService;
