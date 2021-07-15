import { IBIZProKeywordBaseService } from './ibizpro-keyword-base.service';

/**
 * 关键字服务
 *
 * @export
 * @class IBIZProKeywordService
 * @extends {IBIZProKeywordBaseService}
 */
export class IBIZProKeywordService extends IBIZProKeywordBaseService {
    /**
     * Creates an instance of IBIZProKeywordService.
     * @memberof IBIZProKeywordService
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
     * @return {*}  {IBIZProKeywordService}
     * @memberof IBIZProKeywordService
     */
    static getInstance(context?: any): IBIZProKeywordService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IBIZProKeywordService` : `IBIZProKeywordService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IBIZProKeywordService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IBIZProKeywordService;
