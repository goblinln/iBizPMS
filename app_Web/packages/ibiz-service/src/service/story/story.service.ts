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
}
export default StoryService;