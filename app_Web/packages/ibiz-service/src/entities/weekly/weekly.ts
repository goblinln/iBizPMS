import { WeeklyBase } from './weekly-base';

/**
 * 周报
 *
 * @export
 * @class Weekly
 * @extends {WeeklyBase}
 * @implements {IWeekly}
 */
export class Weekly extends WeeklyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Weekly
     */
    clone(): Weekly {
        return new Weekly(this);
    }
}
export default Weekly;
