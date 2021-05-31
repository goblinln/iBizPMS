import { IEntityBase } from 'ibiz-core';

/**
 * 系统角色
 *
 * @export
 * @interface ISysRole
 * @extends {IEntityBase}
 */
export interface ISysRole extends IEntityBase {
    /**
     * 角色标识
     */
    roleid?: any;
    /**
     * 角色名称
     */
    rolename?: any;
    /**
     * 备注
     */
    memo?: any;
    /**
     * 父角色标识
     */
    proleid?: any;
    /**
     * 父角色名称
     */
    prolename?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新人
     */
    updateman?: any;
}
