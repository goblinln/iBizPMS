import { MonthlyBase } from './monthly-base';

/**
 * 月报
 *
 * @export
 * @class Monthly
 * @extends {MonthlyBase}
 * @implements {IMonthly}
 */
export class Monthly extends MonthlyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Monthly
     */
    clone(): Monthly {
        return new Monthly(this);
    }
}
export default Monthly;
