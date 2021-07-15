import { AccountStoryBaseService } from './account-story-base.service';

/**
 * 需求服务
 *
 * @export
 * @class AccountStoryService
 * @extends {AccountStoryBaseService}
 */
export class AccountStoryService extends AccountStoryBaseService {
    /**
     * Creates an instance of AccountStoryService.
     * @memberof AccountStoryService
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
     * @return {*}  {AccountStoryService}
     * @memberof AccountStoryService
     */
    static getInstance(context?: any): AccountStoryService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}AccountStoryService` : `AccountStoryService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new AccountStoryService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default AccountStoryService;
