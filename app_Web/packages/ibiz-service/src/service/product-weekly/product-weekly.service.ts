import { ProductWeeklyBaseService } from './product-weekly-base.service';

/**
 * 产品周报服务
 *
 * @export
 * @class ProductWeeklyService
 * @extends {ProductWeeklyBaseService}
 */
export class ProductWeeklyService extends ProductWeeklyBaseService {
    /**
     * Creates an instance of ProductWeeklyService.
     * @memberof ProductWeeklyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductWeeklyService')) {
            return ___ibz___.sc.get('ProductWeeklyService');
        }
        ___ibz___.sc.set('ProductWeeklyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductWeeklyService}
     * @memberof ProductWeeklyService
     */
    static getInstance(): ProductWeeklyService {
        if (!___ibz___.sc.has('ProductWeeklyService')) {
            new ProductWeeklyService();
        }
        return ___ibz___.sc.get('ProductWeeklyService');
    }
}
export default ProductWeeklyService;
