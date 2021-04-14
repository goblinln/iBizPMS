import { IEntityBase } from 'ibiz-core';

/**
 * burn
 *
 * @export
 * @interface IBurn
 * @extends {IEntityBase}
 */
export interface IBurn extends IEntityBase {
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
     * 归属部门
     */
    dept?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 虚拟主键
     */
    id?: any;
    /**
     * 总计消耗
     */
    consumed?: any;
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
