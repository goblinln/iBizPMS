import { StoryStageBaseService } from './story-stage-base.service';

/**
 * 需求阶段服务
 *
 * @export
 * @class StoryStageService
 * @extends {StoryStageBaseService}
 */
export class StoryStageService extends StoryStageBaseService {
    /**
     * Creates an instance of StoryStageService.
     * @memberof StoryStageService
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
     * @return {*}  {StoryStageService}
     * @memberof StoryStageService
     */
    static getInstance(context?: any): StoryStageService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}StoryStageService` : `StoryStageService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new StoryStageService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default StoryStageService;
