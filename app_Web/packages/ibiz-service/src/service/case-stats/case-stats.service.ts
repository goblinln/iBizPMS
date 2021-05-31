import { CaseStatsBaseService } from './case-stats-base.service';

/**
 * 测试用例统计服务
 *
 * @export
 * @class CaseStatsService
 * @extends {CaseStatsBaseService}
 */
export class CaseStatsService extends CaseStatsBaseService {
    /**
     * Creates an instance of CaseStatsService.
     * @memberof CaseStatsService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('CaseStatsService')) {
            return ___ibz___.sc.get('CaseStatsService');
        }
        ___ibz___.sc.set('CaseStatsService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {CaseStatsService}
     * @memberof CaseStatsService
     */
    static getInstance(): CaseStatsService {
        if (!___ibz___.sc.has('CaseStatsService')) {
            new CaseStatsService();
        }
        return ___ibz___.sc.get('CaseStatsService');
    }
}
export default CaseStatsService;
