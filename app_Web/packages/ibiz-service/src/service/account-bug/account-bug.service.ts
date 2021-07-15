import { AccountBugBaseService } from './account-bug-base.service';

/**
 * Bug服务
 *
 * @export
 * @class AccountBugService
 * @extends {AccountBugBaseService}
 */
export class AccountBugService extends AccountBugBaseService {
    /**
     * Creates an instance of AccountBugService.
     * @memberof AccountBugService
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
     * @return {*}  {AccountBugService}
     * @memberof AccountBugService
     */
    static getInstance(context?: any): AccountBugService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}AccountBugService` : `AccountBugService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new AccountBugService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default AccountBugService;
