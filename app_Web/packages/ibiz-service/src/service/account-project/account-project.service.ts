import { AccountProjectBaseService } from './account-project-base.service';

/**
 * 项目服务
 *
 * @export
 * @class AccountProjectService
 * @extends {AccountProjectBaseService}
 */
export class AccountProjectService extends AccountProjectBaseService {
    /**
     * Creates an instance of AccountProjectService.
     * @memberof AccountProjectService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('AccountProjectService')) {
            return ___ibz___.sc.get('AccountProjectService');
        }
        ___ibz___.sc.set('AccountProjectService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {AccountProjectService}
     * @memberof AccountProjectService
     */
    static getInstance(): AccountProjectService {
        if (!___ibz___.sc.has('AccountProjectService')) {
            new AccountProjectService();
        }
        return ___ibz___.sc.get('AccountProjectService');
    }
}
export default AccountProjectService;
