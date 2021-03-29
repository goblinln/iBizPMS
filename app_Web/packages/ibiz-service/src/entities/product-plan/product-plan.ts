import { ProductPlanBase } from './product-plan-base';

/**
 * 产品计划
 *
 * @export
 * @class ProductPlan
 * @extends {ProductPlanBase}
 * @implements {IProductPlan}
 */
export class ProductPlan extends ProductPlanBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductPlan
     */
    clone(): ProductPlan {
        return new ProductPlan(this);
    }
}
export default ProductPlan;
