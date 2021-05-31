import { IEntityBase } from 'ibiz-core';

/**
 * burn
 *
 * @export
 * @interface IProjectBurn
 * @extends {IEntityBase}
 */
export interface IProjectBurn extends IEntityBase {
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 周末
     */
    isweekend?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 总计消耗
     */
    consumed?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 主键
     */
    id?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 最初预计
     */
    estimate?: any;
    /**
     * 所属项目
     */
    project?: any;
    /**
     * 任务
     */
    task?: any;
}
