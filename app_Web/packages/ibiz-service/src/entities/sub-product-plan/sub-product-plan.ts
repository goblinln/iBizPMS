import { SubProductPlanBase } from './sub-product-plan-base';

/**
 * 产品计划
 *
 * @export
 * @class SubProductPlan
 * @extends {SubProductPlanBase}
 * @implements {ISubProductPlan}
 */
export class SubProductPlan extends SubProductPlanBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SubProductPlan
     */
    clone(): SubProductPlan {
        return new SubProductPlan(this);
    }
}
export default SubProductPlan;
