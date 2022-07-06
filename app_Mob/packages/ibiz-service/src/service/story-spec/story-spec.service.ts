import { StorySpecBaseService } from './story-spec-base.service';

/**
 * 需求描述服务
 *
 * @export
 * @class StorySpecService
 * @extends {StorySpecBaseService}
 */
export class StorySpecService extends StorySpecBaseService {
    /**
     * Creates an instance of StorySpecService.
     * @memberof StorySpecService
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
     * @return {*}  {StorySpecService}
     * @memberof StorySpecService
     */
    static getInstance(context?: any): StorySpecService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}StorySpecService` : `StorySpecService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new StorySpecService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default StorySpecService;
