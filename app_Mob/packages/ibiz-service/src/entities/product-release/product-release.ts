import { ProductReleaseBase } from './product-release-base';

/**
 * 发布
 *
 * @export
 * @class ProductRelease
 * @extends {ProductReleaseBase}
 * @implements {IProductRelease}
 */
export class ProductRelease extends ProductReleaseBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductRelease
     */
    clone(): ProductRelease {
        return new ProductRelease(this);
    }
}
export default ProductRelease;
