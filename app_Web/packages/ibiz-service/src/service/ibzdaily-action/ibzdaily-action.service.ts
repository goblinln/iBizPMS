import { IBZDailyActionBaseService } from './ibzdaily-action-base.service';

/**
 * 日报日志服务
 *
 * @export
 * @class IBZDailyActionService
 * @extends {IBZDailyActionBaseService}
 */
export class IBZDailyActionService extends IBZDailyActionBaseService {
    /**
     * Creates an instance of IBZDailyActionService.
     * @memberof IBZDailyActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZDailyActionService')) {
            return ___ibz___.sc.get('IBZDailyActionService');
        }
        ___ibz___.sc.set('IBZDailyActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZDailyActionService}
     * @memberof IBZDailyActionService
     */
    static getInstance(): IBZDailyActionService {
        if (!___ibz___.sc.has('IBZDailyActionService')) {
            new IBZDailyActionService();
        }
        return ___ibz___.sc.get('IBZDailyActionService');
    }
}
export default IBZDailyActionService;
