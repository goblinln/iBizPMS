import { UserBaseService } from './user-base.service';

/**
 * 用户服务
 *
 * @export
 * @class UserService
 * @extends {UserBaseService}
 */
export class UserService extends UserBaseService {
    /**
     * Creates an instance of UserService.
     * @memberof UserService
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
     * @return {*}  {UserService}
     * @memberof UserService
     */
    static getInstance(context?: any): UserService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}UserService` : `UserService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new UserService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default UserService;
