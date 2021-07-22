import { IbzTaskestimateBaseService } from './ibz-taskestimate-base.service';

/**
 * 任务预计服务
 *
 * @export
 * @class IbzTaskestimateService
 * @extends {IbzTaskestimateBaseService}
 */
export class IbzTaskestimateService extends IbzTaskestimateBaseService {
    /**
     * Creates an instance of IbzTaskestimateService.
     * @memberof IbzTaskestimateService
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
     * @return {*}  {IbzTaskestimateService}
     * @memberof IbzTaskestimateService
     */
    static getInstance(context?: any): IbzTaskestimateService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzTaskestimateService` : `IbzTaskestimateService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzTaskestimateService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzTaskestimateService;
