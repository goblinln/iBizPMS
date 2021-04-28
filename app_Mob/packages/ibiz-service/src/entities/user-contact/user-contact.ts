import { UserContactBase } from './user-contact-base';

/**
 * 用户联系方式
 *
 * @export
 * @class UserContact
 * @extends {UserContactBase}
 * @implements {IUserContact}
 */
export class UserContact extends UserContactBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof UserContact
     */
    clone(): UserContact {
        return new UserContact(this);
    }
}
export default UserContact;
