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
}
export default SubProductPlanService;