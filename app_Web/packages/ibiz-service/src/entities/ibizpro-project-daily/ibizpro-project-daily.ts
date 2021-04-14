import { IbizproProjectDailyBase } from './ibizpro-project-daily-base';

/**
 * 项目日报
 *
 * @export
 * @class IbizproProjectDaily
 * @extends {IbizproProjectDailyBase}
 * @implements {IIbizproProjectDaily}
 */
export class IbizproProjectDaily extends IbizproProjectDailyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbizproProjectDaily
     */
    clone(): IbizproProjectDaily {
        return new IbizproProjectDaily(this);
    }
}
export default IbizproProjectDaily;
