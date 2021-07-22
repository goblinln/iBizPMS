import { WeeklyBaseService } from './weekly-base.service';

/**
 * 周报服务
 *
 * @export
 * @class WeeklyService
 * @extends {WeeklyBaseService}
 */
export class WeeklyService extends WeeklyBaseService {
    /**
     * Creates an instance of WeeklyService.
     * @memberof WeeklyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('WeeklyService')) {
            return ___ibz___.sc.get('WeeklyService');
        }
        ___ibz___.sc.set('WeeklyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {WeeklyService}
     * @memberof WeeklyService
     */
    static getInstance(): WeeklyService {
        if (!___ibz___.sc.has('WeeklyService')) {
            new WeeklyService();
        }
        return ___ibz___.sc.get('WeeklyService');
    }
}
export default WeeklyService;
