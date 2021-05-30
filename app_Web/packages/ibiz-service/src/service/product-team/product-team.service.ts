import { ProductTeamBaseService } from './product-team-base.service';

/**
 * 产品团队服务
 *
 * @export
 * @class ProductTeamService
 * @extends {ProductTeamBaseService}
 */
export class ProductTeamService extends ProductTeamBaseService {
    /**
     * Creates an instance of ProductTeamService.
     * @memberof ProductTeamService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductTeamService')) {
            return ___ibz___.sc.get('ProductTeamService');
        }
        ___ibz___.sc.set('ProductTeamService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductTeamService}
     * @memberof ProductTeamService
     */
    static getInstance(): ProductTeamService {
        if (!___ibz___.sc.has('ProductTeamService')) {
            new ProductTeamService();
        }
        return ___ibz___.sc.get('ProductTeamService');
    }
}
export default ProductTeamService;
