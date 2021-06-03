import { AccountProductBaseService } from './account-product-base.service';

/**
 * 产品服务
 *
 * @export
 * @class AccountProductService
 * @extends {AccountProductBaseService}
 */
export class AccountProductService extends AccountProductBaseService {
    /**
     * Creates an instance of AccountProductService.
     * @memberof AccountProductService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('AccountProductService')) {
            return ___ibz___.sc.get('AccountProductService');
        }
        ___ibz___.sc.set('AccountProductService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {AccountProductService}
     * @memberof AccountProductService
     */
    static getInstance(): AccountProductService {
        if (!___ibz___.sc.has('AccountProductService')) {
            new AccountProductService();
        }
        return ___ibz___.sc.get('AccountProductService');
    }
}
export default AccountProductService;
