import { ProductBugBaseService } from './product-bug-base.service';

/**
 * Bug服务
 *
 * @export
 * @class ProductBugService
 * @extends {ProductBugBaseService}
 */
export class ProductBugService extends ProductBugBaseService {
    /**
     * Creates an instance of ProductBugService.
     * @memberof ProductBugService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductBugService')) {
            return ___ibz___.sc.get('ProductBugService');
        }
        ___ibz___.sc.set('ProductBugService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductBugService}
     * @memberof ProductBugService
     */
    static getInstance(): ProductBugService {
        if (!___ibz___.sc.has('ProductBugService')) {
            new ProductBugService();
        }
        return ___ibz___.sc.get('ProductBugService');
    }
}
export default ProductBugService;
