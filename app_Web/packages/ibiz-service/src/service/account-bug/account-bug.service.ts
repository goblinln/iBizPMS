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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('AccountBugService')) {
            return ___ibz___.sc.get('AccountBugService');
        }
        ___ibz___.sc.set('AccountBugService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {AccountBugService}
     * @memberof AccountBugService
     */
    static getInstance(): AccountBugService {
        if (!___ibz___.sc.has('AccountBugService')) {
            new AccountBugService();
        }
        return ___ibz___.sc.get('AccountBugService');
    }
}
export default AccountBugService;
