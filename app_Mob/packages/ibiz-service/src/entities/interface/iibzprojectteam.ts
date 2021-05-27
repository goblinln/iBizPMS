import { IEntityBase } from 'ibiz-core';

/**
 * 项目团队
 *
 * @export
 * @interface IIBZPROJECTTEAM
 * @extends {IEntityBase}
 */
export interface IIBZPROJECTTEAM extends IEntityBase {
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
     * 退场时间
     */
    exitdate?: any;
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
     * 项目经理
     */
    pm?: any;
    /**
     * 所属项目
     */
    projectname?: any;
    /**
     * 项目编号
     */
    root?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
}
