import { IEntityBase } from 'ibiz-core';

/**
 * 用户角色关系
 *
 * @export
 * @interface ISysUserRole
 * @extends {IEntityBase}
 */
export interface ISysUserRole extends IEntityBase {
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
}
