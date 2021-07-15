import { AccountTestTaskBaseService } from './account-test-task-base.service';

/**
 * 测试版本服务
 *
 * @export
 * @class AccountTestTaskService
 * @extends {AccountTestTaskBaseService}
 */
export class AccountTestTaskService extends AccountTestTaskBaseService {
    /**
     * Creates an instance of AccountTestTaskService.
     * @memberof AccountTestTaskService
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
     * @return {*}  {AccountTestTaskService}
     * @memberof AccountTestTaskService
     */
    static getInstance(context?: any): AccountTestTaskService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}AccountTestTaskService` : `AccountTestTaskService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new AccountTestTaskService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default AccountTestTaskService;
