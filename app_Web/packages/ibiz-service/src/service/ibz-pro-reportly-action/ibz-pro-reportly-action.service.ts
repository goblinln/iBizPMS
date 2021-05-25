import { IbzProReportlyActionBaseService } from './ibz-pro-reportly-action-base.service';

/**
 * 汇报日志服务
 *
 * @export
 * @class IbzProReportlyActionService
 * @extends {IbzProReportlyActionBaseService}
 */
export class IbzProReportlyActionService extends IbzProReportlyActionBaseService {
    /**
     * Creates an instance of IbzProReportlyActionService.
     * @memberof IbzProReportlyActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzProReportlyActionService')) {
            return ___ibz___.sc.get('IbzProReportlyActionService');
        }
        ___ibz___.sc.set('IbzProReportlyActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzProReportlyActionService}
     * @memberof IbzProReportlyActionService
     */
    static getInstance(): IbzProReportlyActionService {
        if (!___ibz___.sc.has('IbzProReportlyActionService')) {
            new IbzProReportlyActionService();
        }
        return ___ibz___.sc.get('IbzProReportlyActionService');
    }
}
export default IbzProReportlyActionService;
