import { IEntityBase } from 'ibiz-core';

/**
 * 部门
 *
 * @export
 * @interface IDept
 * @extends {IEntityBase}
 */
export interface IDept extends IEntityBase {
    /**
     * 负责人
     */
    manager?: any;
    /**
     * 无子部门
     */
    isleaf?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * function
     */
    function?: any;
    /**
     * order
     */
    order?: any;
    /**
     * path
     */
    path?: any;
    /**
     * position
     */
    position?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 部门名称
     */
    name?: any;
    /**
     * 上级部门
     */
    parentname?: any;
    /**
     * parent
     */
    parent?: any;
}
