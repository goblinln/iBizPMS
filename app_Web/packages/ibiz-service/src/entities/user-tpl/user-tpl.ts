import { UserTplBase } from './user-tpl-base';

/**
 * 用户模板
 *
 * @export
 * @class UserTpl
 * @extends {UserTplBase}
 * @implements {IUserTpl}
 */
export class UserTpl extends UserTplBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof UserTpl
     */
    clone(): UserTpl {
        return new UserTpl(this);
    }
}
export default UserTpl;
