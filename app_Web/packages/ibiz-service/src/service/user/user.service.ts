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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('UserService')) {
            return ___ibz___.sc.get('UserService');
        }
        ___ibz___.sc.set('UserService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {UserService}
     * @memberof UserService
     */
    static getInstance(): UserService {
        if (!___ibz___.sc.has('UserService')) {
            new UserService();
        }
        return ___ibz___.sc.get('UserService');
    }
}
export default UserService;