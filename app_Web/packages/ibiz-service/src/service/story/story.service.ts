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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('StoryService')) {
            return ___ibz___.sc.get('StoryService');
        }
        ___ibz___.sc.set('StoryService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {StoryService}
     * @memberof StoryService
     */
    static getInstance(): StoryService {
        if (!___ibz___.sc.has('StoryService')) {
            new StoryService();
        }
        return ___ibz___.sc.get('StoryService');
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
