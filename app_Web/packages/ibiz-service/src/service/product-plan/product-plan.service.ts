import { Http } from 'ibiz-core';
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
     * @return {*}  {ProductPlanService}
     * @memberof ProductPlanService
     */
    static getInstance(context?: any): ProductPlanService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProductPlanService` : `ProductPlanService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProductPlanService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
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

}
export default ProductPlanService;
