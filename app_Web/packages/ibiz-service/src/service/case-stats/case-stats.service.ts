import { CaseStatsBaseService } from './case-stats-base.service';

/**
 * 测试用例统计服务
 *
 * @export
 * @class CaseStatsService
 * @extends {CaseStatsBaseService}
 */
export class CaseStatsService extends CaseStatsBaseService {
    /**
     * Creates an instance of CaseStatsService.
     * @memberof CaseStatsService
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
     * @return {*}  {CaseStatsService}
     * @memberof CaseStatsService
     */
    static getInstance(context?: any): CaseStatsService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}CaseStatsService` : `CaseStatsService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new CaseStatsService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default CaseStatsService;
