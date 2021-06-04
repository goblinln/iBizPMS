import { ProductDailyBase } from './product-daily-base';

/**
 * 产品日报
 *
 * @export
 * @class ProductDaily
 * @extends {ProductDailyBase}
 * @implements {IProductDaily}
 */
export class ProductDaily extends ProductDailyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductDaily
     */
    clone(): ProductDaily {
        return new ProductDaily(this);
    }
}
export default ProductDaily;
