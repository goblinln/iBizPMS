import { IbizproProjectDailyBaseService } from './ibizpro-project-daily-base.service';

/**
 * 项目日报服务
 *
 * @export
 * @class IbizproProjectDailyService
 * @extends {IbizproProjectDailyBaseService}
 */
export class IbizproProjectDailyService extends IbizproProjectDailyBaseService {
    /**
     * Creates an instance of IbizproProjectDailyService.
     * @memberof IbizproProjectDailyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbizproProjectDailyService')) {
            return ___ibz___.sc.get('IbizproProjectDailyService');
        }
        ___ibz___.sc.set('IbizproProjectDailyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbizproProjectDailyService}
     * @memberof IbizproProjectDailyService
     */
    static getInstance(): IbizproProjectDailyService {
        if (!___ibz___.sc.has('IbizproProjectDailyService')) {
            new IbizproProjectDailyService();
        }
        return ___ibz___.sc.get('IbizproProjectDailyService');
    }
}
export default IbizproProjectDailyService;