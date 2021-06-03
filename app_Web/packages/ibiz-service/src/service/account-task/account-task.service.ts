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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('AccountTaskService')) {
            return ___ibz___.sc.get('AccountTaskService');
        }
        ___ibz___.sc.set('AccountTaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {AccountTaskService}
     * @memberof AccountTaskService
     */
    static getInstance(): AccountTaskService {
        if (!___ibz___.sc.has('AccountTaskService')) {
            new AccountTaskService();
        }
        return ___ibz___.sc.get('AccountTaskService');
    }
}
export default AccountTaskService;
