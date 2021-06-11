import { Http, HttpResponse } from 'ibiz-core';
import { ProductPlanBaseService } from './product-plan-base.service';

/**
 * 实体服务对象基类
 *
 * @export
 * @class ProductPlanService
 * @extends {ProductPlanBaseService}
 */
export class ProductPlanService extends ProductPlanBaseService {
    /**
     * Creates an instance of ProductPlanService.
     * @memberof ProductPlanService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProductPlanService')) {
            return ___ibz___.sc.get('ProductPlanService');
        }
        ___ibz___.sc.set('ProductPlanService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProductPlanService}
     * @memberof ProductPlanService
     */
    static getInstance(): ProductPlanService {
        if (!___ibz___.sc.has('ProductPlanService')) {
            new ProductPlanService();
        }
        return ___ibz___.sc.get('ProductPlanService');
    }

    /**
     * ImportPlanTemplet接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async ImportPlanTemplet(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product){
            context.productplan=0;
            data.id=0;
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/importplantemplet`,data,isloading);

            return res;
        }
        context.productplan=0;
        data.id=0;
        let res:any = Http.getInstance().post(`/productplans/${context.productplan}/importplantemplet`,data,isloading);
        return res;
    }

    /**
     * FetchDefaultParent
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<IHttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchDefaultParent(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/products/${context.product}/productplans/fetchdefaultparent`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().post(`/productplans/fetchdefaultparent`,tempData,isloading);
        return res;
    }

    /**
     * GetEnd接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async GetPlanEnd(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let res: any = { status: 200, data: {} };
        if(!(data && data.begin && data.delta)) {
            return res;
        }
        let begin: Date = new Date(data.begin);
        let period = parseInt(data.delta);

        begin.setDate(begin.getDate() + period - 1);

        let year = begin.getFullYear();
        let month = begin.getMonth() + 1;
        let day = begin.getDate();
        Object.assign(res.data, {
            end: `${year}-${month < 10 ? ('0' + month) : month}-${day < 10 ? ('0' + day) : day}`
        });
        return res;
    }

    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (Object.is(_context.productplan,"null")) {
          return this.GetDraft(_context, _data);
        }
        if (_context.product && _context.project && _context.productplan) {
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/productplans/${_context.productplan}`);
            return res;
        }
        if (_context.project && _context.productplan) {
            const res = await this.http.get(`/projects/${_context.project}/productplans/${_context.productplan}`);
            return res;
        }
        if (_context.product && _context.productplan) {
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}`);
            return res;
        }
    this.log.warn([`[ProductPlan]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }

}
export default ProductPlanService;
