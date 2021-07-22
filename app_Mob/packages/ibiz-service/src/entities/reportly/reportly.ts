import { ReportlyBase } from './reportly-base';

/**
 * 汇报
 *
 * @export
 * @class Reportly
 * @extends {ReportlyBase}
 * @implements {IReportly}
 */
export class Reportly extends ReportlyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Reportly
     */
    clone(): Reportly {
        return new Reportly(this);
    }
}
export default Reportly;
