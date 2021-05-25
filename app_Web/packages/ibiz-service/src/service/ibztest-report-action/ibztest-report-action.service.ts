import { IBZTestReportActionBaseService } from './ibztest-report-action-base.service';

/**
 * 报告日志服务
 *
 * @export
 * @class IBZTestReportActionService
 * @extends {IBZTestReportActionBaseService}
 */
export class IBZTestReportActionService extends IBZTestReportActionBaseService {
    /**
     * Creates an instance of IBZTestReportActionService.
     * @memberof IBZTestReportActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZTestReportActionService')) {
            return ___ibz___.sc.get('IBZTestReportActionService');
        }
        ___ibz___.sc.set('IBZTestReportActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZTestReportActionService}
     * @memberof IBZTestReportActionService
     */
    static getInstance(): IBZTestReportActionService {
        if (!___ibz___.sc.has('IBZTestReportActionService')) {
            new IBZTestReportActionService();
        }
        return ___ibz___.sc.get('IBZTestReportActionService');
    }
}
export default IBZTestReportActionService;
