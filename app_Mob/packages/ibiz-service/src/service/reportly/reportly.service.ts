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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {ReportlyService}
     * @memberof ReportlyService
     */
    static getInstance(context?: any): ReportlyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ReportlyService` : `ReportlyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ReportlyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ReportlyService;
