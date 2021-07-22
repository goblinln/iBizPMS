import { ReportSummaryBaseService } from './report-summary-base.service';

/**
 * 汇报汇总服务
 *
 * @export
 * @class ReportSummaryService
 * @extends {ReportSummaryBaseService}
 */
export class ReportSummaryService extends ReportSummaryBaseService {
    /**
     * Creates an instance of ReportSummaryService.
     * @memberof ReportSummaryService
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
     * @return {*}  {ReportSummaryService}
     * @memberof ReportSummaryService
     */
    static getInstance(context?: any): ReportSummaryService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ReportSummaryService` : `ReportSummaryService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ReportSummaryService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ReportSummaryService;
