import { AccountTestCaseBaseService } from './account-test-case-base.service';

/**
 * 测试用例服务
 *
 * @export
 * @class AccountTestCaseService
 * @extends {AccountTestCaseBaseService}
 */
export class AccountTestCaseService extends AccountTestCaseBaseService {
    /**
     * Creates an instance of AccountTestCaseService.
     * @memberof AccountTestCaseService
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
     * @return {*}  {AccountTestCaseService}
     * @memberof AccountTestCaseService
     */
    static getInstance(context?: any): AccountTestCaseService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}AccountTestCaseService` : `AccountTestCaseService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new AccountTestCaseService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default AccountTestCaseService;
