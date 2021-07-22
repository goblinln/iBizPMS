import { MonthlyBaseService } from './monthly-base.service';

/**
 * 月报服务
 *
 * @export
 * @class MonthlyService
 * @extends {MonthlyBaseService}
 */
export class MonthlyService extends MonthlyBaseService {
    /**
     * Creates an instance of MonthlyService.
     * @memberof MonthlyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('MonthlyService')) {
            return ___ibz___.sc.get('MonthlyService');
        }
        ___ibz___.sc.set('MonthlyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {MonthlyService}
     * @memberof MonthlyService
     */
    static getInstance(): MonthlyService {
        if (!___ibz___.sc.has('MonthlyService')) {
            new MonthlyService();
        }
        return ___ibz___.sc.get('MonthlyService');
    }
}
export default MonthlyService;
