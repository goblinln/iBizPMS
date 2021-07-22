import { DailyBaseService } from './daily-base.service';

/**
 * 日报服务
 *
 * @export
 * @class DailyService
 * @extends {DailyBaseService}
 */
export class DailyService extends DailyBaseService {
    /**
     * Creates an instance of DailyService.
     * @memberof DailyService
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
     * @return {*}  {DailyService}
     * @memberof DailyService
     */
    static getInstance(context?: any): DailyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}DailyService` : `DailyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new DailyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default DailyService;
