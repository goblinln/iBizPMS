import { AccountProductBase } from './account-product-base';

/**
 * 产品
 *
 * @export
 * @class AccountProduct
 * @extends {AccountProductBase}
 * @implements {IAccountProduct}
 */
export class AccountProduct extends AccountProductBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof AccountProduct
     */
    clone(): AccountProduct {
        return new AccountProduct(this);
    }
}
export default AccountProduct;
