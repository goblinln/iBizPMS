import { UserBase } from './user-base';

/**
 * 用户
 *
 * @export
 * @class User
 * @extends {UserBase}
 * @implements {IUser}
 */
export class User extends UserBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof User
     */
    clone(): User {
        return new User(this);
    }
}
export default User;
