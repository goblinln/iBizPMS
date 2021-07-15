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

    /**
     * ImportPlanStories接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof StoryServiceBase
     */
    public async ImportPlanStories(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        context.story = 0;
        data.id = 0;
        return this.http.post(`/stories/${context.story}/importplanstories`,data);
    }
}
export default StoryService;
