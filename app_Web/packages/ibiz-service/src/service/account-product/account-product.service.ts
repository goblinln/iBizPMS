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
     * @return {*}  {AccountProductService}
     * @memberof AccountProductService
     */
    static getInstance(context?: any): AccountProductService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}AccountProductService` : `AccountProductService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new AccountProductService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default AccountProductService;
