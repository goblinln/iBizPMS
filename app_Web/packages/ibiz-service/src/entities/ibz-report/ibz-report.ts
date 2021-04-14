import { IbzReportBase } from './ibz-report-base';

/**
 * 汇报汇总
 *
 * @export
 * @class IbzReport
 * @extends {IbzReportBase}
 * @implements {IIbzReport}
 */
export class IbzReport extends IbzReportBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzReport
     */
    clone(): IbzReport {
        return new IbzReport(this);
    }
}
export default IbzReport;
