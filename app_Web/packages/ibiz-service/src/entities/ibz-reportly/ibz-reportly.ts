import { IbzReportlyBase } from './ibz-reportly-base';

/**
 * 汇报
 *
 * @export
 * @class IbzReportly
 * @extends {IbzReportlyBase}
 * @implements {IIbzReportly}
 */
export class IbzReportly extends IbzReportlyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzReportly
     */
    clone(): IbzReportly {
        return new IbzReportly(this);
    }
}
export default IbzReportly;
