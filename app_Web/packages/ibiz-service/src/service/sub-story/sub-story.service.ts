import { SubStoryBaseService } from './sub-story-base.service';

/**
 * 需求服务
 *
 * @export
 * @class SubStoryService
 * @extends {SubStoryBaseService}
 */
export class SubStoryService extends SubStoryBaseService {
    /**
     * Creates an instance of SubStoryService.
     * @memberof SubStoryService
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
     * @return {*}  {SubStoryService}
     * @memberof SubStoryService
     */
    static getInstance(context?: any): SubStoryService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SubStoryService` : `SubStoryService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SubStoryService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SubStoryService;
