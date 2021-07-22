import { ReportSummaryBase } from './report-summary-base';

/**
 * 汇报汇总
 *
 * @export
 * @class ReportSummary
 * @extends {ReportSummaryBase}
 * @implements {IReportSummary}
 */
export class ReportSummary extends ReportSummaryBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ReportSummary
     */
    clone(): ReportSummary {
        return new ReportSummary(this);
    }
}
export default ReportSummary;
