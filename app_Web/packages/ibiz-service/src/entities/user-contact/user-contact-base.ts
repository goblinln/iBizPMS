import { EntityBase } from 'ibiz-core';
import { IUserContact } from '../interface';

/**
 * 用户联系方式基类
 *
 * @export
 * @abstract
 * @class UserContactBase
 * @extends {EntityBase}
 * @implements {IUserContact}
 */
export abstract class UserContactBase extends EntityBase implements IUserContact {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof UserContactBase
     */
    get srfdename(): string {
        return 'ZT_USERCONTACT';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.listname;
    }
    set srfmajortext(val: any) {
        this.listname = val;
    }
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof UserContactBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.listname = data.listname || data.srfmajortext;
    }
}
