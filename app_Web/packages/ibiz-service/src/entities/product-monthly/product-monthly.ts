import { ProductMonthlyBase } from './product-monthly-base';

/**
 * 产品月报
 *
 * @export
 * @class ProductMonthly
 * @extends {ProductMonthlyBase}
 * @implements {IProductMonthly}
 */
export class ProductMonthly extends ProductMonthlyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductMonthly
     */
    clone(): ProductMonthly {
        return new ProductMonthly(this);
    }
}
export default ProductMonthly;
