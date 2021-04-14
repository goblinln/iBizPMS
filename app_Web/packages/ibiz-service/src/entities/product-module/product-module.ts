import { ProductModuleBase } from './product-module-base';

/**
 * 需求模块
 *
 * @export
 * @class ProductModule
 * @extends {ProductModuleBase}
 * @implements {IProductModule}
 */
export class ProductModule extends ProductModuleBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductModule
     */
    clone(): ProductModule {
        return new ProductModule(this);
    }
}
export default ProductModule;
