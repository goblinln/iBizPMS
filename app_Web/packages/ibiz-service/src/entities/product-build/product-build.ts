import { ProductBuildBase } from './product-build-base';

/**
 * 版本
 *
 * @export
 * @class ProductBuild
 * @extends {ProductBuildBase}
 * @implements {IProductBuild}
 */
export class ProductBuild extends ProductBuildBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductBuild
     */
    clone(): ProductBuild {
        return new ProductBuild(this);
    }
}
export default ProductBuild;
