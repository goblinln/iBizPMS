import { ProductLineBaseService } from './product-line-base.service';

/**
 * 产品线（废弃）服务
 *
 * @export
 * @class ProductLineService
 * @extends {ProductLineBaseService}
 */
export class ProductLineService extends ProductLineBaseService {
    /**
     * Creates an instance of ProductLineService.
     * @memberof ProductLineService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductLineService')) {
            return ___ibz___.sc.get('ProductLineService');
        }
        ___ibz___.sc.set('ProductLineService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductLineService}
     * @memberof ProductLineService
     */
    static getInstance(): ProductLineService {
        if (!___ibz___.sc.has('ProductLineService')) {
            new ProductLineService();
        }
        return ___ibz___.sc.get('ProductLineService');
    }
}
export default ProductLineService;
