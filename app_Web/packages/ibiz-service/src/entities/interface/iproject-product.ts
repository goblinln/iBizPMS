import { IEntityBase } from 'ibiz-core';

/**
 * 项目产品
 *
 * @export
 * @interface IProjectProduct
 * @extends {IEntityBase}
 */
export interface IProjectProduct extends IEntityBase {
    /**
     * 虚拟主键
     */
    id?: any;
    /**
     * 产品
     */
    productname?: any;
    /**
     * 项目
     */
    projectname?: any;
    /**
     * 计划名称
     */
    planname?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 产品计划
     */
    plan?: any;
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 项目
     */
    project?: any;
}
