import { CompanyStatsBaseService } from './company-stats-base.service';

/**
 * 公司动态汇总服务
 *
 * @export
 * @class CompanyStatsService
 * @extends {CompanyStatsBaseService}
 */
export class CompanyStatsService extends CompanyStatsBaseService {
    /**
     * Creates an instance of CompanyStatsService.
     * @memberof CompanyStatsService
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
     * @return {*}  {CompanyStatsService}
     * @memberof CompanyStatsService
     */
    static getInstance(context?: any): CompanyStatsService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}CompanyStatsService` : `CompanyStatsService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new CompanyStatsService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default CompanyStatsService;
