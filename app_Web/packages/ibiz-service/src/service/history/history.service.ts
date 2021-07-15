import { HistoryBaseService } from './history-base.service';

/**
 * 操作历史服务
 *
 * @export
 * @class HistoryService
 * @extends {HistoryBaseService}
 */
export class HistoryService extends HistoryBaseService {
    /**
     * Creates an instance of HistoryService.
     * @memberof HistoryService
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
     * @return {*}  {HistoryService}
     * @memberof HistoryService
     */
    static getInstance(context?: any): HistoryService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}HistoryService` : `HistoryService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new HistoryService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default HistoryService;
