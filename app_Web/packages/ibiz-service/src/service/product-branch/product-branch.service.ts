import { ProductBranchBaseService } from './product-branch-base.service';

/**
 * 产品的分支和平台信息服务
 *
 * @export
 * @class ProductBranchService
 * @extends {ProductBranchBaseService}
 */
export class ProductBranchService extends ProductBranchBaseService {
    /**
     * Creates an instance of ProductBranchService.
     * @memberof ProductBranchService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductBranchService')) {
            return ___ibz___.sc.get('ProductBranchService');
        }
        ___ibz___.sc.set('ProductBranchService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductBranchService}
     * @memberof ProductBranchService
     */
    static getInstance(): ProductBranchService {
        if (!___ibz___.sc.has('ProductBranchService')) {
            new ProductBranchService();
        }
        return ___ibz___.sc.get('ProductBranchService');
    }
}
export default ProductBranchService;
