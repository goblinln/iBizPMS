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
     * 归属组织名
     */
    orgname?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * userList
     */
    userlist?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 标题
     */
    listname?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 用户联系方式编号
     */
    usercontactsn?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * account
     */
    account?: any;
}
