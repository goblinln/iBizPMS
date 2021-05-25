import { ProductPlanActionBaseService } from './product-plan-action-base.service';

/**
 * 产品计划日志服务
 *
 * @export
 * @class ProductPlanActionService
 * @extends {ProductPlanActionBaseService}
 */
export class ProductPlanActionService extends ProductPlanActionBaseService {
    /**
     * Creates an instance of ProductPlanActionService.
     * @memberof ProductPlanActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductPlanActionService')) {
            return ___ibz___.sc.get('ProductPlanActionService');
        }
        ___ibz___.sc.set('ProductPlanActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductPlanActionService}
     * @memberof ProductPlanActionService
     */
    static getInstance(): ProductPlanActionService {
        if (!___ibz___.sc.has('ProductPlanActionService')) {
            new ProductPlanActionService();
        }
        return ___ibz___.sc.get('ProductPlanActionService');
    }
}
export default ProductPlanActionService;
