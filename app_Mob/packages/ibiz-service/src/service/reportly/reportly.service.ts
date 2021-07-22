import { ReportlyBaseService } from './reportly-base.service';

/**
 * 汇报服务
 *
 * @export
 * @class ReportlyService
 * @extends {ReportlyBaseService}
 */
export class ReportlyService extends ReportlyBaseService {
    /**
     * Creates an instance of ReportlyService.
     * @memberof ReportlyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ReportlyService')) {
            return ___ibz___.sc.get('ReportlyService');
        }
        ___ibz___.sc.set('ReportlyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ReportlyService}
     * @memberof ReportlyService
     */
    static getInstance(): ReportlyService {
        if (!___ibz___.sc.has('ReportlyService')) {
            new ReportlyService();
        }
        return ___ibz___.sc.get('ReportlyService');
    }
}
export default ReportlyService;
