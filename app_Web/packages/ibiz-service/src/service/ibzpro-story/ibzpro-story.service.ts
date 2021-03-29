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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZProStoryService')) {
            return ___ibz___.sc.get('IBZProStoryService');
        }
        ___ibz___.sc.set('IBZProStoryService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZProStoryService}
     * @memberof IBZProStoryService
     */
    static getInstance(): IBZProStoryService {
        if (!___ibz___.sc.has('IBZProStoryService')) {
            new IBZProStoryService();
        }
        return ___ibz___.sc.get('IBZProStoryService');
    }
}
export default IBZProStoryService;