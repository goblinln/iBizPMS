import { StoryBaseService } from './story-base.service';

/**
 * 需求服务
 *
 * @export
 * @class StoryService
 * @extends {StoryBaseService}
 */
export class StoryService extends StoryBaseService {
    /**
     * Creates an instance of StoryService.
     * @memberof StoryService
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
     * @return {*}  {StoryService}
     * @memberof StoryService
     */
    static getInstance(context?: any): StoryService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}StoryService` : `StoryService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new StoryService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default StoryService;
