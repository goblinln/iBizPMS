import { IEntityBase } from 'ibiz-core';

/**
 * 产品团队
 *
 * @export
 * @interface IProductTeam
 * @extends {IEntityBase}
 */
export interface IProductTeam extends IEntityBase {
    /**
     * 成员状态
     */
    teamstatus?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 加盟日
     */
    join?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 总计可用
     */
    total?: any;
    /**
     * 结束时间
     */
    end?: any;
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
     * 归属部门
     */
    dept?: any;
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
     * 由谁更新
     */
    updateby?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 当前负责人
     */
    leadingcadre?: any;
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
