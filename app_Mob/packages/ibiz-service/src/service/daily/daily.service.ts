import { DailyBaseService } from './daily-base.service';

/**
 * 日报服务
 *
 * @export
 * @class DailyService
 * @extends {DailyBaseService}
 */
export class DailyService extends DailyBaseService {
    /**
     * Creates an instance of DailyService.
     * @memberof DailyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('DailyService')) {
            return ___ibz___.sc.get('DailyService');
        }
        ___ibz___.sc.set('DailyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {DailyService}
     * @memberof DailyService
     */
    static getInstance(): DailyService {
        if (!___ibz___.sc.has('DailyService')) {
            new DailyService();
        }
        return ___ibz___.sc.get('DailyService');
    }
}
export default DailyService;
