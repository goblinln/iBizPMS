import { WeeklyBaseService } from './weekly-base.service';

/**
 * 周报服务
 *
 * @export
 * @class WeeklyService
 * @extends {WeeklyBaseService}
 */
export class WeeklyService extends WeeklyBaseService {
    /**
     * Creates an instance of WeeklyService.
     * @memberof WeeklyService
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
     * @return {*}  {WeeklyService}
     * @memberof WeeklyService
     */
    static getInstance(context?: any): WeeklyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}WeeklyService` : `WeeklyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new WeeklyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default WeeklyService;
