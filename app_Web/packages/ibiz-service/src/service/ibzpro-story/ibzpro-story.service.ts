import { IBZProStoryBaseService } from './ibzpro-story-base.service';

/**
 * 需求服务
 *
 * @export
 * @class IBZProStoryService
 * @extends {IBZProStoryBaseService}
 */
export class IBZProStoryService extends IBZProStoryBaseService {
    /**
     * Creates an instance of IBZProStoryService.
     * @memberof IBZProStoryService
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
     * @return {*}  {IBZProStoryService}
     * @memberof IBZProStoryService
     */
    static getInstance(context?: any): IBZProStoryService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IBZProStoryService` : `IBZProStoryService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IBZProStoryService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IBZProStoryService;
