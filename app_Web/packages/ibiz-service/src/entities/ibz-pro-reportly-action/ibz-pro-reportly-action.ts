import { IbzProReportlyActionBase } from './ibz-pro-reportly-action-base';

/**
 * 汇报日志
 *
 * @export
 * @class IbzProReportlyAction
 * @extends {IbzProReportlyActionBase}
 * @implements {IIbzProReportlyAction}
 */
export class IbzProReportlyAction extends IbzProReportlyActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzProReportlyAction
     */
    clone(): IbzProReportlyAction {
        return new IbzProReportlyAction(this);
    }
}
export default IbzProReportlyAction;
