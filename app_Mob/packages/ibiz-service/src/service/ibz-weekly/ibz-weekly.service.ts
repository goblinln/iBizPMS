import { IbzWeeklyBaseService } from './ibz-weekly-base.service';

/**
 * 周报服务
 *
 * @export
 * @class IbzWeeklyService
 * @extends {IbzWeeklyBaseService}
 */
export class IbzWeeklyService extends IbzWeeklyBaseService {
    /**
     * Creates an instance of IbzWeeklyService.
     * @memberof IbzWeeklyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzWeeklyService')) {
            return ___ibz___.sc.get('IbzWeeklyService');
        }
        ___ibz___.sc.set('IbzWeeklyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzWeeklyService}
     * @memberof IbzWeeklyService
     */
    static getInstance(): IbzWeeklyService {
        if (!___ibz___.sc.has('IbzWeeklyService')) {
            new IbzWeeklyService();
        }
        return ___ibz___.sc.get('IbzWeeklyService');
    }
}
export default IbzWeeklyService;
