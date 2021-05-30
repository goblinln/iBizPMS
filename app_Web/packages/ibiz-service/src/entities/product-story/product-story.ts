import { ProductStoryBase } from './product-story-base';

/**
 * 需求
 *
 * @export
 * @class ProductStory
 * @extends {ProductStoryBase}
 * @implements {IProductStory}
 */
export class ProductStory extends ProductStoryBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProductStory
     */
    clone(): ProductStory {
        return new ProductStory(this);
    }
}
export default ProductStory;
