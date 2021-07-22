import { UserContactBaseService } from './user-contact-base.service';

/**
 * 用户联系方式服务
 *
 * @export
 * @class UserContactService
 * @extends {UserContactBaseService}
 */
export class UserContactService extends UserContactBaseService {
    /**
     * Creates an instance of UserContactService.
     * @memberof UserContactService
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
     * @return {*}  {UserContactService}
     * @memberof UserContactService
     */
    static getInstance(context?: any): UserContactService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}UserContactService` : `UserContactService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new UserContactService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default UserContactService;
