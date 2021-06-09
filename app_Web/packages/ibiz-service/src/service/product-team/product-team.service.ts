import { Http } from 'ibiz-core';
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

    /**
     * saveBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectTeamService
     */
    public async saveBatch(context: any = {}, data: any, isloading?: boolean): Promise<any> {
        if (context['product']) {
            return Http.getInstance().post(`/products/${context['product']}/${this.APPDENAMEPLURAL.toLowerCase()}/savebatch`, data, isloading);
        }
    }

}
export default ProductTeamService;
