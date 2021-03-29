import { IBZProProductBase } from './ibzpro-product-base';

/**
 * 平台产品
 *
 * @export
 * @class IBZProProduct
 * @extends {IBZProProductBase}
 * @implements {IIBZProProduct}
 */
export class IBZProProduct extends IBZProProductBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZProProduct
     */
    clone(): IBZProProduct {
        return new IBZProProduct(this);
    }
}
export default IBZProProduct;
