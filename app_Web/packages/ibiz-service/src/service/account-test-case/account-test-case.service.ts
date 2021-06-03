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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('AccountTestCaseService')) {
            return ___ibz___.sc.get('AccountTestCaseService');
        }
        ___ibz___.sc.set('AccountTestCaseService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {AccountTestCaseService}
     * @memberof AccountTestCaseService
     */
    static getInstance(): AccountTestCaseService {
        if (!___ibz___.sc.has('AccountTestCaseService')) {
            new AccountTestCaseService();
        }
        return ___ibz___.sc.get('AccountTestCaseService');
    }
}
export default AccountTestCaseService;
