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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('UserTplService')) {
            return ___ibz___.sc.get('UserTplService');
        }
        ___ibz___.sc.set('UserTplService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {UserTplService}
     * @memberof UserTplService
     */
    static getInstance(): UserTplService {
        if (!___ibz___.sc.has('UserTplService')) {
            new UserTplService();
        }
        return ___ibz___.sc.get('UserTplService');
    }
}
export default UserTplService;
