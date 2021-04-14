import { ProductStatsBaseService } from './product-stats-base.service';

/**
 * 产品统计服务
 *
 * @export
 * @class ProductStatsService
 * @extends {ProductStatsBaseService}
 */
export class ProductStatsService extends ProductStatsBaseService {
    /**
     * Creates an instance of ProductStatsService.
     * @memberof ProductStatsService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductStatsService')) {
            return ___ibz___.sc.get('ProductStatsService');
        }
        ___ibz___.sc.set('ProductStatsService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductStatsService}
     * @memberof ProductStatsService
     */
    static getInstance(): ProductStatsService {
        if (!___ibz___.sc.has('ProductStatsService')) {
            new ProductStatsService();
        }
        return ___ibz___.sc.get('ProductStatsService');
    }
}
export default ProductStatsService;