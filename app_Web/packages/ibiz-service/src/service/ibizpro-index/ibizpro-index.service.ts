import { IbizproIndexBaseService } from './ibizpro-index-base.service';

/**
 * 索引检索服务
 *
 * @export
 * @class IbizproIndexService
 * @extends {IbizproIndexBaseService}
 */
export class IbizproIndexService extends IbizproIndexBaseService {
    /**
     * Creates an instance of IbizproIndexService.
     * @memberof IbizproIndexService
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
     * @return {*}  {IbizproIndexService}
     * @memberof IbizproIndexService
     */
    static getInstance(context?: any): IbizproIndexService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbizproIndexService` : `IbizproIndexService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbizproIndexService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbizproIndexService;
