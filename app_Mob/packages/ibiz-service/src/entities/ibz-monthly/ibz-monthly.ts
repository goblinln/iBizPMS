import { IbzMonthlyBase } from './ibz-monthly-base';

/**
 * 月报
 *
 * @export
 * @class IbzMonthly
 * @extends {IbzMonthlyBase}
 * @implements {IIbzMonthly}
 */
export class IbzMonthly extends IbzMonthlyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzMonthly
     */
    clone(): IbzMonthly {
        return new IbzMonthly(this);
    }
}
export default IbzMonthly;
