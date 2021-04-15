import { ProductLifeBase } from './product-life-base';

/**
 * 产品生命周期
 *
 * @export
 * @class ProductLife
 * @extends {ProductLifeBase}
 * @implements {IProductLife}
 */
export class ProductLife extends ProductLifeBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductLife
     */
    clone(): ProductLife {
        return new ProductLife(this);
    }
}
export default ProductLife;
