import { IbizproProductDailyBase } from './ibizpro-product-daily-base';

/**
 * 产品日报
 *
 * @export
 * @class IbizproProductDaily
 * @extends {IbizproProductDailyBase}
 * @implements {IIbizproProductDaily}
 */
export class IbizproProductDaily extends IbizproProductDailyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbizproProductDaily
     */
    clone(): IbizproProductDaily {
        return new IbizproProductDaily(this);
    }
}
export default IbizproProductDaily;
