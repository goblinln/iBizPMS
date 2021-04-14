import { CompanyStatsBaseService } from './company-stats-base.service';

/**
 * 公司动态汇总服务
 *
 * @export
 * @class CompanyStatsService
 * @extends {CompanyStatsBaseService}
 */
export class CompanyStatsService extends CompanyStatsBaseService {
    /**
     * Creates an instance of CompanyStatsService.
     * @memberof CompanyStatsService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('CompanyStatsService')) {
            return ___ibz___.sc.get('CompanyStatsService');
        }
        ___ibz___.sc.set('CompanyStatsService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {CompanyStatsService}
     * @memberof CompanyStatsService
     */
    static getInstance(): CompanyStatsService {
        if (!___ibz___.sc.has('CompanyStatsService')) {
            new CompanyStatsService();
        }
        return ___ibz___.sc.get('CompanyStatsService');
    }
}
export default CompanyStatsService;