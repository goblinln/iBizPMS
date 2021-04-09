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
     * 周末
     */
    isweekend?: any;
    /**
     * 日期
     */
    date?: any;
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
