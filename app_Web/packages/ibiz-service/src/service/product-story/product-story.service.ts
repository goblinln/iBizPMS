import { ProductStoryBaseService } from './product-story-base.service';

/**
 * 需求服务
 *
 * @export
 * @class ProductStoryService
 * @extends {ProductStoryBaseService}
 */
export class ProductStoryService extends ProductStoryBaseService {
    /**
     * Creates an instance of ProductStoryService.
     * @memberof ProductStoryService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductStoryService')) {
            return ___ibz___.sc.get('ProductStoryService');
        }
        ___ibz___.sc.set('ProductStoryService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductStoryService}
     * @memberof ProductStoryService
     */
    static getInstance(): ProductStoryService {
        if (!___ibz___.sc.has('ProductStoryService')) {
            new ProductStoryService();
        }
        return ___ibz___.sc.get('ProductStoryService');
    }
}
export default ProductStoryService;
