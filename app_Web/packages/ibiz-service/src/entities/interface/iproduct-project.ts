import { IEntityBase } from 'ibiz-core';

/**
 * 项目产品
 *
 * @export
 * @interface IProductProject
 * @extends {IEntityBase}
 */
export interface IProductProject extends IEntityBase {
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
     * 项目名称
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
     * 项目编号
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
    /**
     * 项目状态
     *
     * @type {('wait' | 'doing' | 'suspended' | 'closed')} wait: 未开始, doing: 进行中, suspended: 已挂起, closed: 已关闭
     */
    status?: 'wait' | 'doing' | 'suspended' | 'closed';
    /**
     * 项目代号
     */
    projectcode?: any;
    /**
     * 结束日期
     */
    projectend?: any;
}
