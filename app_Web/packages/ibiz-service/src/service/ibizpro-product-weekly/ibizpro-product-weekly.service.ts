import { IbizproProductWeeklyBaseService } from './ibizpro-product-weekly-base.service';

/**
 * 产品周报服务
 *
 * @export
 * @class IbizproProductWeeklyService
 * @extends {IbizproProductWeeklyBaseService}
 */
export class IbizproProductWeeklyService extends IbizproProductWeeklyBaseService {
    /**
     * Creates an instance of IbizproProductWeeklyService.
     * @memberof IbizproProductWeeklyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbizproProductWeeklyService')) {
            return ___ibz___.sc.get('IbizproProductWeeklyService');
        }
        ___ibz___.sc.set('IbizproProductWeeklyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbizproProductWeeklyService}
     * @memberof IbizproProductWeeklyService
     */
    static getInstance(): IbizproProductWeeklyService {
        if (!___ibz___.sc.has('IbizproProductWeeklyService')) {
            new IbizproProductWeeklyService();
        }
        return ___ibz___.sc.get('IbizproProductWeeklyService');
    }
}
export default IbizproProductWeeklyService;
