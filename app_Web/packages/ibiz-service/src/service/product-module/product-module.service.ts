import { ProductModuleBaseService } from './product-module-base.service';

/**
 * 需求模块服务
 *
 * @export
 * @class ProductModuleService
 * @extends {ProductModuleBaseService}
 */
export class ProductModuleService extends ProductModuleBaseService {
    /**
     * Creates an instance of ProductModuleService.
     * @memberof ProductModuleService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductModuleService')) {
            return ___ibz___.sc.get('ProductModuleService');
        }
        ___ibz___.sc.set('ProductModuleService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductModuleService}
     * @memberof ProductModuleService
     */
    static getInstance(): ProductModuleService {
        if (!___ibz___.sc.has('ProductModuleService')) {
            new ProductModuleService();
        }
        return ___ibz___.sc.get('ProductModuleService');
    }
}
export default ProductModuleService;