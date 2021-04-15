import { IbizproProductMonthlyBase } from './ibizpro-product-monthly-base';

/**
 * 产品月报
 *
 * @export
 * @class IbizproProductMonthly
 * @extends {IbizproProductMonthlyBase}
 * @implements {IIbizproProductMonthly}
 */
export class IbizproProductMonthly extends IbizproProductMonthlyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbizproProductMonthly
     */
    clone(): IbizproProductMonthly {
        return new IbizproProductMonthly(this);
    }
}
export default IbizproProductMonthly;
