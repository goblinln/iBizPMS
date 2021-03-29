import { IbzDailyBaseService } from './ibz-daily-base.service';

/**
 * 日报服务
 *
 * @export
 * @class IbzDailyService
 * @extends {IbzDailyBaseService}
 */
export class IbzDailyService extends IbzDailyBaseService {
    /**
     * Creates an instance of IbzDailyService.
     * @memberof IbzDailyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzDailyService')) {
            return ___ibz___.sc.get('IbzDailyService');
        }
        ___ibz___.sc.set('IbzDailyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzDailyService}
     * @memberof IbzDailyService
     */
    static getInstance(): IbzDailyService {
        if (!___ibz___.sc.has('IbzDailyService')) {
            new IbzDailyService();
        }
        return ___ibz___.sc.get('IbzDailyService');
    }
}
export default IbzDailyService;