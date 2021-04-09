import { ProductLifeBaseService } from './product-life-base.service';

/**
 * 产品生命周期服务
 *
 * @export
 * @class ProductLifeService
 * @extends {ProductLifeBaseService}
 */
export class ProductLifeService extends ProductLifeBaseService {
    /**
     * Creates an instance of ProductLifeService.
     * @memberof ProductLifeService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductLifeService')) {
            return ___ibz___.sc.get('ProductLifeService');
        }
        ___ibz___.sc.set('ProductLifeService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductLifeService}
     * @memberof ProductLifeService
     */
    static getInstance(): ProductLifeService {
        if (!___ibz___.sc.has('ProductLifeService')) {
            new ProductLifeService();
        }
        return ___ibz___.sc.get('ProductLifeService');
    }
}
export default ProductLifeService;