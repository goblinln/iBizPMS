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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SubProductPlanService')) {
            return ___ibz___.sc.get('SubProductPlanService');
        }
        ___ibz___.sc.set('SubProductPlanService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SubProductPlanService}
     * @memberof SubProductPlanService
     */
    static getInstance(): SubProductPlanService {
        if (!___ibz___.sc.has('SubProductPlanService')) {
            new SubProductPlanService();
        }
        return ___ibz___.sc.get('SubProductPlanService');
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
