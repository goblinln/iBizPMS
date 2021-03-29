import { IbzReportBaseService } from './ibz-report-base.service';

/**
 * 汇报汇总服务
 *
 * @export
 * @class IbzReportService
 * @extends {IbzReportBaseService}
 */
export class IbzReportService extends IbzReportBaseService {
    /**
     * Creates an instance of IbzReportService.
     * @memberof IbzReportService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzReportService')) {
            return ___ibz___.sc.get('IbzReportService');
        }
        ___ibz___.sc.set('IbzReportService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzReportService}
     * @memberof IbzReportService
     */
    static getInstance(): IbzReportService {
        if (!___ibz___.sc.has('IbzReportService')) {
            new IbzReportService();
        }
        return ___ibz___.sc.get('IbzReportService');
    }
}
export default IbzReportService;