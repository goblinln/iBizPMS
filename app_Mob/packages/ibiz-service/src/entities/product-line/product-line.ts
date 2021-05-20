import { ProductLineBase } from './product-line-base';

/**
 * 产品线（废弃）
 *
 * @export
 * @class ProductLine
 * @extends {ProductLineBase}
 * @implements {IProductLine}
 */
export class ProductLine extends ProductLineBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductLine
     */
    clone(): ProductLine {
        return new ProductLine(this);
    }
}
export default ProductLine;
