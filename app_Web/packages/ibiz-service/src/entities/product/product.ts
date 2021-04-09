import { ProductBase } from './product-base';

/**
 * 产品
 *
 * @export
 * @class Product
 * @extends {ProductBase}
 * @implements {IProduct}
 */
export class Product extends ProductBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Product
     */
    clone(): Product {
        return new Product(this);
    }
}
export default Product;
