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
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
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
    /**
     * 主键
     */
    id?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
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
    /**
     * 产品编号
     */
    productcode?: any;
    /**
     * 计划开始时间
     */
    begin?: any;
    /**
     * 计划结束时间
     */
    end?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
}
