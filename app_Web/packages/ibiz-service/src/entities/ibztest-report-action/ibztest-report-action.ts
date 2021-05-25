import { IBZTestReportActionBase } from './ibztest-report-action-base';

/**
 * 报告日志
 *
 * @export
 * @class IBZTestReportAction
 * @extends {IBZTestReportActionBase}
 * @implements {IIBZTestReportAction}
 */
export class IBZTestReportAction extends IBZTestReportActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZTestReportAction
     */
    clone(): IBZTestReportAction {
        return new IBZTestReportAction(this);
    }
}
export default IBZTestReportAction;
