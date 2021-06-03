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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('AccountTestTaskService')) {
            return ___ibz___.sc.get('AccountTestTaskService');
        }
        ___ibz___.sc.set('AccountTestTaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {AccountTestTaskService}
     * @memberof AccountTestTaskService
     */
    static getInstance(): AccountTestTaskService {
        if (!___ibz___.sc.has('AccountTestTaskService')) {
            new AccountTestTaskService();
        }
        return ___ibz___.sc.get('AccountTestTaskService');
    }
}
export default AccountTestTaskService;
