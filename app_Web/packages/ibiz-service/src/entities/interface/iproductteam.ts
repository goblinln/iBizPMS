import { IEntityBase } from 'ibiz-core';

/**
 * 产品团队
 *
 * @export
 * @interface IPRODUCTTEAM
 * @extends {IEntityBase}
 */
export interface IPRODUCTTEAM extends IEntityBase {
    /**
     * 用户
     */
    account?: any;
    /**
     * 加盟日
     */
    join?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 总计可用
     */
    total?: any;
    /**
     * 用户
     */
    username?: any;
    /**
     * 最初预计
     */
    estimate?: any;
    /**
     * 可用工时/天
     */
    hours?: any;
    /**
     * 任务数
     */
    taskcnt?: any;
    /**
     * 团队类型
     *
     * @type {('project' | 'task' | 'product')} project: 项目团队, task: 任务团队, product: 产品团队
     */
    type?: 'project' | 'task' | 'product';
    /**
     * 总计消耗
     */
    consumed?: any;
    /**
     * 可用工日
     */
    days?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 受限用户
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    limited?: 'yes' | 'no';
    /**
     * 排序
     */
    order?: any;
    /**
     * 角色
     */
    role?: any;
    /**
     * 产品编号
     */
    root?: any;
}
