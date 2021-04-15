import { ProductStatsBase } from './product-stats-base';

/**
 * 产品统计
 *
 * @export
 * @class ProductStats
 * @extends {ProductStatsBase}
 * @implements {IProductStats}
 */
export class ProductStats extends ProductStatsBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductStats
     */
    clone(): ProductStats {
        return new ProductStats(this);
    }
}
export default ProductStats;
