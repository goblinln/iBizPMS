import { IbzProMonthlyActionBaseService } from './ibz-pro-monthly-action-base.service';

/**
 * 月报日志服务
 *
 * @export
 * @class IbzProMonthlyActionService
 * @extends {IbzProMonthlyActionBaseService}
 */
export class IbzProMonthlyActionService extends IbzProMonthlyActionBaseService {
    /**
     * Creates an instance of IbzProMonthlyActionService.
     * @memberof IbzProMonthlyActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzProMonthlyActionService')) {
            return ___ibz___.sc.get('IbzProMonthlyActionService');
        }
        ___ibz___.sc.set('IbzProMonthlyActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzProMonthlyActionService}
     * @memberof IbzProMonthlyActionService
     */
    static getInstance(): IbzProMonthlyActionService {
        if (!___ibz___.sc.has('IbzProMonthlyActionService')) {
            new IbzProMonthlyActionService();
        }
        return ___ibz___.sc.get('IbzProMonthlyActionService');
    }
}
export default IbzProMonthlyActionService;
