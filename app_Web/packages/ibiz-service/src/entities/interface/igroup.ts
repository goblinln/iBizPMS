import { IEntityBase } from 'ibiz-core';

/**
 * 群组
 *
 * @export
 * @interface IGroup
 * @extends {IEntityBase}
 */
export interface IGroup extends IEntityBase {
    /**
     * acl
     */
    acl?: any;
    /**
     * 分组描述
     */
    desc?: any;
    /**
     * ID
     */
    id?: any;
    /**
     * 分组名称
     */
    name?: any;
    /**
     * role
     */
    role?: any;
    /**
     * 群组编号
     */
    groupsn?: any;
}
