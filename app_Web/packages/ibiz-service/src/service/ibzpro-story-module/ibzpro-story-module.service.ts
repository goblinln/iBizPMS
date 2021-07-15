import { IBZProStoryModuleBaseService } from './ibzpro-story-module-base.service';

/**
 * 需求模块（iBizSys）服务
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
     * @return {*}  {IBZProStoryModuleService}
     * @memberof IBZProStoryModuleService
     */
    static getInstance(context?: any): IBZProStoryModuleService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IBZProStoryModuleService` : `IBZProStoryModuleService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IBZProStoryModuleService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IBZProStoryModuleService;
