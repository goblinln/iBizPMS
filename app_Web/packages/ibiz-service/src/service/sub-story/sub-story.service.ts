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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SubStoryService')) {
            return ___ibz___.sc.get('SubStoryService');
        }
        ___ibz___.sc.set('SubStoryService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SubStoryService}
     * @memberof SubStoryService
     */
    static getInstance(): SubStoryService {
        if (!___ibz___.sc.has('SubStoryService')) {
            new SubStoryService();
        }
        return ___ibz___.sc.get('SubStoryService');
    }
}
export default SubStoryService;
