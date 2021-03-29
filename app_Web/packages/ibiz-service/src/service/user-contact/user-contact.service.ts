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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('UserContactService')) {
            return ___ibz___.sc.get('UserContactService');
        }
        ___ibz___.sc.set('UserContactService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {UserContactService}
     * @memberof UserContactService
     */
    static getInstance(): UserContactService {
        if (!___ibz___.sc.has('UserContactService')) {
            new UserContactService();
        }
        return ___ibz___.sc.get('UserContactService');
    }
}
export default UserContactService;