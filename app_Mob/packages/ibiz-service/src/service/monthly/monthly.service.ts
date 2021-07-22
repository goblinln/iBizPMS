import { MonthlyBaseService } from './monthly-base.service';

/**
 * 月报服务
 *
 * @export
 * @class MonthlyService
 * @extends {MonthlyBaseService}
 */
export class MonthlyService extends MonthlyBaseService {
    /**
     * Creates an instance of MonthlyService.
     * @memberof MonthlyService
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
     * @return {*}  {MonthlyService}
     * @memberof MonthlyService
     */
    static getInstance(context?: any): MonthlyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}MonthlyService` : `MonthlyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new MonthlyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default MonthlyService;
