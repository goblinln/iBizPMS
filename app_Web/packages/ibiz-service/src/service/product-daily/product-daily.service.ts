import { ProductDailyBaseService } from './product-daily-base.service';

/**
 * 产品日报服务
 *
 * @export
 * @class ProductDailyService
 * @extends {ProductDailyBaseService}
 */
export class ProductDailyService extends ProductDailyBaseService {
    /**
     * Creates an instance of ProductDailyService.
     * @memberof ProductDailyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductDailyService')) {
            return ___ibz___.sc.get('ProductDailyService');
        }
        ___ibz___.sc.set('ProductDailyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductDailyService}
     * @memberof ProductDailyService
     */
    static getInstance(): ProductDailyService {
        if (!___ibz___.sc.has('ProductDailyService')) {
            new ProductDailyService();
        }
        return ___ibz___.sc.get('ProductDailyService');
    }
}
export default ProductDailyService;
