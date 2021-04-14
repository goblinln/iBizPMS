import { IbzReportlyBaseService } from './ibz-reportly-base.service';

/**
 * 汇报服务
 *
 * @export
 * @class IbzReportlyService
 * @extends {IbzReportlyBaseService}
 */
export class IbzReportlyService extends IbzReportlyBaseService {
    /**
     * Creates an instance of IbzReportlyService.
     * @memberof IbzReportlyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzReportlyService')) {
            return ___ibz___.sc.get('IbzReportlyService');
        }
        ___ibz___.sc.set('IbzReportlyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzReportlyService}
     * @memberof IbzReportlyService
     */
    static getInstance(): IbzReportlyService {
        if (!___ibz___.sc.has('IbzReportlyService')) {
            new IbzReportlyService();
        }
        return ___ibz___.sc.get('IbzReportlyService');
    }
}
export default IbzReportlyService;