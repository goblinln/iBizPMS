import { IbzDailyBase } from './ibz-daily-base';

/**
 * 日报
 *
 * @export
 * @class IbzDaily
 * @extends {IbzDailyBase}
 * @implements {IIbzDaily}
 */
export class IbzDaily extends IbzDailyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzDaily
     */
    clone(): IbzDaily {
        return new IbzDaily(this);
    }
}
export default IbzDaily;
