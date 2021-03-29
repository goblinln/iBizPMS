import { AccountTaskestimateBaseService } from './account-taskestimate-base.service';

/**
 * 用户工时统计服务
 *
 * @export
 * @class AccountTaskestimateService
 * @extends {AccountTaskestimateBaseService}
 */
export class AccountTaskestimateService extends AccountTaskestimateBaseService {
    /**
     * Creates an instance of AccountTaskestimateService.
     * @memberof AccountTaskestimateService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('AccountTaskestimateService')) {
            return ___ibz___.sc.get('AccountTaskestimateService');
        }
        ___ibz___.sc.set('AccountTaskestimateService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {AccountTaskestimateService}
     * @memberof AccountTaskestimateService
     */
    static getInstance(): AccountTaskestimateService {
        if (!___ibz___.sc.has('AccountTaskestimateService')) {
            new AccountTaskestimateService();
        }
        return ___ibz___.sc.get('AccountTaskestimateService');
    }
}
export default AccountTaskestimateService;