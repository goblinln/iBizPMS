import { ProductProjectBase } from './product-project-base';

/**
 * 项目产品
 *
 * @export
 * @class ProductProject
 * @extends {ProductProjectBase}
 * @implements {IProductProject}
 */
export class ProductProject extends ProductProjectBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductProject
     */
    clone(): ProductProject {
        return new ProductProject(this);
    }
}
export default ProductProject;
