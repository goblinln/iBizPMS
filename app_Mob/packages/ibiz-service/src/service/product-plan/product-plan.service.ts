import { ProductPlanBaseService } from './product-plan-base.service';

/**
 * 产品计划服务
 *
 * @export
 * @class ProductPlanService
 * @extends {ProductPlanBaseService}
 */
export class ProductPlanService extends ProductPlanBaseService {
    /**
     * Creates an instance of ProductPlanService.
     * @memberof ProductPlanService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductPlanService')) {
            return ___ibz___.sc.get('ProductPlanService');
        }
        ___ibz___.sc.set('ProductPlanService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductPlanService}
     * @memberof ProductPlanService
     */
    static getInstance(): ProductPlanService {
        if (!___ibz___.sc.has('ProductPlanService')) {
            new ProductPlanService();
        }
        return ___ibz___.sc.get('ProductPlanService');
    }
}
export default ProductPlanService;
