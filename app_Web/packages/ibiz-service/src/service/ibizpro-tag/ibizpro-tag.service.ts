import { IBIZProTagBaseService } from './ibizpro-tag-base.service';

/**
 * 标签服务
 *
 * @export
 * @class IBIZProTagService
 * @extends {IBIZProTagBaseService}
 */
export class IBIZProTagService extends IBIZProTagBaseService {
    /**
     * Creates an instance of IBIZProTagService.
     * @memberof IBIZProTagService
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
     * @return {*}  {IBIZProTagService}
     * @memberof IBIZProTagService
     */
    static getInstance(context?: any): IBIZProTagService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IBIZProTagService` : `IBIZProTagService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IBIZProTagService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IBIZProTagService;
