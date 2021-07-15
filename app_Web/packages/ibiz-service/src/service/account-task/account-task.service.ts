import { AccountTaskBaseService } from './account-task-base.service';

/**
 * 任务服务
 *
 * @export
 * @class AccountTaskService
 * @extends {AccountTaskBaseService}
 */
export class AccountTaskService extends AccountTaskBaseService {
    /**
     * Creates an instance of AccountTaskService.
     * @memberof AccountTaskService
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
     * @return {*}  {AccountTaskService}
     * @memberof AccountTaskService
     */
    static getInstance(context?: any): AccountTaskService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}AccountTaskService` : `AccountTaskService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new AccountTaskService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default AccountTaskService;
