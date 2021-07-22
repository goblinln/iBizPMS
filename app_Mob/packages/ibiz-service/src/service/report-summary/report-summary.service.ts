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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ReportSummaryService')) {
            return ___ibz___.sc.get('ReportSummaryService');
        }
        ___ibz___.sc.set('ReportSummaryService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ReportSummaryService}
     * @memberof ReportSummaryService
     */
    static getInstance(): ReportSummaryService {
        if (!___ibz___.sc.has('ReportSummaryService')) {
            new ReportSummaryService();
        }
        return ___ibz___.sc.get('ReportSummaryService');
    }
}
export default ReportSummaryService;
