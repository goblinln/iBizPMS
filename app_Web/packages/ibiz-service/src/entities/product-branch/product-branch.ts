import { ProductBranchBase } from './product-branch-base';

/**
 * 产品的分支和平台信息
 *
 * @export
 * @class ProductBranch
 * @extends {ProductBranchBase}
 * @implements {IProductBranch}
 */
export class ProductBranch extends ProductBranchBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductBranch
     */
    clone(): ProductBranch {
        return new ProductBranch(this);
    }
}
export default ProductBranch;
