import { IBZProWeeklyActionBaseService } from './ibzpro-weekly-action-base.service';

/**
 * 周报日志服务
 *
 * @export
 * @class IBZProWeeklyActionService
 * @extends {IBZProWeeklyActionBaseService}
 */
export class IBZProWeeklyActionService extends IBZProWeeklyActionBaseService {
    /**
     * Creates an instance of IBZProWeeklyActionService.
     * @memberof IBZProWeeklyActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZProWeeklyActionService')) {
            return ___ibz___.sc.get('IBZProWeeklyActionService');
        }
        ___ibz___.sc.set('IBZProWeeklyActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZProWeeklyActionService}
     * @memberof IBZProWeeklyActionService
     */
    static getInstance(): IBZProWeeklyActionService {
        if (!___ibz___.sc.has('IBZProWeeklyActionService')) {
            new IBZProWeeklyActionService();
        }
        return ___ibz___.sc.get('IBZProWeeklyActionService');
    }
}
export default IBZProWeeklyActionService;
