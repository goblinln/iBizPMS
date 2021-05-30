import { IbizproProjectWeeklyBase } from './ibizpro-project-weekly-base';

/**
 * 项目周报
 *
 * @export
 * @class IbizproProjectWeekly
 * @extends {IbizproProjectWeeklyBase}
 * @implements {IIbizproProjectWeekly}
 */
export class IbizproProjectWeekly extends IbizproProjectWeeklyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbizproProjectWeekly
     */
    clone(): IbizproProjectWeekly {
        return new IbizproProjectWeekly(this);
    }
}
export default IbizproProjectWeekly;
