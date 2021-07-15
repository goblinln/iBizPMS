import { IbzPlanTempletBaseService } from './ibz-plan-templet-base.service';

/**
 * 计划模板服务
 *
 * @export
 * @class IbzPlanTempletService
 * @extends {IbzPlanTempletBaseService}
 */
export class IbzPlanTempletService extends IbzPlanTempletBaseService {
    /**
     * Creates an instance of IbzPlanTempletService.
     * @memberof IbzPlanTempletService
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
     * @return {*}  {IbzPlanTempletService}
     * @memberof IbzPlanTempletService
     */
    static getInstance(context?: any): IbzPlanTempletService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzPlanTempletService` : `IbzPlanTempletService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzPlanTempletService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzPlanTempletService;
