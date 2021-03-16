import { IbizproProductDailyBaseService } from './ibizpro-product-daily-base.service';

/**
 * 产品日报服务
 *
 * @export
 * @class IbizproProductDailyService
 * @extends {IbizproProductDailyBaseService}
 */
export class IbizproProductDailyService extends IbizproProductDailyBaseService {
    /**
     * Creates an instance of IbizproProductDailyService.
     * @memberof IbizproProductDailyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbizproProductDailyService')) {
            return ___ibz___.sc.get('IbizproProductDailyService');
        }
        ___ibz___.sc.set('IbizproProductDailyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbizproProductDailyService}
     * @memberof IbizproProductDailyService
     */
    static getInstance(): IbizproProductDailyService {
        if (!___ibz___.sc.has('IbizproProductDailyService')) {
            new IbizproProductDailyService();
        }
        return ___ibz___.sc.get('IbizproProductDailyService');
    }
}
export default IbizproProductDailyService;
