import { EntityBase } from 'ibiz-core';
import { ISysUserRole } from '../interface';

/**
 * 用户角色关系基类
 *
 * @export
 * @abstract
 * @class SysUserRoleBase
 * @extends {EntityBase}
 * @implements {ISysUserRole}
 */
export abstract class SysUserRoleBase extends EntityBase implements ISysUserRole {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SysUserRoleBase
     */
    get srfdename(): string {
        return 'SYS_USER_ROLE';
    }
    get srfkey() {
        return this.userroleid;
    }
    set srfkey(val: any) {
        this.userroleid = val;
    }
    get srfmajortext() {
        return this.userid;
    }
    set srfmajortext(val: any) {
        this.userid = val;
    }
    /**
     * 用户角色关系标识
     */
    userroleid?: any;
    /**
     * 角色标识
     */
    roleid?: any;
    /**
     * 角色名称
     */
    rolename?: any;
    /**
     * 用户标识
     */
    userid?: any;
    /**
     * 用户名称
     */
    personname?: any;
    /**
     * 登录名
     */
    loginname?: any;
    /**
     * 单位
     */
    orgname?: any;
    /**
     * 主部门
     */
    mdeptname?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新时间
     */
    updatedate?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SysUserRoleBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.userroleid = data.userroleid || data.srfkey;
        this.userid = data.userid || data.srfmajortext;
    }
}
