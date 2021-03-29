import { IEntityBase } from 'ibiz-core';

/**
 * 项目团队
 *
 * @export
 * @interface IProjectTeam
 * @extends {IEntityBase}
 */
export interface IProjectTeam extends IEntityBase {
    /**
     * 角色
     */
    role?: any;
    /**
     * 受限用户
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    limited?: 'yes' | 'no';
    /**
     * 总计可用
     */
    total?: any;
    /**
     * 用户
     */
    username?: any;
    /**
     * 可用工日
     */
    days?: any;
    /**
     * 团队类型
     *
     * @type {('project' | 'task' | 'product')} project: 项目团队, task: 任务团队, product: 产品团队
     */
    type?: 'project' | 'task' | 'product';
    /**
     * 排序
     */
    order?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 总计消耗
     */
    consumed?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 最初预计
     */
    estimate?: any;
    /**
     * 加盟日
     */
    join?: any;
    /**
     * 可用工时/天
     */
    hours?: any;
    /**
     * 任务数
     */
    taskcnt?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 项目编号
     */
    root?: any;
}
