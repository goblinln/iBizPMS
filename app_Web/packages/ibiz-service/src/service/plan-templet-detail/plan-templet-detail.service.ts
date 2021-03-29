import { PlanTempletDetailBaseService } from './plan-templet-detail-base.service';

/**
 * 计划模板详情服务
 *
 * @export
 * @class PlanTempletDetailService
 * @extends {PlanTempletDetailBaseService}
 */
export class PlanTempletDetailService extends PlanTempletDetailBaseService {
    /**
     * Creates an instance of PlanTempletDetailService.
     * @memberof PlanTempletDetailService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('PlanTempletDetailService')) {
            return ___ibz___.sc.get('PlanTempletDetailService');
        }
        ___ibz___.sc.set('PlanTempletDetailService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {PlanTempletDetailService}
     * @memberof PlanTempletDetailService
     */
    static getInstance(): PlanTempletDetailService {
        if (!___ibz___.sc.has('PlanTempletDetailService')) {
            new PlanTempletDetailService();
        }
        return ___ibz___.sc.get('PlanTempletDetailService');
    }
}
export default PlanTempletDetailService;