import { AccountBaseService } from './account-base.service';

/**
 * 系统用户服务
 *
 * @export
 * @class AccountService
 * @extends {AccountBaseService}
 */
export class AccountService extends AccountBaseService {
    /**
     * Creates an instance of AccountService.
     * @memberof AccountService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('AccountService')) {
            return ___ibz___.sc.get('AccountService');
        }
        ___ibz___.sc.set('AccountService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {AccountService}
     * @memberof AccountService
     */
    static getInstance(): AccountService {
        if (!___ibz___.sc.has('AccountService')) {
            new AccountService();
        }
        return ___ibz___.sc.get('AccountService');
    }
}
export default AccountService;
