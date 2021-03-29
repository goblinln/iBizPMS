import { IBZProStoryModuleBaseService } from './ibzpro-story-module-base.service';

/**
 * 需求模块服务
 *
 * @export
 * @class IBZProStoryModuleService
 * @extends {IBZProStoryModuleBaseService}
 */
export class IBZProStoryModuleService extends IBZProStoryModuleBaseService {
    /**
     * Creates an instance of IBZProStoryModuleService.
     * @memberof IBZProStoryModuleService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZProStoryModuleService')) {
            return ___ibz___.sc.get('IBZProStoryModuleService');
        }
        ___ibz___.sc.set('IBZProStoryModuleService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZProStoryModuleService}
     * @memberof IBZProStoryModuleService
     */
    static getInstance(): IBZProStoryModuleService {
        if (!___ibz___.sc.has('IBZProStoryModuleService')) {
            new IBZProStoryModuleService();
        }
        return ___ibz___.sc.get('IBZProStoryModuleService');
    }
}
export default IBZProStoryModuleService;
