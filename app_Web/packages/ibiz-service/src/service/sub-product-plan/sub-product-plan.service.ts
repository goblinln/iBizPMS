import { SubProductPlanBaseService } from './sub-product-plan-base.service';

/**
 * 产品计划服务
 *
 * @export
 * @class SubProductPlanService
 * @extends {SubProductPlanBaseService}
 */
export class SubProductPlanService extends SubProductPlanBaseService {
    /**
     * Creates an instance of SubProductPlanService.
     * @memberof SubProductPlanService
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
     * @return {*}  {SubProductPlanService}
     * @memberof SubProductPlanService
     */
    static getInstance(context?: any): SubProductPlanService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SubProductPlanService` : `SubProductPlanService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SubProductPlanService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
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
export default SubProductPlanService;
