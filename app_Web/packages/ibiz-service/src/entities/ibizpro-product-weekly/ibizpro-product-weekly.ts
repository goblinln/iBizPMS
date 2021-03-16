import { IbizproProductWeeklyBase } from './ibizpro-product-weekly-base';

/**
 * 产品周报
 *
 * @export
 * @class IbizproProductWeekly
 * @extends {IbizproProductWeeklyBase}
 * @implements {IIbizproProductWeekly}
 */
export class IbizproProductWeekly extends IbizproProductWeeklyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbizproProductWeekly
     */
    clone(): IbizproProductWeekly {
        return new IbizproProductWeekly(this);
    }
}
export default IbizproProductWeekly;
