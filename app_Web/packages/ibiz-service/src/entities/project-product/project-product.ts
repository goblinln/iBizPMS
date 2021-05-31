import { ProjectProductBase } from './project-product-base';

/**
 * 项目产品
 *
 * @export
 * @class ProjectProduct
 * @extends {ProjectProductBase}
 * @implements {IProjectProduct}
 */
export class ProjectProduct extends ProjectProductBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectProduct
     */
    clone(): ProjectProduct {
        return new ProjectProduct(this);
    }
}
export default ProjectProduct;
