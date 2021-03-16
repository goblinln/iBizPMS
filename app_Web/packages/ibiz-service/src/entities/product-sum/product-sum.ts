import { ProductSumBase } from './product-sum-base';

/**
 * 产品汇总表
 *
 * @export
 * @class ProductSum
 * @extends {ProductSumBase}
 * @implements {IProductSum}
 */
export class ProductSum extends ProductSumBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductSum
     */
    clone(): ProductSum {
        return new ProductSum(this);
    }
}
export default ProductSum;
