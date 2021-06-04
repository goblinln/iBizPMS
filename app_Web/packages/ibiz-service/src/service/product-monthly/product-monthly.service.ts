import { ProductMonthlyBaseService } from './product-monthly-base.service';

/**
 * 产品月报服务
 *
 * @export
 * @class ProductMonthlyService
 * @extends {ProductMonthlyBaseService}
 */
export class ProductMonthlyService extends ProductMonthlyBaseService {
    /**
     * Creates an instance of ProductMonthlyService.
     * @memberof ProductMonthlyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductMonthlyService')) {
            return ___ibz___.sc.get('ProductMonthlyService');
        }
        ___ibz___.sc.set('ProductMonthlyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductMonthlyService}
     * @memberof ProductMonthlyService
     */
    static getInstance(): ProductMonthlyService {
        if (!___ibz___.sc.has('ProductMonthlyService')) {
            new ProductMonthlyService();
        }
        return ___ibz___.sc.get('ProductMonthlyService');
    }
}
export default ProductMonthlyService;
