import { ProductSumBaseService } from './product-sum-base.service';

/**
 * 产品汇总表服务
 *
 * @export
 * @class ProductSumService
 * @extends {ProductSumBaseService}
 */
export class ProductSumService extends ProductSumBaseService {
    /**
     * Creates an instance of ProductSumService.
     * @memberof ProductSumService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductSumService')) {
            return ___ibz___.sc.get('ProductSumService');
        }
        ___ibz___.sc.set('ProductSumService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductSumService}
     * @memberof ProductSumService
     */
    static getInstance(): ProductSumService {
        if (!___ibz___.sc.has('ProductSumService')) {
            new ProductSumService();
        }
        return ___ibz___.sc.get('ProductSumService');
    }
}
export default ProductSumService;