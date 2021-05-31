import { ProductBaseService } from './product-base.service';

/**
 * 产品服务
 *
 * @export
 * @class ProductService
 * @extends {ProductBaseService}
 */
export class ProductService extends ProductBaseService {
    /**
     * Creates an instance of ProductService.
     * @memberof ProductService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductService')) {
            return ___ibz___.sc.get('ProductService');
        }
        ___ibz___.sc.set('ProductService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductService}
     * @memberof ProductService
     */
    static getInstance(): ProductService {
        if (!___ibz___.sc.has('ProductService')) {
            new ProductService();
        }
        return ___ibz___.sc.get('ProductService');
    }
}
export default ProductService;
