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
     * @return {*}  {AccountProjectService}
     * @memberof AccountProjectService
     */
    static getInstance(context?: any): AccountProjectService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}AccountProjectService` : `AccountProjectService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new AccountProjectService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default AccountProjectService;
