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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductTeamService}
     * @memberof ProductTeamService
     */
	static getInstance(context?: any): ProductTeamService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductTeamService` : `ProductTeamService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductTeamService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
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
