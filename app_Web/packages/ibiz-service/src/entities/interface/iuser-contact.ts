import { IEntityBase } from 'ibiz-core';

/**
 * 用户联系方式
 *
 * @export
 * @interface IUserContact
 * @extends {IEntityBase}
 */
export interface IUserContact extends IEntityBase {
    /**
     * userList
     */
    userlist?: any;
    /**
     * 标题
     */
    listname?: any;
    /**
     * id
     */
    id?: any;
    /**
     * account
     */
    account?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 归属部门
     */
    dept?: any;
}
