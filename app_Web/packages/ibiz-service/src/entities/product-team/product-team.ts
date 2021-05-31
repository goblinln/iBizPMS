import { ProductTeamBase } from './product-team-base';

/**
 * 产品团队
 *
 * @export
 * @class ProductTeam
 * @extends {ProductTeamBase}
 * @implements {IProductTeam}
 */
export class ProductTeam extends ProductTeamBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductTeam
     */
    clone(): ProductTeam {
        return new ProductTeam(this);
    }
}
export default ProductTeam;
