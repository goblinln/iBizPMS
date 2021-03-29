import { Http, IHttpResponse } from 'ibiz-core';
import { ProductBaseService } from './product-base.service';

/**
 * 实体服务对象基类
 *
 * @export
 * @class ProductService
 * @extends {ProductBaseService}
 */
export class ProductService extends ProductBaseService {
    /**
     * Creates an instance of ProductService.
     * @memberof ProductService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductService')) {
            return ___ibz___.sc.get('ProductService');
        }
        ___ibz___.sc.set('ProductService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductService}
     * @memberof ProductService
     */
    static getInstance(): ProductService {
        if (!___ibz___.sc.has('ProductService')) {
            new ProductService();
        }
        return ___ibz___.sc.get('ProductService');
    }

    /**
     * CancelProductTop
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<IHttpResponse>}
     * @memberof ProductService
     */
    async CancelProductTop(_context: any = {}, _data: any = {}): Promise<IHttpResponse> {
        let res:any = Http.getInstance().post(`/products/${_context.product}/cancelproducttop`,_data);
        return res;
    }

    /**
     * ProductTop
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<IHttpResponse>}
     * @memberof ProductService
     */
    async ProductTop(_context: any = {}, _data: any = {}): Promise<IHttpResponse> {
        let res:any = Http.getInstance().post(`/products/${_context.product}/producttop`,_data);
        return res;
    }

}
export default ProductService;
