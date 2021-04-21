import { IbizproProjectMonthlyBase } from './ibizpro-project-monthly-base';

/**
 * 项目月报
 *
 * @export
 * @class IbizproProjectMonthly
 * @extends {IbizproProjectMonthlyBase}
 * @implements {IIbizproProjectMonthly}
 */
export class IbizproProjectMonthly extends IbizproProjectMonthlyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbizproProjectMonthly
     */
    clone(): IbizproProjectMonthly {
        return new IbizproProjectMonthly(this);
    }
}
export default IbizproProjectMonthly;
