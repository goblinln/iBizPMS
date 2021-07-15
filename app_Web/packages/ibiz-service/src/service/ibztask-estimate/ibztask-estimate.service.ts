import { IBZTaskEstimateBaseService } from './ibztask-estimate-base.service';

/**
 * 任务预计服务
 *
 * @export
 * @class IBZTaskEstimateService
 * @extends {IBZTaskEstimateBaseService}
 */
export class IBZTaskEstimateService extends IBZTaskEstimateBaseService {
    /**
     * Creates an instance of IBZTaskEstimateService.
     * @memberof IBZTaskEstimateService
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
     * @return {*}  {IBZTaskEstimateService}
     * @memberof IBZTaskEstimateService
     */
    static getInstance(context?: any): IBZTaskEstimateService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IBZTaskEstimateService` : `IBZTaskEstimateService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IBZTaskEstimateService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IBZTaskEstimateService;
