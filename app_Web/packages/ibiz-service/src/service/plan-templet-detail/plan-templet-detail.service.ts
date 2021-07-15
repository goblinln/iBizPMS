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
     * @param 应用上下文
     * @return {*}  {PlanTempletDetailService}
     * @memberof PlanTempletDetailService
     */
    static getInstance(context?: any): PlanTempletDetailService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}PlanTempletDetailService` : `PlanTempletDetailService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new PlanTempletDetailService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default PlanTempletDetailService;
