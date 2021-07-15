import { UserTplBaseService } from './user-tpl-base.service';

/**
 * 用户模板服务
 *
 * @export
 * @class UserTplService
 * @extends {UserTplBaseService}
 */
export class UserTplService extends UserTplBaseService {
    /**
     * Creates an instance of UserTplService.
     * @memberof UserTplService
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
     * @return {*}  {UserTplService}
     * @memberof UserTplService
     */
    static getInstance(context?: any): UserTplService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}UserTplService` : `UserTplService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new UserTplService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default UserTplService;
