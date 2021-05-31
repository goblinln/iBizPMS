import { ProductProjectBaseService } from './product-project-base.service';

/**
 * 项目服务
 *
 * @export
 * @class ProductProjectService
 * @extends {ProductProjectBaseService}
 */
export class ProductProjectService extends ProductProjectBaseService {
    /**
     * Creates an instance of ProductProjectService.
     * @memberof ProductProjectService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductProjectService')) {
            return ___ibz___.sc.get('ProductProjectService');
        }
        ___ibz___.sc.set('ProductProjectService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductProjectService}
     * @memberof ProductProjectService
     */
    static getInstance(): ProductProjectService {
        if (!___ibz___.sc.has('ProductProjectService')) {
            new ProductProjectService();
        }
        return ___ibz___.sc.get('ProductProjectService');
    }
}
export default ProductProjectService;
