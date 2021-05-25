import { ProductPlanActionBase } from './product-plan-action-base';

/**
 * 产品计划日志
 *
 * @export
 * @class ProductPlanAction
 * @extends {ProductPlanActionBase}
 * @implements {IProductPlanAction}
 */
export class ProductPlanAction extends ProductPlanActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductPlanAction
     */
    clone(): ProductPlanAction {
        return new ProductPlanAction(this);
    }
}
export default ProductPlanAction;
