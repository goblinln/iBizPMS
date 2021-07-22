import { DailyBase } from './daily-base';

/**
 * 日报
 *
 * @export
 * @class Daily
 * @extends {DailyBase}
 * @implements {IDaily}
 */
export class Daily extends DailyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Daily
     */
    clone(): Daily {
        return new Daily(this);
    }
}
export default Daily;
