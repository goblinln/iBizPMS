import { ProductBugBase } from './product-bug-base';

/**
 * Bug
 *
 * @export
 * @class ProductBug
 * @extends {ProductBugBase}
 * @implements {IProductBug}
 */
export class ProductBug extends ProductBugBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductBug
     */
    clone(): ProductBug {
        return new ProductBug(this);
    }
}
export default ProductBug;
