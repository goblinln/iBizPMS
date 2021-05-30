import { ProductProjectBase } from './product-project-base';

/**
 * 项目
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
