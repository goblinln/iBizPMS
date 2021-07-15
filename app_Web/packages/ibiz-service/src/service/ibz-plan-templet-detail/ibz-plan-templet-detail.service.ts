import { IbzPlanTempletDetailBaseService } from './ibz-plan-templet-detail-base.service';

/**
 * 计划模板详情服务
 *
 * @export
 * @class IbzPlanTempletDetailService
 * @extends {IbzPlanTempletDetailBaseService}
 */
export class IbzPlanTempletDetailService extends IbzPlanTempletDetailBaseService {
    /**
     * Creates an instance of IbzPlanTempletDetailService.
     * @memberof IbzPlanTempletDetailService
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
     * @return {*}  {IbzPlanTempletDetailService}
     * @memberof IbzPlanTempletDetailService
     */
    static getInstance(context?: any): IbzPlanTempletDetailService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzPlanTempletDetailService` : `IbzPlanTempletDetailService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzPlanTempletDetailService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzPlanTempletDetailService;
