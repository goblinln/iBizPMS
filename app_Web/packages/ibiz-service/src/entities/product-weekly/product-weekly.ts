import { ProductWeeklyBase } from './product-weekly-base';

/**
 * 产品周报
 *
 * @export
 * @class ProductWeekly
 * @extends {ProductWeeklyBase}
 * @implements {IProductWeekly}
 */
export class ProductWeekly extends ProductWeeklyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductWeekly
     */
    clone(): ProductWeekly {
        return new ProductWeekly(this);
    }
}
export default ProductWeekly;
