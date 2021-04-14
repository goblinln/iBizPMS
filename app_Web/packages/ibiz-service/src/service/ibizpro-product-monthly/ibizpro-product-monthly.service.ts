import { IbizproProductMonthlyBaseService } from './ibizpro-product-monthly-base.service';

/**
 * 产品月报服务
 *
 * @export
 * @class IbizproProductMonthlyService
 * @extends {IbizproProductMonthlyBaseService}
 */
export class IbizproProductMonthlyService extends IbizproProductMonthlyBaseService {
    /**
     * Creates an instance of IbizproProductMonthlyService.
     * @memberof IbizproProductMonthlyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbizproProductMonthlyService')) {
            return ___ibz___.sc.get('IbizproProductMonthlyService');
        }
        ___ibz___.sc.set('IbizproProductMonthlyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbizproProductMonthlyService}
     * @memberof IbizproProductMonthlyService
     */
    static getInstance(): IbizproProductMonthlyService {
        if (!___ibz___.sc.has('IbizproProductMonthlyService')) {
            new IbizproProductMonthlyService();
        }
        return ___ibz___.sc.get('IbizproProductMonthlyService');
    }
}
export default IbizproProductMonthlyService;