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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('StorySpecService')) {
            return ___ibz___.sc.get('StorySpecService');
        }
        ___ibz___.sc.set('StorySpecService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {StorySpecService}
     * @memberof StorySpecService
     */
    static getInstance(): StorySpecService {
        if (!___ibz___.sc.has('StorySpecService')) {
            new StorySpecService();
        }
        return ___ibz___.sc.get('StorySpecService');
    }
}
export default StorySpecService;